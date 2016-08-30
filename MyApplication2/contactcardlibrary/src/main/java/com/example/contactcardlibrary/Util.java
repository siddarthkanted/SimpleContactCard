package com.example.contactcardlibrary;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sikanted on 8/29/2016.
 */
public class Util {

    public static void pushNotificationWithOnClickMultiline(Context context, String notificationTitle, String notificationMessage,  Intent notificationIntent) {
        int icon = R.drawable.defaultcontactimage;

        int mNotificationId = 001;

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context);
        Notification notification = mBuilder.setSmallIcon(icon).setTicker(notificationTitle).setWhen(0)
                .setAutoCancel(false)
                .setContentTitle(notificationTitle)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMessage))
                .setContentIntent(resultPendingIntent)
                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultcontactimage))
                .setContentText(notificationMessage).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mNotificationId, notification);
    }

}
