package com.nguapps.ashtech.wetherapp;

public interface WeatherForecastPresenterInterface {
    void getWeatherForecast(String city);
    void setWeatherForecast(DataExtractor dataExtractor);
    void failedFetchingWeather();
}
