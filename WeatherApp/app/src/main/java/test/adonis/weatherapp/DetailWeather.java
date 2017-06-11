package test.adonis.weatherapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import test.adonis.weatherapp.controller.DailyAdapter;
import test.adonis.weatherapp.controller.DetailController;
import test.adonis.weatherapp.controller.WeathersAdapter;
import test.adonis.weatherapp.model.Weather;
import test.adonis.weatherapp.utils.PopUpUtils;

public class DetailWeather extends AppCompatActivity {

    private ImageView mWeatherImg;
    private TextView mTextCountry, mHumidity, mCurrentTemp, mDescription, mPressure ;
    private RecyclerView mRecyclerView = null;
    private DailyAdapter mDailyAdapter = null;
    private DetailController mDetailController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail_weather);

        Bundle bundle = getIntent().getExtras();
        String country = "";
        String idCountry = "";

        if (bundle != null) {
            country = bundle.getString("Title");
            idCountry = bundle.getString("idCountry");
        }

        mDetailController = new DetailController(this);
        linkUI();

        mTextCountry.setText("Weather in " + country);
        mDetailController.searchWeatherForCountry(country);
        mDetailController.getDailyWeatherFor(idCountry);
    }

    private void linkUI(){
        mWeatherImg = (ImageView) findViewById(R.id.weather_img);
        mTextCountry = (TextView) findViewById(R.id.full_name_country);
        mCurrentTemp = (TextView) findViewById(R.id.current_temp);
        mDescription = (TextView) findViewById(R.id.current_desc);
        mPressure = (TextView) findViewById(R.id.current_pressure);
        mHumidity = (TextView) findViewById(R.id.current_humidity);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    public void showDataWeather(Weather weather){
        Glide.with(this).load(weather.getWeatherIcon()).into(mWeatherImg);
        mCurrentTemp.setText(weather.getMainTemp() + " °C");
        mDescription.setText(weather.getWeatherDescription());
        mPressure.setText(weather.getMainPressure() + " hpa");
        mHumidity.setText(weather.getMainHumidity() + " %");
    }

    public void showDailyWeather(ArrayList<Weather> dailyWeather){
        if(dailyWeather != null){
            if(dailyWeather.size() > 0){
                mDailyAdapter = new DailyAdapter(this, dailyWeather, mDetailController);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mDailyAdapter);
            } else {
                AlertDialog dialog = PopUpUtils.showUserMessage(this, getString(R.string.error_message_not_found));
                dialog.show();
            }
        } else {
            AlertDialog dialog = PopUpUtils.showUserMessage(this, getString(R.string.error_message_no_items));
            dialog.show();
        }
    }

}
