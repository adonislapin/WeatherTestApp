package test.adonis.weatherapp.dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import test.adonis.weatherapp.constants.WeatherConstants;
import test.adonis.weatherapp.model.Weather;

import static test.adonis.weatherapp.utils.MathUtils.KelvinToCelsiusConverter;

/**
 * Created by Paola on 09/06/2017.
 */

public class JsonParser {

    public ArrayList<Weather> countriesParcer(String rawText){
        ArrayList<Weather> weathers = null;
        try {
            if (rawText != null) {
                JSONObject jObj = new JSONObject(cleanRawText(rawText));
                JSONArray arrJson = jObj.getJSONArray("list");
                weathers = new ArrayList<Weather>();

                for (int a = 0; a < arrJson.length(); a++) {
                    Weather weatherTemp = new Weather();

                    JSONObject weatherItem = arrJson.getJSONObject(a);
                    weatherTemp.setId(weatherItem.getInt("id"));
                    weatherTemp.setCountry(weatherItem.getString("name"));

                    JSONObject main = weatherItem.getJSONObject("main");

                    weatherTemp.setMainTemp(KelvinToCelsiusConverter(main.getString("temp")));
                    weatherTemp.setMainHumidity(main.getString("humidity"));
                    weatherTemp.setMainPressure(main.getString("pressure"));
                    weatherTemp.setMainTempMax(KelvinToCelsiusConverter(main.getString("temp_max")));
                    weatherTemp.setMainTempMin(KelvinToCelsiusConverter(main.getString("temp_min")));

                    JSONObject wind = weatherItem.getJSONObject("wind");

                    weatherTemp.setWindSpeed(wind.getString("speed"));

                    JSONObject sys = weatherItem.getJSONObject("sys");

                    weatherTemp.setCityName(sys.getString("country"));

                    JSONObject weatherInfo = weatherItem.getJSONArray("weather").getJSONObject(0);

                    weatherTemp.setWeatherMain(weatherInfo.getString("main"));
                    weatherTemp.setWeatherDescription(weatherInfo.getString("description"));
                    weatherTemp.setWeatherIcon( getUrlForIcon(weatherInfo.getString("icon")));
                    weatherTemp.setWeatherId(weatherInfo.getInt("id"));

                    weathers.add(weatherTemp);
                    weatherTemp = null;
                }

                return  weathers;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Weather getWeatherForRawText(String text){
        Weather weatherTemp = null;

        try {
            JSONObject weatherItem = new JSONObject(text);
            weatherTemp = new Weather();

            weatherTemp.setId(weatherItem.getInt("id"));
            weatherTemp.setCountry(weatherItem.getString("name"));
            weatherTemp.setVisibility(weatherItem.getString("visibility"));

            JSONObject main = weatherItem.getJSONObject("main");
            weatherTemp.setMainTemp(main.getString("temp"));
            weatherTemp.setMainHumidity(main.getString("humidity"));
            weatherTemp.setMainPressure(main.getString("pressure"));
            weatherTemp.setMainTempMax(main.getString("temp_max"));
            weatherTemp.setMainTempMin(main.getString("temp_min"));

            JSONObject wind = weatherItem.getJSONObject("wind");

            weatherTemp.setWindSpeed(wind.getString("speed"));
            weatherTemp.setWindDeg(wind.getString("deg"));

            JSONObject sys = weatherItem.getJSONObject("sys");

            weatherTemp.setCityName(sys.getString("country"));
            weatherTemp.setSunrise(sys.getString("sunrise"));
            weatherTemp.setSunset(sys.getString("sunset"));

            JSONObject weatherInfo = weatherItem.getJSONArray("weather").getJSONObject(0);

            weatherTemp.setWeatherMain(weatherInfo.getString("main"));
            weatherTemp.setWeatherDescription(weatherInfo.getString("description"));
            weatherTemp.setWeatherIcon( getUrlForIcon(weatherInfo.getString("icon")));
            weatherTemp.setWeatherId(weatherInfo.getInt("id"));
        } catch (JSONException e) {
            Log.d(WeatherConstants.TAG, "getWeatherForRawText" + e.getLocalizedMessage());
        }

        return  weatherTemp;
    }

    public ArrayList<Weather> getDailyWeather(String text){
        ArrayList<Weather> daily = null;

        try {
            JSONObject rootItem = new JSONObject(text);
            JSONArray arrJson = rootItem.getJSONArray("list");
            daily = new ArrayList<Weather>();

            for (int i = 0; i < arrJson.length(); i++) {
                Weather weatherTemp = new Weather();
                JSONObject weatherItem = arrJson.getJSONObject(i);

                weatherTemp.setMainHumidity(weatherItem.getString("humidity"));
                weatherTemp.setMainPressure(weatherItem.getString("pressure"));
                weatherTemp.setWindSpeed(weatherItem.getString("speed"));
                weatherTemp.setWindDeg(weatherItem.getString("deg"));
                weatherTemp.setClouds(weatherItem.getString("clouds"));

                JSONObject temp = weatherItem.getJSONObject("temp");
                weatherTemp.setTempDay(temp.getString("day"));
                weatherTemp.setTempNight(temp.getString("night"));

                JSONObject weatherInfo = weatherItem.getJSONArray("weather").getJSONObject(0);

                weatherTemp.setWeatherMain(weatherInfo.getString("main"));
                weatherTemp.setWeatherDescription(weatherInfo.getString("description"));
                weatherTemp.setWeatherIcon( getUrlForIcon(weatherInfo.getString("icon")));
                weatherTemp.setWeatherId(weatherInfo.getInt("id"));

                weatherTemp.setDate(getDateForPosition(i));

                daily.add(weatherTemp);
                weatherTemp = null;
            }
        } catch (JSONException e) {
            Log.d(WeatherConstants.TAG, "getWeatherForRawText" + e.getLocalizedMessage());
        }

        return daily;
    }

    private String cleanRawText(String raw){
        String cleanedString = "";

        String [] temp = raw.split("\\(");

        if(temp.length > 1){
            cleanedString = temp[temp.length -1];
            cleanedString = cleanedString.substring(0, cleanedString.length() - 1);
        }

        return  cleanedString;
    }

    private String getUrlForIcon(String icon){
        if(icon != null){
            return WeatherConstants.IMG_URL + icon + WeatherConstants.IMG_SUFIX;
        } else {
            return WeatherConstants.DEFAULT_IMG;
        }
    }

    public static String getDateForPosition(int position) {
        String date = "";
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd MM yyyy");
        Date now = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, position); //minus number would decrement the days

        date = sdfDate.format(cal.getTime());
        return date;
    }

}
