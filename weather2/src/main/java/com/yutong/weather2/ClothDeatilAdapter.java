package com.yutong.weather2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yutong on 2016/7/3.
 */
public class ClothDeatilAdapter extends RecyclerView.Adapter<ClothDeatilAdapter.ViewHolder>{
    @Override
    public ClothDeatilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clothes_detail_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClothDeatilAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,price,describe;
        public View view;
        public ImageView clothesView;
        Context context;
        public ViewHolder(View v) {
            super(v);
            view = v;
            name = (TextView)v.findViewById(R.id.txt_name_clothes_detail);
            price = (TextView)v.findViewById(R.id.txt_price_clothes_detail);
            describe = (TextView)v.findViewById(R.id.txt_describe_clothes_detail);
            clothesView = (ImageView)v.findViewById(R.id.img_cond_threeday_detail);
        }
    }
}
