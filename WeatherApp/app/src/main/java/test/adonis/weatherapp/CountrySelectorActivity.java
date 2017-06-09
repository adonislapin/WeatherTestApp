package test.adonis.weatherapp;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

import test.adonis.weatherapp.controller.CountryController;
import test.adonis.weatherapp.controller.WeathersAdapter;
import test.adonis.weatherapp.model.Weather;
import test.adonis.weatherapp.utils.PopUpUtils;

import static java.security.AccessController.getContext;

public class CountrySelectorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextCountry;
    private Button mBtnGo;
    private ProgressBar mProgressBar;
    private CountryController mCountryController = null;

    private RecyclerView mRecyclerView;
    private WeathersAdapter mWeathersAdapter;
    private ArrayList<Weather> mWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selector);

        mCountryController = new CountryController(this);

        linkUI();
    }

    private void linkUI(){
        mEditTextCountry = (EditText) findViewById(R.id.edit_country);
        mBtnGo = (Button) findViewById(R.id.btnGo);
        mProgressBar = (ProgressBar) findViewById(R.id.pgBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mBtnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnGo){
            if(mEditTextCountry.getText() != null && !mEditTextCountry.getText().toString().equals("")){
                mCountryController.searchCountry(mEditTextCountry.getText().toString());
            } else {
                AlertDialog dialog = PopUpUtils.showUserMessage(this , getString(R.string.error_message_empty) );
                dialog.show();
            }
        }
    }

    public void viewProgressBar(boolean enabled){
        mProgressBar.setVisibility(enabled ? View.VISIBLE : View.GONE);
        mProgressBar.setIndeterminate( enabled );
    }

    public void fillCountries(ArrayList<Weather> weathers){
        if(weathers != null){
            if(weathers.size() > 0){
                this.mWeathers = weathers;

                mWeathersAdapter = new WeathersAdapter(this, mWeathers);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mWeathersAdapter);

                mRecyclerView.setVisibility(View.VISIBLE);
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
