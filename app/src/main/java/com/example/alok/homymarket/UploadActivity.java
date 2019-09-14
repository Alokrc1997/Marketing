package com.example.alok.homymarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    EditText mTitle,mBase,mSelling,mItem,mdescrip;


    byte[] imageBytes;
    Button mUpload,pic1,pic2,pic3,pic4;
    String URL="https://homimarket.com/wp-content/Alok/data_upload.php";
    String title,base,selling,items,descrip;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    private Uri filePath1,filePath2,filePath3,filePath4;
    String mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        image=findViewById(R.id.uploadImage);
        mdescrip=findViewById(R.id.uploadDescription);
        mTitle=findViewById(R.id.uploadTitle);
        mBase=findViewById(R.id.uploadBase);
        mSelling=findViewById(R.id.uploadPrice);
        mItem=findViewById(R.id.uploadNo);
        mUpload=findViewById(R.id.uploadBtn);
        pic1=findViewById(R.id.pic1);
        pic2=findViewById(R.id.pic2);
        pic3=findViewById(R.id.pic3);
        pic4=findViewById(R.id.pic4);


        Intent intent=getIntent();
        mid=intent.getStringExtra("id");


        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Log.e("pic1","pic1");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                //showFileChooser();
            }


        });
        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Log.e("pic2","pic2");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);

                //showFileChooser();
            }


        });
        pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Log.e("pic3","pic3");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3);
                //showFileChooser();
            }


        });

        pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Log.e("pic4","pic4");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 4);
                //showFileChooser();
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
                        map.put("des",descrip);
                        //Log.e("aaa",getStringImage(filePath1));
                        map.put("image1",getStringImage(bitmap1));
                        map.put("image2",getStringImage(bitmap2));
                        map.put("image3",getStringImage(bitmap3));
                        map.put("image4",getStringImage(bitmap4));

                        return map;
                    }
                };
                sr.setShouldCache(false);
                rq.add(sr);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath1 = data.getData();
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                image.setImageBitmap(bitmap1);
                pic1.setText("Selected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath2 = data.getData();
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath2);
                image.setImageBitmap(bitmap2);
                pic2.setText("Selected");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath3 = data.getData();
            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath3);
                image.setImageBitmap(bitmap3);
                pic3.setText("Selected");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 4 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath4 = data.getData();
            try {
                bitmap4 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath4);
                image.setImageBitmap(bitmap4);
                pic4.setText("Selected");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
     imageBytes = baos.toByteArray();
     Log.e("alok",imageBytes.toString());



        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void getValues(){
        title=mTitle.getText().toString().trim();
        base=mBase.getText().toString().trim();
        selling=mSelling.getText().toString().trim();
        items=mItem.getText().toString().trim();
        descrip=mdescrip.getText().toString().trim();

    }



}
