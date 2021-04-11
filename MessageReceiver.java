package com.example.broadcast;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.onlinelab.MainActivity;
import com.example.onlinelab.NotificationActivity;
import com.example.onlinelab.R;

public class MessageReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "2";

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String getMessage = intent.getStringExtra("msg");
        Toast.makeText(context,"Message Sent Successfully",Toast.LENGTH_SHORT).show();
        callNotification(context, getMessage);

        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void callNotification(Context context, String message) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Channel 2", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);

            Intent intent = new Intent(context, SendBroadcast.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,12,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder = new NotificationCompat.Builder(context, CHANNEL_ID);
            builder.setSmallIcon(R.drawable.ic_stat_name)
                    .setContentTitle("A new Message Arrived")
                    .setContentText(message)
                    .setContentIntent(pendingIntent);

            notificationManager.notify(1, builder.build());
        }
    }
}
