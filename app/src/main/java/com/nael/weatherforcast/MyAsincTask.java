package com.nael.weatherforcast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static java.lang.System.in;

/**
 * Created by Madanael on 24/03/2017.
 */

public class MyAsincTask extends AsyncTask<Object,Void,JSONObject> {

    TextView ville,date,temps,temp,vent,humidité;
    ImageView iconView;
    String URL;

    @Override
    protected JSONObject doInBackground(Object... params) {

        ville = (TextView) params[0];
        date = (TextView) params[1];
        temps = (TextView) params[2];
        temp = (TextView) params[3];
        vent = (TextView) params[4];
        humidité = (TextView) params[5];
        iconView = (ImageView) params[6];


        try {

            URL = "http://api.openweathermap.org/data/2.5/forecast?id=2996943&appid=b62d6e2a6803318ed3628853684f4114&units=metric&lang=fr";

            URL url = new URL(URL);

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

                ArrayList<Weather> previsions = new ArrayList<>();
                Weather weather;
                String temperature,windSpeed,humidity,city,weatherDescription,time,iconRef;

                //Parsage du flux
                JSONObject listElement,main,wind;

                JSONArray list = json.getJSONArray("list");

                for(int i=0; i<list.length();i++){
                    listElement = list.getJSONObject(i);

                    main = listElement.getJSONObject("main");
                    wind = listElement.getJSONObject("wind");

                    time = listElement.getString("dt_txt");
                    humidity = main.getString("humidity");
                    temperature = main.getString("temp");
                    windSpeed = wind.getString("speed");
                    city = "Lyon";
                    weatherDescription = listElement.getJSONArray("weather").getJSONObject(0).getString("description");
                    iconRef = listElement.getJSONArray("weather").getJSONObject(0).getString("icon");

                    weather = new Weather(temperature,windSpeed,humidity,city,weatherDescription,time);
                    weather.setIcon("http://openweathermap.org/img/w/"+iconRef+".png");

                    previsions.add(weather);
                }

                weather = previsions.get(0);

                //Mis à jours des textes views
                ville.setText(weather.getVille());
                date.setText(weather.getDate());
                temps.setText(weather.getWeatherDescription());
                temp.setText(weather.getTemp()+"°C");
                vent.setText("Vent: "+weather.getVent()+"km/h");
                humidité.setText("Humidity: "+weather.getHumidity()+"%");

                /*
                URL imageUrl = new URL(weather.getIcon());

                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iconView.setImageBitmap(bitmap);
                */

            } catch (JSONException e) {
                e.printStackTrace();
            } /*catch (IOException e){
                e.printStackTrace();
            }*/
        }

        super.onPostExecute(json);
    }
}
