package test.adonis.weatherapp.controller;

import android.os.Handler;
import android.util.Log;

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

    public DetailController (DetailWeather viewReference) {
        this.viewReference = viewReference;
    }

    public void searchWeatherForCountry(String country){
        SearchTask searchTask = new SearchTask(country, mHandler, mSearchRunnable);
        searchTask.start();
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

}
