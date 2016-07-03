package com.yutong.weather2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yutong on 2016/6/30.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private String[][] forecastDataSet;
    public final static String DATE = "com.yutong.weather2.DATE";
    public final static String CONDITION = "com.yutong.weather2.CONDITION";
    public final static String TEMP_MAX = "com.yutong.weather2.TEMP_MAX";
    public final static String TEMP_MIN = "com.yutong.weather2.TEMP_MIN";
    public final static String CONDITION_CODE = "com.yutong.weather2.CONDITION_CODE";
    //private Bitmap imgaeCondtion;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date,condition,maxTemp,minTemp;
        public View view;
        public ImageView condView;
        Context context;
        public ViewHolder(View v) {
            super(v);
            view = v;
            date = (TextView) v.findViewById(R.id.text_date_threeday);
            condition = (TextView)v.findViewById(R.id.text_cond_threeday);
            maxTemp = (TextView)v.findViewById(R.id.text_max_temp_threeday);
            minTemp = (TextView)v.findViewById(R.id.text_min_temp_threeday);
            condView = (ImageView)v.findViewById(R.id.image_cond_threeday);
            context = itemView.getContext();
        }
    }

    public MyAdapter(String[][] DataSet) {
        forecastDataSet = DataSet;
        //imgaeCondtion = img;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.threeday_forecast_row, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        holder.date.setText(forecastDataSet[position][0]);
        holder.condition.setText(forecastDataSet[position][3]+"/"+forecastDataSet[position][4]);
        holder.maxTemp.setText(forecastDataSet[position][5]+(char) 0x00B0);
        holder.minTemp.setText(forecastDataSet[position][6]+(char) 0x00B0);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context,ClothesActivity.class);
                intent.putExtra(DATE,forecastDataSet[position][0]);
                intent.putExtra(CONDITION,forecastDataSet[position][3]+"/"+forecastDataSet[position][4]);
                intent.putExtra(TEMP_MAX,forecastDataSet[position][5]+(char) 0x00B0);
                intent.putExtra(TEMP_MIN,forecastDataSet[position][6]+(char) 0x00B0);
                intent.putExtra(CONDITION_CODE,forecastDataSet[position][1]);
                context.startActivity(intent);
            }
        });

        switch (forecastDataSet[position][1]){
            case "100":
                holder.condView.setImageResource(R.drawable.a100);
                break;
            case "101":
                holder.condView.setImageResource(R.drawable.a101);
                break;
            case "102":
                holder.condView.setImageResource(R.drawable.a103);
                break;
            case "103":
                holder.condView.setImageResource(R.drawable.a103);
                break;
            case "104":
                holder.condView.setImageResource(R.drawable.a104);
                break;
            case "200":
                holder.condView.setImageResource(R.drawable.a200);
                break;
            case "201":
                holder.condView.setImageResource(R.drawable.a201);
                break;
            case "202":
                holder.condView.setImageResource(R.drawable.a202);
                break;
            case "203":
                holder.condView.setImageResource(R.drawable.a203);
                break;
            case "204":
                holder.condView.setImageResource(R.drawable.a204);
                break;
            case "205":
                holder.condView.setImageResource(R.drawable.a205);
                break;
            case "206":
                holder.condView.setImageResource(R.drawable.a206);
                break;
            case "207":
                holder.condView.setImageResource(R.drawable.a207);
                break;
            case "208":
                holder.condView.setImageResource(R.drawable.a208);
                break;
            case "209":
                holder.condView.setImageResource(R.drawable.a209);
                break;
            case "210":
                holder.condView.setImageResource(R.drawable.a210);
                break;
            case "211":
                holder.condView.setImageResource(R.drawable.a211);
                break;
            case "212":
                holder.condView.setImageResource(R.drawable.a212);
                break;
            case "213":
                holder.condView.setImageResource(R.drawable.a213);
                break;
            case "300":
                holder.condView.setImageResource(R.drawable.a300);
                break;
            case "301":
                holder.condView.setImageResource(R.drawable.a301);
                break;
            case "302":
                holder.condView.setImageResource(R.drawable.a302);
                break;
            case "303":
                holder.condView.setImageResource(R.drawable.a303);
                break;
            case "304":
                holder.condView.setImageResource(R.drawable.a304);
                break;
            case "305":
                holder.condView.setImageResource(R.drawable.a305);
                break;
            case "306":
                holder.condView.setImageResource(R.drawable.a306);
                break;
            case "307":
                holder.condView.setImageResource(R.drawable.a307);
                break;
            case "308":
                holder.condView.setImageResource(R.drawable.a308);
                break;
            case "309":
                holder.condView.setImageResource(R.drawable.a309);
                break;
            case "310":
                holder.condView.setImageResource(R.drawable.a310);
                break;
            case "311":
                holder.condView.setImageResource(R.drawable.a311);
                break;
            case "312":
                holder.condView.setImageResource(R.drawable.a312);
                break;
            case "313":
                holder.condView.setImageResource(R.drawable.a313);
                break;
            case "400":
                holder.condView.setImageResource(R.drawable.a400);
                break;
            case "401":
                holder.condView.setImageResource(R.drawable.a401);
                break;
            case "402":
                holder.condView.setImageResource(R.drawable.a402);
                break;
            case "403":
                holder.condView.setImageResource(R.drawable.a403);
                break;
            case "404":
                holder.condView.setImageResource(R.drawable.a404);
                break;
            case "405":
                holder.condView.setImageResource(R.drawable.a405);
                break;
            case "406":
                holder.condView.setImageResource(R.drawable.a406);
                break;
            case "407":
                holder.condView.setImageResource(R.drawable.a407);
                break;
            case "500":
                holder.condView.setImageResource(R.drawable.a500);
                break;
            case "501":
                holder.condView.setImageResource(R.drawable.a501);
                break;
            case "502":
                holder.condView.setImageResource(R.drawable.a502);
                break;
            case "503":
                holder.condView.setImageResource(R.drawable.a503);
                break;
            case "504":
                holder.condView.setImageResource(R.drawable.a504);
                break;
            case "507":
                holder.condView.setImageResource(R.drawable.a507);
                break;
            case "508":
                holder.condView.setImageResource(R.drawable.a508);
                break;
            case "900":
                holder.condView.setImageResource(R.drawable.a900);
                break;
            case "901":
                holder.condView.setImageResource(R.drawable.a901);
                break;
            default:
                holder.condView.setImageResource(R.drawable.a999);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
