package com.example.alok.homymarket;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button stockBtn,orderBtn,uploadBtn;
     String mid,mpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        stockBtn=findViewById(R.id.stockBtn);
        orderBtn=findViewById(R.id.orderBtn);
        uploadBtn=findViewById(R.id.uploadBtn);

        Intent a=getIntent();
        mid=a.getStringExtra("id");
        mpass=a.getStringExtra("pass");

        stockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,StockActivity.class);
                i.putExtra("pass",mpass);
                i.putExtra("id",mid);
                startActivity(i);

            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,OrderActivity.class);
                startActivity(i);

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,UploadActivity.class);
                i.putExtra("id",mid);
                startActivity(i);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if(item.getItemId()==R.id.changePass)
         {
             Intent password=new Intent(HomeActivity.this,PasswordChange.class);
             password.putExtra("pass",mpass);
             password.putExtra("id",mid);
             startActivity(password);


         }
        return true;
    }
}
