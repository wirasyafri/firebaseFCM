package com.ti4e.wira.platform05fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class MyNotificationManager {
    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context){
        mCtx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context){
        if(mInstance==null){
            mInstance= new MyNotificationManager(context);
        }
        return  mInstance;
    }

    public void displatNotification(String tittle,String body){
        //channel id diu=butuhkan untuk oreo keatas
        String CHANNEL_ID = "my_channel_01";


        Intent resulIntent = new Intent(mCtx,MainActivity.class);
        //pendinginten berfungsi untuk menjembatani third party untuk masuk ke activity aplikasi
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx,0,resulIntent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx,CHANNEL_ID).setSmallIcon(R.drawable.ic_stat_name).setContentTitle(tittle).setContentText(body).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);

        if(mNotifyMgr != null){
            mNotifyMgr.notify(1,mBuilder.build());
        }
    }


    public void displaypesan(String tittle,String body,String gambar,String nama){
        String CHANNEL_ID = "my_channel_01";

    //untuk memparsing nilai dari data json yang baru dikirim maka diperlukan function putextra ketika berpindah activity
        Intent resulIntent = new Intent(mCtx,pesan.class);
        resulIntent.putExtra("judul",tittle);
        resulIntent.putExtra("isi",body);
        resulIntent.putExtra("gambar",gambar);
        resulIntent.putExtra("nama",nama);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx,0,resulIntent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat
                .Builder(mCtx,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(tittle)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);

        if(mNotifyMgr != null){
            mNotifyMgr.notify(0,mBuilder.build());
        }
    }
}
