package com.example.alok.homymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    String id;
    Button comp,pend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        comp=findViewById(R.id.completed);
        pend=findViewById(R.id.pending);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderActivity.this,OrderCompletedActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        pend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderActivity.this,OrderPendingActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });





    }
}
