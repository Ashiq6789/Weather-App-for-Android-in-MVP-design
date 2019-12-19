package com.nguapps.ashtech.wetherapp;

import android.os.Handler;
import android.util.Log;



public class WeatherForecastModel implements WeatherForecastModelInterface {

    private WeatherForecastPresenter weatherForecastPresenter;
    private Handler handler;

    public WeatherForecastModel(WeatherForecastPresenter weatherForecastPresenter){ this.weatherForecastPresenter = weatherForecastPresenter;}

    @Override
    public void getWeatherForecast(String city) {

        handler = new Handler();

        new Thread(()->{
            MagicLamp magicLamp=MagicLamp.getInstance();
            String result=magicLamp.getWeatherData(city);
            if (result!=null) {
                DataExtractor object = new DataExtractor(result);
                handler.post(()-> weatherForecastPresenter.setWeatherForecast(object));
            }else{
                handler.post(()-> weatherForecastPresenter.failedFetchingWeather());
            }
        }).start();
    }
}
