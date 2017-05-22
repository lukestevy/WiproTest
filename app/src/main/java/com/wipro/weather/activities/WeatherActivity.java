package com.wipro.weather.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wipro.weather.R;
import com.wipro.weather.adapters.WeatherRecyclerViewAdapter;
import com.wipro.weather.data.WeatherData;
import com.wipro.weather.presenters.ILoadWeather;
import com.wipro.weather.presenters.Presenter_LoadWeather;
import com.wipro.weather.utilities.DialogUtils;
import com.wipro.weather.utilities.NetworkUtils;

import java.util.ArrayList;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class WeatherActivity extends AppCompatActivity implements ILoadWeather.IView{
    /**
     * Weather activity presenter class
     */
    private Presenter_LoadWeather mFetchWeather;
    private ILoadWeather.IPresenter iPresenter;

    /**
     * Object containing loading view
     */
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        connectService();
    }

    /**
     * Start the presenter
     */
    @Override
    protected void onResume() {
        super.onResume();
        mFetchWeather.start();
    }

    /**
     * Bind the presenter
     */
    @Override
    public void setPresenter(@NonNull ILoadWeather.IPresenter presenter) {
        iPresenter= checkNotNull(presenter);
    }
    @Override
    protected void onStop(){
        super.onStop();
        onDataFetchSuccess();
        iPresenter.unSubscription();
    }

    /**
     * Initialize a Recycler view and show a list of weather forecast
     * @param mWeatherList weather forecast list
     */
    public void show5DaysForecast(ArrayList<WeatherData> mWeatherList)
    {

        onDataFetchSuccess();
        //Inflate recycler view with the list of  weather forecast
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_weather_forecast_list);
        assert recyclerView != null;
        recyclerView.setAdapter(new WeatherRecyclerViewAdapter(mWeatherList, this));

    }

    public void connectService(){
        //Ask for 5 days forecast to then webservice
        mFetchWeather = new Presenter_LoadWeather(this);
        if(NetworkUtils.isNetworkAvailable(this))
            mFetchWeather.get5DaysWeather();
        else{
            Toast.makeText(getApplicationContext(),"No Network Available",Toast.LENGTH_LONG).show();
        }
    }

    public void onDataFetchSuccess(){
        //Close loading dialog
        if(loadingDialog!=null)
        loadingDialog.dismiss();
    }

    /**
     * Show a generic error when the app was trying to get the weather forecast
     */
    public void onDataFetchError()
    {
        DialogUtils.getErrorDialog(getApplication());
    }

    /**
     * Show loading dialog
     */
    public void onDataFetchLoading() {
        //Setup loading dialog
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("Fetching Data ...");
        loadingDialog.setMessage("Getting 5 days forecast");
        //Show loading dialog
        loadingDialog.show();
    }
}
