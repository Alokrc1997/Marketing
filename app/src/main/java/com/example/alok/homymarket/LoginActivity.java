package com.example.alok.homymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    TextView id,password;
    Button button;
     String mid;
    String mpass;
    String url="https://homimarket.com/wp-content/Alok/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id=findViewById(R.id.loginId);
        password=findViewById(R.id.loginPass);
        button=findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mid=id.getText().toString().trim();
                mpass=password.getText().toString().trim();
                if(mid.isEmpty()||mpass.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Enter the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login();

                }
            }
        });

    }

    private void login(){

        RequestQueue rq= Volley.newRequestQueue(LoginActivity.this);

        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String Response=jsonObject.getString("response");
                    if(Response.equals("true"))
                    {
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(i);
                    }
                    else if(Response.equals("false"))
                    {
                        Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map =new HashMap<>();
                map.put("username",mid);
                map.put("password",mpass);

                return map;
            }
        };
        rq.add(sr);



    }
}
