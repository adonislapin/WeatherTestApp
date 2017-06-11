package test.adonis.weatherapp.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import test.adonis.weatherapp.CountrySelectorActivity;
import test.adonis.weatherapp.DetailWeather;
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

    public void goToItemWithPosition(int position, View view, int idCountry){
        TextView txt = (TextView) ((RelativeLayout)((CardView)((LinearLayout)view).getChildAt(0)).getChildAt(0)).getChildAt(1);
        ImageView img = (ImageView) ((RelativeLayout)((CardView)((LinearLayout)view).getChildAt(0)).getChildAt(0)).getChildAt(0);

        Intent intent = new Intent(viewReference, DetailWeather.class);
        Pair<View, String> pair1 = Pair.create((View)img, img.getTransitionName());
        Pair<View, String> pair2 = Pair.create((View)txt, txt.getTransitionName());

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(viewReference, pair1, pair2);

        Bundle bundle = options.toBundle();
        intent.putExtra("Title", txt.getText());
        intent.putExtra("idCountry", String.valueOf(idCountry));
        viewReference.startActivity(intent, bundle);
    }

}
