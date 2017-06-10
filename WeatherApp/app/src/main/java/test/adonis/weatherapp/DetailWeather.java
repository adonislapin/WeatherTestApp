package test.adonis.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import test.adonis.weatherapp.controller.DetailController;
import test.adonis.weatherapp.model.Weather;

public class DetailWeather extends AppCompatActivity {

    private ImageView mWeatherImg;
    private TextView mTextCountry, mHumidity, mCurrentTemp, mDescription, mPressure ;

    private DetailController mDetailController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail_weather);

        Bundle bundle = getIntent().getExtras();
        String country = "";

        if (bundle != null) {
            country = bundle.getString("Title");
        }

        mDetailController = new DetailController(this);
        linkUI();

        mTextCountry.setText("Weather in " + country);
        mDetailController.searchWeatherForCountry(country);
    }

    private void linkUI(){
        mWeatherImg = (ImageView) findViewById(R.id.weather_img);
        mTextCountry = (TextView) findViewById(R.id.full_name_country);
        mCurrentTemp = (TextView) findViewById(R.id.current_temp);
        mDescription = (TextView) findViewById(R.id.current_desc);
        mPressure = (TextView) findViewById(R.id.current_pressure);
        mHumidity = (TextView) findViewById(R.id.current_humidity);
    }

    public void showDataWeather(Weather weather){
        Glide.with(this).load(weather.getWeatherIcon()).into(mWeatherImg);
        mCurrentTemp.setText(weather.getMainTemp() + " °C");
        mDescription.setText(weather.getWeatherDescription());
        mPressure.setText(weather.getMainPressure() + " hpa");
        mHumidity.setText(weather.getMainHumidity() + " %");
    }

}
