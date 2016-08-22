package com.example.contactcardlibrary;

import android.net.Uri;

/**
 * Created by sikanted on 8/22/2016.
 */
public class ContactData {
    public String name;
    public String number;
    public Uri uri;

    public ContactData(Uri uri, String name, String number) {
        this.uri = uri;
        this.name = name;
        this.number = number;
    }
}
