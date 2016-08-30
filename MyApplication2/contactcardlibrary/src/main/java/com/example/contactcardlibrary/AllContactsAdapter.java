package com.example.contactcardlibrary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sikanted on 8/22/2016.
 */
public class AllContactsAdapter extends ArrayAdapter<ContactData>{
    private int layoutResource;

    public AllContactsAdapter(Context context, int layoutResource, List<ContactData> contactDataList){
        super(context, layoutResource, contactDataList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        final ContactData contactData = getItem(position);

        if (contactData != null) {
            TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            TextView numberTextView = (TextView) view.findViewById(R.id.numberTextView);
            ImageView contactImageView = (ImageView) view.findViewById(R.id.contactImageView);

            if (nameTextView != null) {
                nameTextView.setText(contactData.name);
            }

            if (numberTextView != null) {
                numberTextView.setText(contactData.number);
            }

            if (contactImageView != null && contactData.uri != null) {
                Uri uri = Uri.parse(contactData.uri);
                contactImageView.setImageURI(uri);
            }

            view.setOnClickListener(new View.OnClickListener (){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), RoundedContactCardActivity.class);
                    intent.putExtra("contactName", contactData.name);
                    intent.putExtra("contactNumber", contactData.number);
                    intent.putExtra("contactImageUri", contactData.uri);
                    getContext().startActivity(intent);
                }
            });
        }

        return view;
    }

    private void setPhoto(Uri absPathUri, ImageView imgView){
        if(absPathUri!=null)
        {
            Bitmap myImg = BitmapFactory.decodeFile(absPathUri.getPath());
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                    matrix, true);
            imgView.setImageBitmap(rotated);
        }
    }

}
