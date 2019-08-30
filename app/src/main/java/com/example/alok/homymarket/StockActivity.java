package com.example.alok.homymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StockActivity extends AppCompatActivity {
    String id,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        pass=intent.getStringExtra("pass");

    }
}
