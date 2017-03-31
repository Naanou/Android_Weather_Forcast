package com.nael.weatherforcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView wv_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv_weather = (WebView) findViewById(R.id.wv_weather);

        new MyAsincTask().execute(wv_weather);
    }
}
