package com.nguapps.ashtech.wetherapp;

public class WeatherForecastPresenter implements WeatherForecastPresenterInterface {

    private MainActivity mainActivity;
    private WeatherForecastModel weatherForecastModel;

    public WeatherForecastPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        weatherForecastModel = new WeatherForecastModel(this);
    }

    @Override
    public void getWeatherForecast(String city) {
        mainActivity.progressBarVisibility(true);
        if(city.equals("Dhaka")) city = "Dhaka,%20BD";
        if(city.equals("Coxsbazar")) city = "Coxs%20bazar";
        weatherForecastModel.getWeatherForecast(city);
    }

    @Override
    public void setWeatherForecast(DataExtractor dataExtractor) {
        mainActivity.weatherForecastFetched(dataExtractor.getCurrentTemp(), dataExtractor.getFeelsLike(), dataExtractor.getDescription(),"Max Temp.: "+dataExtractor.getMaxTemp(),
                "Min Temp.: "+dataExtractor.getMinTemp(),"Wind Speed: "+dataExtractor.getSpeed(),"Humidity: "+dataExtractor.getHumidity(),
                "Pressure: "+dataExtractor.getPressure(),"Sunrise: "+dataExtractor.getSunrise(),"Sunset: "+dataExtractor.getSunset());
        mainActivity.progressBarVisibility(false);
    }

    @Override
    public void failedFetchingWeather() {
        mainActivity.weatherForecastFailed();
    }
}
