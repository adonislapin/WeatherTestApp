package test.adonis.weatherapp.dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                    weatherTemp.setWeatherIcon(weatherInfo.getString("icon"));
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

    private String cleanRawText(String raw){
        String cleanedString = "";

        String [] temp = raw.split("\\(");

        if(temp.length > 1){
            cleanedString = temp[temp.length -1];
            cleanedString = cleanedString.substring(0, cleanedString.length() - 1);
        }

        Log.d("XXX","Cleaned: " + cleanedString);

        return  cleanedString;
    }

}
