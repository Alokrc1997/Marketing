package com.example.alok.homymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.List;
import java.util.Map;

public class StockActivity extends AppCompatActivity {
    String id,pass;
    RecyclerView recyclerView;
    ArrayList<Model> list=new ArrayList<>() ;
    String url="https://homimarket.com/wp-content/Alok/fetch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        recyclerView=findViewById(R.id.recycle);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        pass=intent.getStringExtra("pass");

        RequestQueue rq= Volley.newRequestQueue(StockActivity.this);

        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo=new JSONObject(response);
                    int success=jo.getInt("success");
                    {
                        if(success==0)
                        {
                            Toast.makeText(StockActivity.this,jo.getString("message") , Toast.LENGTH_SHORT).show();
                        }else{
                            JSONArray a=jo.getJSONArray("details");

                            for(int i=0;i<a.length();i++){

                                JSONObject j=a.getJSONObject(i);
                                Model model=new Model(j.getString("title"),j.getString("base"),j.getString("selling"),
                                        j.getString("id"),j.getString("link"),j.getString("noItem"));
                                list.add(model);

                            }

                            LinearLayoutManager llm=new LinearLayoutManager(StockActivity.this);
                            llm.setOrientation(LinearLayout.VERTICAL);
                            recyclerView.setLayoutManager(llm);
                            Adapter adapter=new Adapter(list,StockActivity.this,id,pass);
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

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("table",id);

                return map;
            }
        };
        rq.add(sr);



    }
}
