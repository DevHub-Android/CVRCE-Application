package com.devhub.use.cvrceapplication.FirebaseMessaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.devhub.use.cvrceapplication.R;
import com.devhub.use.cvrceapplication.webHome_mainActivity;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

   // private static final drawable R = ;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.e("NOTIDATA", remoteMessage.toString());
        Log.e("NOTIDATA1",remoteMessage.getData().get("message"));
        if(remoteMessage.getData()!=null)
        {
            showNotification(remoteMessage.getData().get("message"));
        }
     //   Log.e("HELLO",remoteMessage.getNotification().getBody());
    }

    private void showNotification(String message) {
        Intent intent = new Intent(this,webHome_mainActivity.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.e("SHOW NOTIFICATION",
                message);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"my_channel")

                .setContentTitle("CVRCE GRIEVANCE")
                .setContentText(message)
                .setSmallIcon(R.drawable.add)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel mChannel = new NotificationChannel("my_channel","my_notification",NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(mChannel);
        }

        manager.notify(0,builder.build());
    }
}
