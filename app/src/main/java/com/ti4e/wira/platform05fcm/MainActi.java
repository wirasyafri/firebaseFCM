package com.ti4e.wira.platform05fcm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActi extends AppCompatActivity {
    ImageView image,image2;
    private static final int SELECT_PICTURE = 1;
    public String selectedImagePath = "";
    Bitmap imgFileCOre,imgFileRepalcer;
    int actResult =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maina);
        image = (ImageView) findViewById(R.id.imageLayout);
        image2 = (ImageView) findViewById(R.id.imageLayout2);

    }

    public void prosesEmoji(View v)
    {
        Bitmap newBitmap = imgFileCOre;
        Bitmap emojier = imgFileRepalcer;
        EmojifierMadeByNafi emoji = new EmojifierMadeByNafi();
        image.setImageBitmap(emoji.detectFaces(getApplicationContext(),newBitmap,emojier));
    }

    public void getGambars(View v)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                    Uri selectedImageUri = data.getData();
                    image2.setVisibility(View.VISIBLE);
                    image2.setImageURI(selectedImageUri);
                    try {
                        imgFileRepalcer = MediaStore.Images.Media.getBitmap( getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(this,selectedImageUri.getEncodedPath(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
