package com.ti4e.wira.platform05fcm;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class pesan extends AppCompatActivity {
    private FirebaseDatabase database;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("ImageSwap");
        mStorageRef = FirebaseStorage.getInstance().getReference("ImageSwap");
//        mDatabase= FirebaseDatabase.getInstance().getReference("ImageSwap");

    }
    @Override
    public void onResume(){
        super.onResume();
        TextView isi =(TextView) findViewById(R.id.isi);
        TextView judul =(TextView) findViewById(R.id.judul);
        ImageView test = (ImageView) findViewById(R.id.coba);
    //syntax dibawah ini berfungsi untuk mengambil nilai putextra dari class MyNotificationManager
    //untuk selanjutnya di tempatkan di objek yang ada di layout activity_pesan
        Intent ahay = getIntent();
        String pesan = ahay.getStringExtra("isi");
        String pengirim = ahay.getStringExtra("judul");
        String gambar = ahay.getStringExtra("gambar");
        String nama = ahay.getStringExtra("nama");

        writeNewGambar("data "+nama,nama,gambar);
        Picasso.get().load(gambar).into(test);
//
//        isi.setText(pesan);
//        judul.setText(pengirim);

    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void writeNewGambar(String userId, String name, String url) {
        Gambar yeye = new Gambar(name, url);

        mDatabase.child("").child(userId).setValue(yeye);
    }


}
