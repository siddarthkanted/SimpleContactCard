package com.example.contactcardlibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private static final int READ_CONTACTS_PERMISSION=3;

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case READ_CONTACTS_PERMISSION:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    displayContacts();
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        permissionCheck();
    }

    public void permissionCheck(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION);
        } else {
            displayContacts();
        }
    }

    public void displayContacts(){
        ListView listView = (ListView)findViewById(R.id.allContactsListView);
        AllContactsAdapter allContactsAdapter = new AllContactsAdapter(this, R.layout.all_contacts, getAllContacts());
        listView.setAdapter(allContactsAdapter);
    }

    public List<ContactData> getAllContacts() {
        List<ContactData> contactDataList = new ArrayList<>();

        Cursor phonesCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while(phonesCursor.moveToNext()){
            String name=phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String photoUriString = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

            if(photoUriString!=null){
                Uri photoUri = Uri.parse(photoUriString);
                contactDataList.add(new ContactData(photoUri, name, phoneNumber));
            }
        }
        return contactDataList;
    }
}
