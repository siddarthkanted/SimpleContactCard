package com.example.sikanted.simplecontactcardapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.contactcardlibrary.ContactActivity;
import com.example.contactcardlibrary.ContactCardLibraryMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callContactCard(View view){
        Intent intent = new Intent(this, ContactCardLibraryMainActivity.class);
        intent.putExtra("contactPersonName", "Siddarth");
        intent.putExtra("contactPersonNumber", "12345");
        startActivity(intent);
    }

    public void allContacts(View view){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}
