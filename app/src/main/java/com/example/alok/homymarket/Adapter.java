package com.example.alok.homymarket;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context mContext;
    String id,password;
    ArrayList<Model> lists;


    public Adapter (ArrayList<Model> lists, Context mContext, String id, String password){
        this.mContext=mContext;
        this.lists=lists;
        this.id=id;
        this.password=password;
        Log.e("aaaa",lists.toString());

    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_layput,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.setData(lists.get(i));
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent =new Intent(mContext,Main3Activity.class);
              //  intent.putExtra("exam",i);
              //  mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,base,sp;
        ImageView pic;
        Button update,dec,inc;
        EditText no;
        CardView card;
        public ViewHolder( View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.single_title);
            base=itemView.findViewById(R.id.single_base);
            sp=itemView.findViewById(R.id.single_selling);
            pic=itemView.findViewById(R.id.single_image);
            dec=itemView.findViewById(R.id.single_dec);
            inc=itemView.findViewById(R.id.single_inc);
            update=itemView.findViewById(R.id.single_update);
            no=itemView.findViewById(R.id.single_items);
            card=itemView.findViewById(R.id.card);
        }
        void setData(Model model){
            title.setText(model.getTitle());
            base.setText(model.getBase());
            sp.setText(model.getSelling());
            no.setText(model.getItems());
            String link=model.getLink();
            Log.e("aaa",link);
            Picasso.get().load(link).into(pic);




        }
    }
}
