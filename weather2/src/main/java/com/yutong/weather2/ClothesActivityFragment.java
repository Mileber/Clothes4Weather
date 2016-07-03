package com.yutong.weather2;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class ClothesActivityFragment extends Fragment {

    TextView mDate,mCondition,mTempMax,mTempMin;
    ImageView mCondView;

    private RecyclerView mClothesDetailRecycleView;
    private RecyclerView.Adapter mClothesDetailAdapter;
    private RecyclerView.LayoutManager mClothesDetailLayoutManager;

    public ClothesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clothes, container, false);
        //mDate = (TextView)root.findViewById(R.id.txt_date_threeday_detail);
        mCondition = (TextView)root.findViewById(R.id.txt_cond_threeday_detail);
        mTempMax = (TextView)root.findViewById(R.id.txt_tmp_max_threeday_detail);
        mTempMin = (TextView)root.findViewById(R.id.txt_tmp_min_threeday_detail);
        mCondView = (ImageView)root.findViewById(R.id.img_cond_threeday_detail);

        Intent intent = getActivity().getIntent();
        String date = intent.getStringExtra(MyAdapter.DATE);
        String condition = intent.getStringExtra(MyAdapter.CONDITION);
        String temp_max = intent.getStringExtra(MyAdapter.TEMP_MAX);
        String temp_min = intent.getStringExtra(MyAdapter.TEMP_MIN);
        String cond_code = intent.getStringExtra(MyAdapter.CONDITION_CODE);

        //mDate.setText(date);
        mCondition.setText(condition);
        mTempMax.setText(temp_max);
        mTempMin.setText(temp_min);

        switch (cond_code){
            case "100":
                mCondView.setImageResource(R.drawable.a100);
                break;
            case "101":
                mCondView.setImageResource(R.drawable.a101);
                break;
            case "102":
                mCondView.setImageResource(R.drawable.a103);
                break;
            case "103":
                mCondView.setImageResource(R.drawable.a103);
                break;
            case "104":
                mCondView.setImageResource(R.drawable.a104);
                break;
            case "200":
                mCondView.setImageResource(R.drawable.a200);
                break;
            case "201":
                mCondView.setImageResource(R.drawable.a201);
                break;
            case "202":
                mCondView.setImageResource(R.drawable.a202);
                break;
            case "203":
                mCondView.setImageResource(R.drawable.a203);
                break;
            case "204":
                mCondView.setImageResource(R.drawable.a204);
                break;
            case "205":
                mCondView.setImageResource(R.drawable.a205);
                break;
            case "206":
                mCondView.setImageResource(R.drawable.a206);
                break;
            case "207":
                mCondView.setImageResource(R.drawable.a207);
                break;
            case "208":
                mCondView.setImageResource(R.drawable.a208);
                break;
            case "209":
                mCondView.setImageResource(R.drawable.a209);
                break;
            case "210":
                mCondView.setImageResource(R.drawable.a210);
                break;
            case "211":
                mCondView.setImageResource(R.drawable.a211);
                break;
            case "212":
                mCondView.setImageResource(R.drawable.a212);
                break;
            case "213":
                mCondView.setImageResource(R.drawable.a213);
                break;
            case "300":
                mCondView.setImageResource(R.drawable.a300);
                break;
            case "301":
                mCondView.setImageResource(R.drawable.a301);
                break;
            case "302":
                mCondView.setImageResource(R.drawable.a302);
                break;
            case "303":
                mCondView.setImageResource(R.drawable.a303);
                break;
            case "304":
                mCondView.setImageResource(R.drawable.a304);
                break;
            case "305":
                mCondView.setImageResource(R.drawable.a305);
                break;
            case "306":
                mCondView.setImageResource(R.drawable.a306);
                break;
            case "307":
                mCondView.setImageResource(R.drawable.a307);
                break;
            case "308":
                mCondView.setImageResource(R.drawable.a308);
                break;
            case "309":
                mCondView.setImageResource(R.drawable.a309);
                break;
            case "310":
                mCondView.setImageResource(R.drawable.a310);
                break;
            case "311":
                mCondView.setImageResource(R.drawable.a311);
                break;
            case "312":
                mCondView.setImageResource(R.drawable.a312);
                break;
            case "313":
                mCondView.setImageResource(R.drawable.a313);
                break;
            case "400":
                mCondView.setImageResource(R.drawable.a400);
                break;
            case "401":
                mCondView.setImageResource(R.drawable.a401);
                break;
            case "402":
                mCondView.setImageResource(R.drawable.a402);
                break;
            case "403":
                mCondView.setImageResource(R.drawable.a403);
                break;
            case "404":
                mCondView.setImageResource(R.drawable.a404);
                break;
            case "405":
                mCondView.setImageResource(R.drawable.a405);
                break;
            case "406":
                mCondView.setImageResource(R.drawable.a406);
                break;
            case "407":
                mCondView.setImageResource(R.drawable.a407);
                break;
            case "500":
                mCondView.setImageResource(R.drawable.a500);
                break;
            case "501":
                mCondView.setImageResource(R.drawable.a501);
                break;
            case "502":
                mCondView.setImageResource(R.drawable.a502);
                break;
            case "503":
                mCondView.setImageResource(R.drawable.a503);
                break;
            case "504":
                mCondView.setImageResource(R.drawable.a504);
                break;
            case "507":
                mCondView.setImageResource(R.drawable.a507);
                break;
            case "508":
                mCondView.setImageResource(R.drawable.a508);
                break;
            case "900":
                mCondView.setImageResource(R.drawable.a900);
                break;
            case "901":
                mCondView.setImageResource(R.drawable.a901);
                break;
            default:
                mCondView.setImageResource(R.drawable.a999);
                break;

        }

        mClothesDetailRecycleView = (RecyclerView) root.findViewById(R.id.clothes_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mClothesDetailRecycleView.setHasFixedSize(true);

        // use a linear layout manager
        //mClothesDetailLayoutManager = new StaggeredGridLayoutManager(3,1);
        mClothesDetailLayoutManager = new LinearLayoutManager(root.getContext());
        mClothesDetailRecycleView.setLayoutManager(mClothesDetailLayoutManager);

        mClothesDetailAdapter = new ClothDeatilAdapter();
        mClothesDetailRecycleView.setAdapter(mClothesDetailAdapter);

        //Toast.makeText(getContext(),"hhh",Toast.LENGTH_SHORT);
        //new FetchClothesTask().execute();
        //Toast.makeText(getContext(),"hhh",Toast.LENGTH_SHORT);
        return root;
    }

    public class FetchClothesTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            String cloth_json = loadJSONFromAsset();
            return cloth_json;
        }

        protected void onPostExecute(String clothes){
            try {
                JSONObject jsonObject = new JSONObject(clothes);
                Toast.makeText(getContext(),jsonObject.toString(),Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //json转换为Map类型
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
