package com.nael.weatherforcast;

import java.util.Date;

/**
 * Created by Madanael on 31/03/2017.
 */

public class Weather {

    private double temperature,vent,humidity;
    private String ville,weatherDescription;
    private String date;

    public Weather(double t, double vt, double h,String v,String wd, String d) {
        temperature=t;
        vent=vt;
        humidity=h;
        ville=v;
        weatherDescription=wd;
        date=d;

    }

    public Weather(){
        ville = null;
        weatherDescription = null;
        date = null;
    }

    //getters
    public double getTemp(){
        return temperature;
    }
    public double getVent(){
        return vent;
    }
    public double getHumidity(){
        return humidity;
    }
    public String getVille(){
        return ville;
    }
    public String getWeatherDescription(){
        return weatherDescription;
    }
    public String getDate(){
        return date;
    }
    //setters
    public void setTemp(double newTemp){
        temperature=newTemp;
    }
    public void setVent(double newVent){
        vent=newVent;
    }
    public void setHumidity(double newHumidity){
        humidity=newHumidity;
    }
    public void setVille(String newVille){
        ville=newVille;
    }
    public void setWeatherDescription(String newWeatherDescription){
        weatherDescription=newWeatherDescription;
    }
    public void setDate(String newDate){
        date = newDate;
    }
}
