package test.adonis.weatherapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import test.adonis.weatherapp.R;
import test.adonis.weatherapp.model.Weather;

/**
 * Created by Adoniram Dominguez on 11/06/2017.
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.MyViewHolderDaily> {

    private Context mContext;
    private List<Weather> weathers;
    private DetailController mDetailController;

    public DailyAdapter(Context mContext, List<Weather> weathers, DetailController countryController) {
        this.mContext = mContext;
        this.weathers = weathers;
        this.mDetailController = countryController;
    }

    public class MyViewHolderDaily extends RecyclerView.ViewHolder {
        public TextView header, subtitle, info;
        public ImageView weatherImg;

        public MyViewHolderDaily(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.header);
            subtitle = (TextView) view.findViewById(R.id.subtitle);
            info = (TextView) view.findViewById(R.id.info);
            weatherImg = (ImageView) view.findViewById(R.id.weather_img);
        }
    }

    @Override
    public DailyAdapter.MyViewHolderDaily onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_daily, parent, false);

        return new DailyAdapter.MyViewHolderDaily(itemView);
    }

    @Override
    public void onBindViewHolder(DailyAdapter.MyViewHolderDaily holder, int position) {
        Weather weatherTemp = weathers.get(position);
        holder.header.setText( "Day: " + weatherTemp.getTempDay() + " Night: " + weatherTemp.getTempNight());
        holder.subtitle.setText("Clouds: " + weatherTemp.getClouds() + "% " +
            weatherTemp.getMainPressure() + " hpa Wind: " + weatherTemp.getWindSpeed() + "m/s" );
        holder.info.setText(weatherTemp.getDate());

        Glide.with(mContext).load(weatherTemp.getWeatherIcon()).into(holder.weatherImg);
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
