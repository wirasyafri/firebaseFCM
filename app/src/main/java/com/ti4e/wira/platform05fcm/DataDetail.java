package com.ti4e.wira.platform05fcm;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DataDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);

        TextView nama = (TextView) findViewById(R.id.namastc);
        ImageView test = (ImageView) findViewById(R.id.imageID);

        Bundle b = getIntent().getExtras();

        long a = b.getLong("id");

        //Gambar data = Gambar.dataGambar[(int) a];

        nama.setText(b.getString("url"));
        Bitmap aas;
        Picasso.get().load(b.getString("url")).into(test);
//        Bitmap lalal = Bitmap.createBitmap(Picasso.get().load(b.getString("url")).into(test));

    }
}
