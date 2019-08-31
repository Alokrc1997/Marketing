package com.example.alok.homymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PasswordChange extends AppCompatActivity {
    String id,oldpass,newpass;
    EditText oldP,newP;
    Button change;
    String url="https://homimarket.com/wp-content/Alok/change_password.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        oldpass=i.getStringExtra("pass");

        oldP=findViewById(R.id.oldPass);
        newP=findViewById(R.id.newPass);
        change=findViewById(R.id.changeButton);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpass=newP.getText().toString().trim();
                if(!oldpass.equals(oldP.getText().toString().trim())){
                    Toast.makeText(PasswordChange.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
                else if((oldP.getText().toString().trim()).isEmpty()||newpass.isEmpty()){
                    Toast.makeText(PasswordChange.this, "Please fill all entries", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    RequestQueue rq= Volley.newRequestQueue(PasswordChange.this);
                    StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("true")){
                                oldpass=newpass;
                                Toast.makeText(PasswordChange.this, "Password changed successfully", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(PasswordChange.this, "Unable to change Password right now", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(PasswordChange.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> map=new HashMap<>();
                            map.put("id",id);
                            map.put("pass",newpass);
                            return map;
                        }
                    };
                    rq.add(sr);
                }

            }
        });



    }
}
