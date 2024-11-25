package com.repidot.slythmclient;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.auto_start), Toast.LENGTH_SHORT).show();
        Log.d("success", "onRecieve");

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("success", "startActicity");
            context.startActivity(i);
            return;
        }
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE);

        try {
            Log.d("success", "startActicity");
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());
    }
}
