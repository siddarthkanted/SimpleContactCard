package com.example.contactcardlibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RoundedContactCardActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_contact_card);

        Button phoneCallButton = (Button)findViewById(R.id.phoneCallButton);
        phoneCallButton.setBackgroundColor(Color.GREEN);

        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setBackgroundColor(Color.RED);

        Intent intent = getIntent();
        String contactName = intent.getStringExtra("contactName");
        String contactNumber = intent.getStringExtra("contactNumber");
        String contactImageUri = intent.getStringExtra("contactImageUri");
        setContactImage(contactImageUri);
        setContactName(contactName);
        setContactNumber(contactNumber);
    }

    public void backButtonClick(View view){
        onBackPressed();
    }

    public void makePhoneCall(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }

    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + getContactNumber()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
            }
        }
    }

    private void setContactImage(String uriString){
        if(uriString != null){
            Uri uri = Uri.parse(uriString);
            de.hdodenhof.circleimageview.CircleImageView contactImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.roundedContactImageView);
            contactImageView.setImageURI(uri);
        }
    }

    private void setContactName(String contactName){
        if(contactName != null){
            TextView nameEditText = (TextView) findViewById(R.id.roundedContactName);
            nameEditText.setText(contactName);
        }
    }

    private void setContactNumber(String contactNumber){
        if(contactNumber != null){
            TextView numberEditText = (TextView) findViewById(R.id.roundedContactNumber);
            numberEditText.setText(contactNumber);
        }
    }

    private String getContactNumber(){
        TextView numberEditText = (TextView) findViewById(R.id.roundedContactNumber);
        return numberEditText.getText().toString();
    }
}
