package com.nguapps.ashtech.wetherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private WeatherForecastPresenter presenter;
    private Spinner spinner;
    private String [] cityList;
    private TextView actualTemp, feelLikeTemp, maxTemp, minTemp, wind, pressure, humidity, description, sunset, sunrise, status, problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.statusBar));

        spinner = findViewById(R.id.address);
        cityList= getResources().getStringArray(R.array.city_List);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.list_layout, R.id.list, cityList);
        spinner.setAdapter(spinnerAdapter);

        Button update= findViewById(R.id.update);

        actualTemp = findViewById(R.id.actual_temp);
        feelLikeTemp = findViewById(R.id.feel_like);
        maxTemp = findViewById(R.id.temp_max);
        minTemp = findViewById(R.id.temp_min);
        wind = findViewById(R.id.wind_speed);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        description = findViewById(R.id.description);
        sunset = findViewById(R.id.sunset);
        sunrise = findViewById(R.id.sunrise);
        status = findViewById(R.id.loader);
        problem = findViewById(R.id.problem);

        presenter = new WeatherForecastPresenter(this);

        update.setOnClickListener(e-> {
            presenter.getWeatherForecast(spinner.getSelectedItem().toString());
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getWeatherForecast(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void progressBarVisibility(Boolean visibility) {
        if (visibility) {
            status.setVisibility(View.VISIBLE);
        }else{
            status.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void weatherForecastFetched(String actual_temp, String feels_like_temp, String descriptionW, String max_temp, String min_temp, String windW,
                                       String humidityW, String pressureW, String sunriseW, String sunsetW) {

        actualTemp.setText(actual_temp);
        feelLikeTemp.setText(feels_like_temp);
        description.setText(descriptionW);
        maxTemp.setText(max_temp);
        minTemp.setText(min_temp);
        wind.setText(windW);
        humidity.setText(humidityW);
        pressure.setText(pressureW);
        sunrise.setText(sunriseW);
        sunset.setText(sunsetW);
        problem.setVisibility(View.INVISIBLE);
    }

    @Override
    public void weatherForecastFailed() {
        problem.setVisibility(View.VISIBLE);
        status.setVisibility(View.INVISIBLE);
    }
}
