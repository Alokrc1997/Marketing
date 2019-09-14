package com.example.alok.homymarket;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.ViewHolder> {
    Context mContext;
    String id,password;
    ArrayList<ModelOrder> lists;


    public AdapterOrder (ArrayList<ModelOrder> lists, Context mContext){
        this.mContext=mContext;
        this.lists=lists;


    }

    @Override
    public AdapterOrder.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pendcomplayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final ViewHolder viewHolder, final int i) {
        viewHolder.setData(lists.get(i));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView orderNo,title,size,itemNo;
        ImageView imageView;

        public ViewHolder( View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.pendcomtitle);
            orderNo=itemView.findViewById(R.id.pendcomOrder);
            size=itemView.findViewById(R.id.pendcomsize);
            itemNo=itemView.findViewById(R.id.pendcomno);
            imageView=itemView.findViewById(R.id.pendcompic);

        }
        void setData(ModelOrder model){
            title.setText(model.getTitle());
            orderNo.setText("#"+model.getId());
            size.setText(model.getSize());
            itemNo.setText(model.getItems());
            String link1=model.getLink();
            Picasso.get().load(link1).into(imageView);



        }
    }
}


