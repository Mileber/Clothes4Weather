package com.yutong.weathertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);

        String httpUrl = "https://api.heweather.com/x3/weather?cityid=CN101020100&key=52a6eb4fc76b47829bbe69b1b7197fa0";
        String jsonResult = request(httpUrl);
        //System.out.println(jsonResult);
        textView.setText(jsonResult);
    }

    public static String request(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead); sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) { e.printStackTrace(); }
        return result;
    }
}
