package test.adonis.weatherapp.controller;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import test.adonis.weatherapp.DetailWeather;
import test.adonis.weatherapp.constants.ServerConstants;
import test.adonis.weatherapp.dao.JsonParser;
import test.adonis.weatherapp.io.HttpConector;
import test.adonis.weatherapp.model.Weather;

/**
 * Created by Paola on 10/06/2017.
 */

public class DetailController extends GenericController {

    DetailWeather viewReference;
    Handler mHandler = new Handler() ;
    SearchRunnable mSearchRunnable = new SearchRunnable();
    DailyRunnable mDailyRunnable = new DailyRunnable();

    public DetailController (DetailWeather viewReference) {
        this.viewReference = viewReference;
    }

    public void searchWeatherForCountry(String country){
        SearchTask searchTask = new SearchTask(country, mHandler, mSearchRunnable);
        searchTask.start();
    }

    public void getDailyWeatherFor(String idCountry){
        DailyTask dailyTask = new DailyTask(idCountry, mHandler, mDailyRunnable);
        dailyTask.start();
    }

    class SearchRunnable implements Runnable{

        Weather weather;

        @Override
        public void run() {
            viewReference.showDataWeather(getWeather());
        }
        public Weather getWeather() {
            return weather;
        }

        public void setWeather(Weather rawText) {
            this.weather = rawText;
        }
    }

    class SearchTask extends Thread{

        private String search;
        private Handler handler;
        private SearchRunnable runnable;

        public SearchTask(String search, Handler handler, SearchRunnable runnable){
            this.handler = handler;
            this.runnable = runnable;
            this.search = search;
        }

        @Override
        public void run() {
            String raw = HttpConector.getData(ServerConstants.WEATHER, ServerConstants.qKeyParameter ,search,
                    ServerConstants.KEY_UNITS, "metric",
                    ServerConstants.KEY_APPID, ServerConstants.APPID);
            Weather weather = new JsonParser().getWeatherForRawText(raw);
            Log.d("XXX","" + weather.getMainTemp());
            runnable.setWeather(weather);

            handler.post(runnable);
        }
    }

    class DailyRunnable implements Runnable{

        ArrayList<Weather> dailyWeather;

        @Override
        public void run() {
            viewReference.showDailyWeather(dailyWeather);
        }

        public void setDailyWeather(ArrayList<Weather> daily) {
            this.dailyWeather = daily;
        }
    }

    class DailyTask extends Thread{

        private String idCountry;
        private Handler handler;
        private DailyRunnable runnable;

        public DailyTask(String idCountry, Handler handler, DailyRunnable runnable){
            this.handler = handler;
            this.runnable = runnable;
            this.idCountry = idCountry;
        }

        @Override
        public void run() {
            String newUrl = String.format(ServerConstants.REQUEST_DAILY, idCountry );

            String raw = HttpConector.getData(ServerConstants.DAILY, newUrl);
            ArrayList<Weather> dailyWeather = new JsonParser().getDailyWeather(raw);

            runnable.setDailyWeather(dailyWeather);

            handler.post(runnable);
        }

    }

}
