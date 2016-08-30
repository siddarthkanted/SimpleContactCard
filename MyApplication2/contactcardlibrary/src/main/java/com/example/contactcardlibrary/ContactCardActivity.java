package com.example.contactcardlibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactCardActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_card);
        Intent intent = getIntent();
        String id = intent.getStringExtra("contactName");
        String name = intent.getStringExtra("contactNumber");
        String uriString = intent.getStringExtra("contactImageUri");
        setContactImage(uriString);
        setContactName(name);
        setContactNumber(id);
    }

    private void setContactImage(String uriString){
        if(uriString != null){
            Uri uri = Uri.parse(uriString);
            ImageView contactImageView = (ImageView) findViewById(R.id.contactCardImageView);
            contactImageView.setImageURI(uri);
        }
    }

    private void setContactName(String contactName){
        if(contactName != null){
            EditText nameEditText = (EditText) findViewById(R.id.contactCardPersonName);
            nameEditText.setText(contactName);
        }
    }

    private void setContactNumber(String contactNumber){
        if(contactNumber != null){
            EditText numberEditText = (EditText) findViewById(R.id.contactCardPersonNumber);
            numberEditText.setText(contactNumber);
        }
    }

    private String getContactNumber(){
        EditText numberEditText = (EditText) findViewById(R.id.contactCardPersonNumber);
        return numberEditText.getText().toString();
    }

    public void backButtonClick(View view){
        onBackPressed();
    }

    public void goToHomeScreen(View view){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
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
}
