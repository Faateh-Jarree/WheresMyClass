package com.droids.ffs.smd_project.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.droids.ffs.smd_project.MainActivity;
import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "4565";
    public static final String NOTIFICATION_CHANNEL_NAME = "Class Reminders";


    @Override
    public void onReceive(Context context, Intent intent) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ViewScheduleActivity.class);

        Intent notificationIntent = new Intent(context, NotificationActivity.class);

        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

//        Uri sound = Uri.parse("android.resource:" + context.getPackageName() + R.raw.notification_sound);

//Notification Channel
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setImportance(NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setLightColor(Color.CYAN);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100, 500});

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);


        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.logo)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100, 500})
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false);



        Log.v("AlarmLog","OnReceive");

        Notification notification = builder.setContentTitle("Class Reminder: "+intent.getStringExtra("className"))
                .setContentText(intent.getStringExtra("classroom"))
                .setTicker("Wheres my class: Class Reminder")
                .setSmallIcon(R.drawable.logo2)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.logo2))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent).build();
        Log.v("AlarmLog","OnReceive");


        notificationManager.notify(Integer.valueOf(NOTIFICATION_CHANNEL_ID), notification);
        Log.v("AlarmLog","OnReceive");


    }

}
