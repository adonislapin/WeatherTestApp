package test.adonis.weatherapp.controller;

import android.os.AsyncTask;

import java.util.ArrayList;

import test.adonis.weatherapp.CountrySelectorActivity;
import test.adonis.weatherapp.constants.ServerConstants;
import test.adonis.weatherapp.dao.JsonParser;
import test.adonis.weatherapp.io.HttpConector;
import test.adonis.weatherapp.model.Weather;

/**
 * Created by Paola on 09/06/2017.
 */

public class CountryController extends GenericController {

    CountrySelectorActivity viewReference;

    public CountryController (CountrySelectorActivity viewReference) {
        this.viewReference = viewReference;
    }

    public void searchCountry(String country){
        CountryTask countryTask = new CountryTask();
        countryTask.execute(country);
    }

    class CountryTask extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            viewReference.viewProgressBar(true);
        }

        @Override
        protected String doInBackground(String... params) {
            String newUrl = String.format(ServerConstants.REQUEST_COUNTRY, params[0] );
            return HttpConector.getData(ServerConstants.STANDALONE, newUrl);
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);

            JsonParser jsonParser = new JsonParser();
            ArrayList<Weather> items = jsonParser.countriesParcer(json);

            viewReference.fillCountries(items);
            viewReference.viewProgressBar(false);
        }
    }

}
