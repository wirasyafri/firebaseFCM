package com.ti4e.wira.platform05fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
    public void clickMasuk(View v)
    {
        Intent in = new Intent(this,MainListActivity.class);
        startActivity(in);
    }


}
