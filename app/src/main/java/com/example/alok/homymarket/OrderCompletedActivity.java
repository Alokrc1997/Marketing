package com.example.alok.homymarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderCompletedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String id;
    String url="https://homimarket.com/wp-content/Alok/orders.php";
    ArrayList<ModelOrder> list=new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed);
        recyclerView=findViewById(R.id.recycleCompleted);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");

        RequestQueue rq= Volley.newRequestQueue(OrderCompletedActivity.this);
        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo=new JSONObject(response);
                    int success=jo.getInt("success");
                    {
                        if(success==0)
                        {
                            Toast.makeText(OrderCompletedActivity.this,jo.getString("message") , Toast.LENGTH_SHORT).show();
                        }else{
                            JSONArray a=jo.getJSONArray("details");
                            list.clear();

                            for(int i=0;i<a.length();i++){

                                JSONObject j=a.getJSONObject(i);
                                if(j.getString("status").equals("completed")) {

                                    ModelOrder model = new ModelOrder(j.getString("id"), j.getString("items"), j.getString("link"),
                                            j.getString("title"), j.getString("price"),j.getString("size"));
                                    list.add(model);
                                }

                            }

                            LinearLayoutManager llm=new LinearLayoutManager(OrderCompletedActivity.this);
                            llm.setOrientation(RecyclerView.VERTICAL);
                            recyclerView.setLayoutManager(llm);
                            AdapterOrder adapter=new AdapterOrder(list,OrderCompletedActivity.this);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OrderCompletedActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("id",id);
                return map;
            }
        };
        sr.setShouldCache(false);
        rq.add(sr);

    }
}
