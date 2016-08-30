package com.example.contactcardlibrary;

import android.net.Uri;

/**
 * Created by sikanted on 8/22/2016.
 */
public class ContactData {
    public String name;
    public String number;
    public String uri;

    public ContactData(String uri, String name, String number) {
        this.uri = uri;
        this.name = name;
        this.number = number;
    }
}
