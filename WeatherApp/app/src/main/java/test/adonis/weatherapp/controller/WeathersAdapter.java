package test.adonis.weatherapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import test.adonis.weatherapp.R;
import test.adonis.weatherapp.constants.WeatherConstants;
import test.adonis.weatherapp.model.Weather;

/**
 * Created by Paola on 09/06/2017.
 */

public class WeathersAdapter extends RecyclerView.Adapter<WeathersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Weather> weathers;
    private CountryController mCountryController;

    public WeathersAdapter(Context mContext, List<Weather> weathers, CountryController countryController) {
        this.mContext = mContext;
        this.weathers = weathers;
        this.mCountryController = countryController;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public TextView country, weatherInfo;
        public ImageView weatherImg, imgFlag;

        public MyViewHolder(View view) {
            super(view);
            country = (TextView) view.findViewById(R.id.country);
            weatherInfo = (TextView) view.findViewById(R.id.weather_info);
            weatherImg = (ImageView) view.findViewById(R.id.weather_img);
            imgFlag = (ImageView) view.findViewById(R.id.country_flag);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCountryController.goToItemWithPosition(position,v);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_weather, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Weather weatherTemp = weathers.get(position);
        holder.country.setText(weatherTemp.getCountry() + ", " + weatherTemp.getCityName() );
        holder.weatherInfo.setText(weatherTemp.getMainTemp() + " From : " + weatherTemp.getMainTempMin() + " to " + weatherTemp.getMainTempMin());

        Glide.with(mContext).load(weatherTemp.getWeatherIcon()).into(holder.weatherImg);

        holder.country.setTransitionName("country_" + position);
        holder.weatherImg.setTransitionName("weather_img_" + position);
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }


}
