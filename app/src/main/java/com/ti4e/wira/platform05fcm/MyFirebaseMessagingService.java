package com.ti4e.wira.platform05fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM_GUE";
    String judul,isi,gambar,nama;


   // @Override
   // public void onNewToken(String s) {
    //    super.onNewToken(s);
    //    Log.e("NEW_TOKEN",s);
    //}
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//
//        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());
//
//    }

    //notif icon
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//
//        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());
//
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG,"Pesennya bang: " + remoteMessage.getData().get("pesan"));
//        }
//
//        MyNotificationManager.getInstance(this).displatNotification(
//                remoteMessage.getData().get("body"),
//                  remoteMessage.getData().get("title")
//        );
//    }

    //buka pesan
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG,"Pesennya bang: " + remoteMessage.getData());
        //code dibawah ini berfungsi untuk mengambil nilai data dari json untuk selanjutnya dimasukkan kedalam parameter di syntax terbawah
            judul =remoteMessage.getData().get("title");
            isi = remoteMessage.getData().get("body");
            gambar = remoteMessage.getData().get("gambar");
            nama =remoteMessage.getData().get("nama");
        }

        MyNotificationManager.getInstance(this).displaypesan(judul,isi,gambar,nama);
    }


}

