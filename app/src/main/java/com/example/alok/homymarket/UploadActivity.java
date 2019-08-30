package com.example.alok.homymarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {
    ImageView image;
    private int PICK_IMAGE_REQUEST = 1;
    EditText mTitle,mBase,mSelling,mItem;
    Button mUpload;
    String URL="https://homimarket.com/wp-content/Alok/data_upload.php";
    String title,base,selling,items;
    private Bitmap bitmap;
    private Uri filePath;
    String mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        image=findViewById(R.id.uploadImage);
        mTitle=findViewById(R.id.uploadTitle);
        mBase=findViewById(R.id.uploadBase);
        mSelling=findViewById(R.id.uploadPrice);
        mItem=findViewById(R.id.uploadNo);
        mUpload=findViewById(R.id.uploadBtn);
        Intent intent=getIntent();
        mid=intent.getStringExtra("id");


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }


        });

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();


                RequestQueue rq= Volley.newRequestQueue(UploadActivity.this);

                StringRequest sr=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("aaa",response);

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("response");
                            Toast.makeText(UploadActivity.this, Response, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();


                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UploadActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> map=new HashMap<>();
                        map.put("title",title);
                        map.put("base",base);
                        map.put("selling",selling);
                        map.put("items",items);
                        map.put("table",mid);
                        map.put("image",getStringImage(bitmap));

                        return map;
                    }
                };
                rq.add(sr);

            }
        });
    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }

    private void getValues(){
        title=mTitle.getText().toString().trim();
        base=mBase.getText().toString().trim();
        selling=mSelling.getText().toString().trim();
        items=mItem.getText().toString().trim();

    }

}
