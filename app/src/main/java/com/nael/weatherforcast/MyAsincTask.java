package com.nael.weatherforcast;

import android.os.AsyncTask;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Madanael on 24/03/2017.
 */

public class MyAsincTask extends AsyncTask<Object,Void,String> {

    WebView webView;

    @Override
    protected String doInBackground(Object... params) {

        String res = null;
        try {
            URL url = new URL("api.openweathermap.org/data/2.5/forecast?q=Lyon,fr&appid=b62d6e2a6803318ed3628853684f4114");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                res = in.readLine();
                webView = (WebView) params[0];
                JSONObject data = new JSONObject(res);
                JSONArray array = data.getJSONArray("list");
                JSONObject temperature = array.getJSONObject(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        webView.loadUrl(s);
        super.onPostExecute(s);
    }
}
