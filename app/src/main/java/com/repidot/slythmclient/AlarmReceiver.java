package com.repidot.slythmclient;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.auto_start), Toast.LENGTH_SHORT).show();
        Log.d("success", "onRecieve");


        Log.d("Check", Arrays.toString(intent.getBooleanArrayExtra("weekday")));
        if(intent.getBooleanArrayExtra("weekday") != null) {
            Log.d("Check", "weekdayCheck");
            boolean[] week = intent.getBooleanArrayExtra("weekday");
            Calendar c = Calendar.getInstance();

            assert week != null;
            Log.d("Check", String.valueOf(c.get(Calendar.DAY_OF_WEEK) - 1));
            Log.d("Check", String.valueOf(!week[c.get(Calendar.DAY_OF_WEEK) - 1]));
            if(!week[c.get(Calendar.DAY_OF_WEEK) - 1]) {
                Log.d("Return", "weekdayReturn");
                return;
            }
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Intent i = new Intent(context, AlarmStarter.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("success", "startActicity");
            context.startActivity(i);
            return;
        }
        Intent i = new Intent(context, AlarmStarter.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        Log.d("check", String.valueOf(context));
        try {
            Log.d("success", "startActicity1");

            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());
    }
}
