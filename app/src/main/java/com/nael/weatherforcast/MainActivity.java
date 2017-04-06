package com.nael.weatherforcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ville,date,temps,temp,vent,humidité;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ville = (TextView) findViewById(R.id.ville);
        date = (TextView) findViewById(R.id.time);
        temps = (TextView) findViewById(R.id.temps);
        temp = (TextView) findViewById(R.id.temperature);
        vent = (TextView) findViewById(R.id.vent);
        humidité = (TextView) findViewById(R.id.humidité);

        new MyAsincTask().execute(ville,date,temps,temp,vent,humidité);
    }


}
