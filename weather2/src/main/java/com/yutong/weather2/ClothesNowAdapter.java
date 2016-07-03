package com.yutong.weather2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yutong on 2016/7/2.
 */
public class ClothesNowAdapter extends RecyclerView.Adapter<ClothesNowAdapter.ViewHolder> {
    public String clothes;
    public String[][] chosedClothes;
    public String temperature;

    public ClothesNowAdapter(String clothes_json,String tmp) {
        clothes = clothes_json;
        temperature = tmp;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView_clothes_now;
        public ViewHolder(View v) {
            super(v);
            imageView_clothes_now = (ImageView)v.findViewById(R.id.image_clothes_now);
        }

        @Override
        public void onClick(View view) {
            //TODO:点击后跳转服装链接
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clothes_row, parent, false);
        Toast.makeText(v.getContext(),"Adapter",Toast.LENGTH_LONG).show();
        try {
            chosedClothes = chooseClothes(clothes,temperature);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //new FetchClothesPicTask().execute(chosedClothes[position][1]);
        //Picasso.with(holder.imageView_clothes_now.getContext()).load(chosedClothes[position][1]).into(holder.imageView_clothes_now);
        holder.imageView_clothes_now.setImageResource(R.drawable.clothes);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public String[][] chooseClothes(String clothes_json, String tmp) throws JSONException {
        String[][] clothUrl = new String[5][3];
        int k=0;

        JSONObject jsonObject1 = new JSONObject(clothes_json);
        JSONArray records = jsonObject1.optJSONArray("RECORDS");
        for(int i=0;i<records.length();i++){
            JSONObject jsonObject = records.optJSONObject(i);
            String clothname = jsonObject.optString("clothname");
            String picurl = jsonObject.optString("picurl");
            String clothurl = jsonObject.optString("clothurl");

            if(Integer.parseInt(tmp)>25){
                if(clothname.contains("裙短")){
                    clothUrl[k][0] = clothname;
                    clothUrl[k][1] = picurl;
                    clothUrl[k][2] = clothurl;
                    k++;
                }
            }

        }
        return clothUrl;
    }

    public class FetchClothesPicTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            String picUrl = strings[0];
            Bitmap pic = null;

            try {
                InputStream is = new java.net.URL(picUrl).openStream();
                pic = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pic;
        }

        protected void onPostExecute(Bitmap pic){
            //holder.imageView_clothes_now.setImageBitmap(pic);
        }
    }

}
