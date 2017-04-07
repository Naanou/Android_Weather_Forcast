package com.nael.weatherforcast;

import java.util.Date;

/**
 * Created by Madanael on 31/03/2017.
 */

public class Weather {

    private String temperature,vent,humidity;
    private String ville,weatherDescription;
    private String date;
    private String icon;

    public Weather(String t, String vt, String h,String v,String wd, String d) {
        temperature=t;
        vent=vt;
        humidity=h;
        ville=v;
        weatherDescription=wd;
        date=d;
        icon = null;

    }

    public Weather(){
        ville = null;
        weatherDescription = null;
        date = null;
        temperature = null;
        vent=null;
        humidity = null;
    }

    //getters
    public String getTemp(){
        return temperature;
    }
    public String getVent(){
        return vent;
    }
    public String getHumidity(){
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
    public String getIcon(){
        return icon;
    }
    //setters
    public void setTemp(String newTemp){
        temperature=newTemp;
    }
    public void setVent(String newVent){
        vent=newVent;
    }
    public void setHumidity(String newHumidity){
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
    public void setIcon(String newIcon){
        icon = newIcon;
    }
}
