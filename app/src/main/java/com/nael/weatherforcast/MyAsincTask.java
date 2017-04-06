package com.nael.weatherforcast;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by Madanael on 24/03/2017.
 */

public class MyAsincTask extends AsyncTask<Object,Void,JSONObject> {

    TextView ville,date,temps,temp,vent,humidité;

    @Override
    protected JSONObject doInBackground(Object... params) {

        ville = (TextView) params[0];
        date = (TextView) params[1];
        temps = (TextView) params[2];
        temp = (TextView) params[3];
        vent = (TextView) params[4];
        humidité = (TextView) params[5];


        try {

            URL url = new URL("api.openweathermap.org/data/2.5/forecast?id=2996943&appid=b62d6e2a6803318ed3628853684f4114&units=metric&lang=fr");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder sb = new StringBuilder();

                String line;

                while((line = in.readLine())!=null){
                    sb.append(line+"\n");
                }

                in.close();

                JSONObject json = new JSONObject(sb.toString());

                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(JSONObject json) {

        if(json == null){
            temps.setText("Erreur de chargement de la météo");
        }
        else {
            try {

                Prevision prv = new Prevision();
                Weather weather;

                //Parsage du flux
                JSONObject listElement,main,wind;

                JSONArray list = new JSONArray(json.getJSONArray("list"));

                for(int i=0; i<list.length();i++){
                    listElement = list.getJSONObject(i);

                    main = listElement.getJSONObject("main");
                    wind = listElement.getJSONObject("wind");

                    weather = new Weather();
                    weather.setDate(listElement.getString("dt_txt"));
                    weather.setHumidity(main.getInt("humidity"));
                    weather.setTemp(main.getDouble("temp"));
                    weather.setVent(wind.getDouble("speed"));
                    weather.setVille("Lyon");
                    weather.setWeatherDescription(listElement.getJSONArray("weather").getJSONObject(0).getString("description"));

                    prv.previsions.add(weather);
                }

                weather = prv.previsions.get(0);
                //Mis à jours des textes views
                ville.setText(weather.getVille());
                date.setText(weather.getDate());
                temps.setText(weather.getWeatherDescription());
                this.temp.setText((int) weather.getTemp());
                vent.setText((int) weather.getVent());
                humidité.setText((int) weather.getHumidity());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        super.onPostExecute(json);
    }
}
