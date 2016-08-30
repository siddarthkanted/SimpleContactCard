package com.example.contactcardlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sikanted on 8/29/2016.
 */
public class ContactListener  extends ContentObserver {

    private Context context;
    private Activity activity;

    public ContactListener(Context context, Activity activity) {
        super(null);
        this.context =context;
        this.activity = activity;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);


        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show();
                Intent notificationIntent = new Intent(context, ContactCardLibraryMainActivity.class);
                Util.pushNotificationWithOnClickMultiline(context, context.getResources().getString(R.string.app_name), "your contact has been changed", notificationIntent);
            }
        });


    }
}
