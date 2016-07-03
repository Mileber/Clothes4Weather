package com.yutong.weather2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    PullToRefreshView mPullToRefreshView;
    //TextView textView;
    String status;
    private View root;
    private RecyclerView mRecyclerView, mClothesNowRecycleView;
    private RecyclerView.Adapter mAdapter, mClothesNowAdapter;
    private String clothes_json;
    private RecyclerView.LayoutManager mLayoutManager, mClothesNowLayoutManager;
    private String[][] forecastDataSet;
    String code_d_cond_daily_forecast;
    private TextView textView_tmp_now,textView_cond_now,textView_locate_now;
    private ImageView imageView_cond_now;
    private String tmp_now;
    //private Bitmap img;
    final String REQUEST_URL = " https://api.heweather.com/x3/weather?cityid=CN101020100&key=52a6eb4fc76b47829bbe69b1b7197fa0";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        //textView = (TextView) root.findViewById(R.id.textview);
        forecastDataSet = new String[10][10];
        textView_tmp_now = (TextView)root.findViewById(R.id.txt_tmp_now);
        textView_cond_now = (TextView)root.findViewById(R.id.txt_locate_now);
        textView_locate_now = (TextView)root.findViewById(R.id.txt_locate_now);
        imageView_cond_now = (ImageView)root.findViewById(R.id.img_cond_now);
        //final FoldingCell fc = (FoldingCell)root.findViewById(R.id.folding_cell);
        /*fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });*/
        //fc.initialize(1000, Color.DKGRAY, 2);
        /*
        mClothesNowRecycleView = (RecyclerView) root.findViewById(R.id.clother_now_recycleview);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mClothesNowRecycleView.setLayoutManager(mClothesNowLayoutManager);

        Toast.makeText(root.getContext(),"1",Toast.LENGTH_SHORT).show();
        clothes_json = loadJSONFromAsset();
        Toast.makeText(root.getContext(),"2",Toast.LENGTH_SHORT).show();
        mClothesNowAdapter = new ClothesNowAdapter(clothes_json,tmp_now);
        mClothesNowRecycleView.setAdapter(mClothesNowAdapter);
        Toast.makeText(root.getContext(),"3",Toast.LENGTH_SHORT).show();
        */
        new FetchWeatherTask().execute(REQUEST_URL);
        mPullToRefreshView = (PullToRefreshView) root.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        new FetchWeatherTask().execute(REQUEST_URL);
                    }
                }, 1000);
            }
        });

        return root;
    }

    public class FetchWeatherTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String jsonResult = request(strings[0]);
            //publishProgress();
            return jsonResult;
        }

        public String request(String httpUrl) {
            BufferedReader reader = null;
            String result = null;
            StringBuffer sbf = new StringBuffer();
            //JSONObject jsonObject = null;
            try {
                URL url = new URL(httpUrl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream is = connection.getInputStream();
                //JSONObject jsonObject1 = new JSONObject((Map) is);
                //jsonObject = jsonObject1.optJSONObject("HeWeather data service 3.0");


                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead); sbf.append("\r\n");
                }
                reader.close();
                result = sbf.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(String result) {
            //textView.setText(result);

            try {
                Map<String,String> map = toMap(result);
                if(map!=null) {
                    for (Map.Entry<String,String> entry : map.entrySet()) {
                        JSONArray jsonArray = new JSONArray(entry.getValue());
                        if(jsonArray!=null) Toast.makeText(getContext(),"天气信息刷新成功",Toast.LENGTH_SHORT).show();
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.optJSONObject(i);
                            JSONObject basic = jsonObject.optJSONObject("basic");
                            String location = basic.optString("city");
                            //Toast.makeText(getContext(),basic.toString(),Toast.LENGTH_LONG).show();
                            JSONObject now = jsonObject.optJSONObject("now");
                            JSONArray daily_forecast = jsonObject.optJSONArray("daily_forecast");       //7天的天气预报
                            JSONObject cond_now = now.optJSONObject("cond");        //当前的天气
                            tmp_now = now.optString("tmp");                  //当前的温度
                            String code_cond_now = cond_now.optString("code");      //当前天气图标代码
                            String txt_cond_now = cond_now.optString("txt");        //当前天气文字
                            textView_tmp_now.setText(tmp_now+(char) 0x00B0);
                            textView_cond_now.setText(txt_cond_now);
                            textView_locate_now.setText(location);
                            switch (code_cond_now){
                                case "100":
                                    imageView_cond_now.setImageResource(R.drawable.a100);
                                    break;
                                case "101":
                                    imageView_cond_now.setImageResource(R.drawable.a101);
                                    break;
                                case "102":
                                    imageView_cond_now.setImageResource(R.drawable.a103);
                                    break;
                                case "103":
                                    imageView_cond_now.setImageResource(R.drawable.a103);
                                    break;
                                case "104":
                                    imageView_cond_now.setImageResource(R.drawable.a104);
                                    break;
                                case "200":
                                    imageView_cond_now.setImageResource(R.drawable.a200);
                                    break;
                                case "201":
                                    imageView_cond_now.setImageResource(R.drawable.a201);
                                    break;
                                case "202":
                                    imageView_cond_now.setImageResource(R.drawable.a202);
                                    break;
                                case "203":
                                    imageView_cond_now.setImageResource(R.drawable.a203);
                                    break;
                                case "204":
                                    imageView_cond_now.setImageResource(R.drawable.a204);
                                    break;
                                case "205":
                                    imageView_cond_now.setImageResource(R.drawable.a205);
                                    break;
                                case "206":
                                    imageView_cond_now.setImageResource(R.drawable.a206);
                                    break;
                                case "207":
                                    imageView_cond_now.setImageResource(R.drawable.a207);
                                    break;
                                case "208":
                                    imageView_cond_now.setImageResource(R.drawable.a208);
                                    break;
                                case "209":
                                    imageView_cond_now.setImageResource(R.drawable.a209);
                                    break;
                                case "210":
                                    imageView_cond_now.setImageResource(R.drawable.a210);
                                    break;
                                case "211":
                                    imageView_cond_now.setImageResource(R.drawable.a211);
                                    break;
                                case "212":
                                    imageView_cond_now.setImageResource(R.drawable.a212);
                                    break;
                                case "213":
                                    imageView_cond_now.setImageResource(R.drawable.a213);
                                    break;
                                case "300":
                                    imageView_cond_now.setImageResource(R.drawable.a300);
                                    break;
                                case "301":
                                    imageView_cond_now.setImageResource(R.drawable.a301);
                                    break;
                                case "302":
                                    imageView_cond_now.setImageResource(R.drawable.a302);
                                    break;
                                case "303":
                                    imageView_cond_now.setImageResource(R.drawable.a303);
                                    break;
                                case "304":
                                    imageView_cond_now.setImageResource(R.drawable.a304);
                                    break;
                                case "305":
                                    imageView_cond_now.setImageResource(R.drawable.a305);
                                    break;
                                case "306":
                                    imageView_cond_now.setImageResource(R.drawable.a306);
                                    break;
                                case "307":
                                    imageView_cond_now.setImageResource(R.drawable.a307);
                                    break;
                                case "308":
                                    imageView_cond_now.setImageResource(R.drawable.a308);
                                    break;
                                case "309":
                                    imageView_cond_now.setImageResource(R.drawable.a309);
                                    break;
                                case "310":
                                    imageView_cond_now.setImageResource(R.drawable.a310);
                                    break;
                                case "311":
                                    imageView_cond_now.setImageResource(R.drawable.a311);
                                    break;
                                case "312":
                                    imageView_cond_now.setImageResource(R.drawable.a312);
                                    break;
                                case "313":
                                    imageView_cond_now.setImageResource(R.drawable.a313);
                                    break;
                                case "400":
                                    imageView_cond_now.setImageResource(R.drawable.a400);
                                    break;
                                case "401":
                                    imageView_cond_now.setImageResource(R.drawable.a401);
                                    break;
                                case "402":
                                    imageView_cond_now.setImageResource(R.drawable.a402);
                                    break;
                                case "403":
                                    imageView_cond_now.setImageResource(R.drawable.a403);
                                    break;
                                case "404":
                                    imageView_cond_now.setImageResource(R.drawable.a404);
                                    break;
                                case "405":
                                    imageView_cond_now.setImageResource(R.drawable.a405);
                                    break;
                                case "406":
                                    imageView_cond_now.setImageResource(R.drawable.a406);
                                    break;
                                case "407":
                                    imageView_cond_now.setImageResource(R.drawable.a407);
                                    break;
                                case "500":
                                    imageView_cond_now.setImageResource(R.drawable.a500);
                                    break;
                                case "501":
                                    imageView_cond_now.setImageResource(R.drawable.a501);
                                    break;
                                case "502":
                                    imageView_cond_now.setImageResource(R.drawable.a502);
                                    break;
                                case "503":
                                    imageView_cond_now.setImageResource(R.drawable.a503);
                                    break;
                                case "504":
                                    imageView_cond_now.setImageResource(R.drawable.a504);
                                    break;
                                case "507":
                                    imageView_cond_now.setImageResource(R.drawable.a507);
                                    break;
                                case "508":
                                    imageView_cond_now.setImageResource(R.drawable.a508);
                                    break;
                                case "900":
                                    imageView_cond_now.setImageResource(R.drawable.a900);
                                    break;
                                case "901":
                                    imageView_cond_now.setImageResource(R.drawable.a901);
                                    break;
                                default:
                                    imageView_cond_now.setImageResource(R.drawable.a999);
                                    break;

                            }
                            for(int j=0;j<daily_forecast.length();j++){
                                //对7天天气的遍历
                                JSONObject jsonObject1 = daily_forecast.getJSONObject(j);
                                //Toast.makeText(getContext(),jsonObject1.toString(),Toast.LENGTH_LONG).show();
                                if(jsonObject1!=null){
                                    String date = jsonObject1.optString("date");
                                    //Toast.makeText(getContext(),date,Toast.LENGTH_SHORT).show();
                                    forecastDataSet[j][0] = date;
                                    JSONObject cond_daily_forecast = jsonObject1.optJSONObject("cond");
                                    if(cond_daily_forecast!=null){
                                        //天气状况
                                        code_d_cond_daily_forecast = cond_daily_forecast.optString("code_d");
                                        String code_n_cond_daily_forecast = cond_daily_forecast.optString("code_n");
                                        String txt_d_cond_daily_forecast = cond_daily_forecast.optString("txt_d");
                                        String txt_n_cond_daily_forecast = cond_daily_forecast.optString("txt_n");
                                        forecastDataSet[j][1] = code_d_cond_daily_forecast;
                                        //Toast.makeText(getContext(),code_d_cond_daily_forecast,Toast.LENGTH_LONG).show();
                                        forecastDataSet[j][2] = code_n_cond_daily_forecast;
                                        forecastDataSet[j][3] = txt_d_cond_daily_forecast;
                                        forecastDataSet[j][4] = txt_n_cond_daily_forecast;
                                    }
                                    JSONObject temp_daily_forecast = jsonObject1.optJSONObject("tmp");
                                    //Toast.makeText(getContext(),temp_daily_forecast.toString(),Toast.LENGTH_LONG).show();
                                    if(temp_daily_forecast!=null){
                                        //最高与最低温度
                                        String max_temp_daily_forecast = temp_daily_forecast.optString("max");
                                        String min_temp_daily_forecast = temp_daily_forecast.optString("min");
                                        forecastDataSet[j][5] = max_temp_daily_forecast;
                                        forecastDataSet[j][6] = min_temp_daily_forecast;
                                    }
                                    /*
                                    for(int k=0;k<6;k++){
                                        Toast.makeText(getContext(),forecastDataSet[j][k],Toast.LENGTH_LONG).show();
                                    }
                                    */
                                }
                            }
                        }
                    }
                }

                mRecyclerView = (RecyclerView) root.findViewById(R.id.threeday_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(root.getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);

                mAdapter = new MyAdapter(forecastDataSet);
                mRecyclerView.setAdapter(mAdapter);

                //mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getContext()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public Map toMap(String jsonString) throws JSONException {

            JSONObject jsonObject = new JSONObject(jsonString);

            Map result = new HashMap();
            Iterator iterator = jsonObject.keys();
            String key = null;
            String value = null;

            while (iterator.hasNext()) {
                key = (String) iterator.next();
                value = jsonObject.getString(key);
                result.put(key, value);

            }
            return result;

        }
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getActivity().getAssets().open("clothinfo.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
