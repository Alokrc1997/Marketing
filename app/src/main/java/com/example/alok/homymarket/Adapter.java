package com.example.alok.homymarket;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context mContext;
    String id,password;
    ArrayList<Model> lists;
    ArrayList<String> images;


    public Adapter (ArrayList<Model> lists, Context mContext, String id, String password){
        this.mContext=mContext;
        this.lists=lists;
        this.id=id;
        this.password=password;

    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_layput,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.setData(lists.get(i));
        viewHolder.a=Integer.parseInt(lists.get(i).getItems());
        viewHolder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.a>0)
              viewHolder.no.setText(Integer.toString(--viewHolder.a));
            }
        });
        viewHolder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.no.setText(Integer.toString(++viewHolder.a));


            }
        });

        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 viewHolder.m=lists.get(i).getId();
                 viewHolder.n=Integer.toString(viewHolder.a);

                String url="https://homimarket.com/wp-content/Alok/update_item.php";
                RequestQueue rq= Volley.newRequestQueue(mContext);
                StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("true")){
                            Toast.makeText(mContext, "ItemUpdated", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map=new HashMap<>();
                        map.put("table",id);
                        Log.e("check",viewHolder.m);
                        map.put("id",viewHolder.m);
                        map.put("item",viewHolder.n);

                        return map;
                    }
                };
                rq.add(sr);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,base,sp,des;
        ImageView pic1,pic2,pic3,pic4;
        Button update,dec,inc;
        TextView no;
        CardView card;
        ViewPager pager;
        LinearLayout linearLayout;
        private ImageView[] dots;

        int dots_count=4;

        RelativeLayout relativeLayout;
        int a;
        String m,n;

        public ViewHolder( View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.single_title);
            base=itemView.findViewById(R.id.single_base);
            sp=itemView.findViewById(R.id.single_selling);
            pic1=itemView.findViewById(R.id.single_image1);
            pic2=itemView.findViewById(R.id.single_image2);
            pic3=itemView.findViewById(R.id.single_image3);
            pic4=itemView.findViewById(R.id.single_image4);

            dec=itemView.findViewById(R.id.single_dec);
            inc=itemView.findViewById(R.id.single_inc);
            update=itemView.findViewById(R.id.single_update);
            no=itemView.findViewById(R.id.single_items);
            card=itemView.findViewById(R.id.card);
            images=new ArrayList<>();
            des=itemView.findViewById(R.id.des);
        }
        void setData(Model model){
            title.setText(model.getTitle());
            base.setText(model.getBase());
            des.setText(model.getDescp());
            sp.setText(model.getSelling());
            no.setText(model.getItems());
            String link1=model.getLink1();
            String link2=model.getLink2();
            String link3=model.getLink3();
            String link4=model.getLink4();
            Picasso.get().load(link1).into(pic1);
            Picasso.get().load(link2).into(pic2);
            Picasso.get().load(link3).into(pic3);
            Picasso.get().load(link4).into(pic4);


        }
    }
}
