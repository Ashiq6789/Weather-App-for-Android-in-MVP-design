package com.nguapps.ashtech.wetherapp;

public interface MainActivityInterface {
    void progressBarVisibility(Boolean visibility);
    void weatherForecastFetched(String actual_temp, String feels_like_temp, String descriptionW, String max_temp, String min_temp, String wind,
                                String humidityW, String pressureW, String sunriseW, String sunset);
    void weatherForecastFailed();
}
