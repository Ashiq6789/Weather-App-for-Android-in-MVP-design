package com.nguapps.ashtech.wetherapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataExtractor {
    private double currentTemp, feelsLike, maxTemp, minTemp, speed;
    private int pressure, humidity;
    private String description, sunrise, sunset;
    private boolean failed;

 public DataExtractor(String data){
     try {
         JSONObject jsonObject= new JSONObject(data);
         description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
         currentTemp = jsonObject.getJSONObject("main").getDouble("temp");
         feelsLike = jsonObject.getJSONObject("main").getDouble("feels_like");
         maxTemp = jsonObject.getJSONObject("main").getDouble("temp_max");
         minTemp = jsonObject.getJSONObject("main").getDouble("temp_min");
         speed = jsonObject.getJSONObject("wind").getDouble("speed");
         pressure = jsonObject.getJSONObject("main").getInt("pressure");
         humidity = jsonObject.getJSONObject("main").getInt("humidity");
         SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
         sunrise = sdf.format(new Date(jsonObject.getJSONObject("sys").getLong("sunrise")*1000));
         sunset = sdf.format(new Date(jsonObject.getJSONObject("sys").getLong("sunset")*1000));
         Log.v("prob", jsonObject+"");
     } catch (JSONException e) {
         Log.v("prob", e+"");
         failed=true;
     }
 }

    public String getCurrentTemp() {
        return failed? null : String.format("%.1f",(currentTemp-273.15))+"°C";
    }

    public String getMaxTemp() {
        return failed? null : String.format("%.1f",(maxTemp-273.15))+"°C";
    }

    public String getMinTemp() {
        return failed? null : String.format("%.1f",(minTemp-273.15))+"°C";
    }

    public String getSpeed() {
        return failed? null : String.format("%.1f",((speed*3600)/1000))+"kmh";
    }

    public String getPressure() {
        return failed? null : pressure+"hpa";
    }

    public String getHumidity() {
        return failed? null : humidity+"%";
    }

    public String getFeelsLike() {
        return failed? null : String.format("%.1f",(feelsLike-273.15))+"°C";
    }

    public String getDescription() {
        return failed? null : description;
    }

    public String getSunrise(){
        return failed? null : sunrise;
    }

    public String getSunset(){
        return failed? null : sunset;
    }
}
