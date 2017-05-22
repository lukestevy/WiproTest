package com.wipro.weather.activities.controllers;



import com.wipro.weather.activities.WeatherActivity;
import com.wipro.weather.data.WeatherResponse;
import com.wipro.weather.interfaces.RestWeatherInterface;
import com.wipro.weather.services.RestWeatherManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A Presenter which ask the background for weather info and inform the view when this data is available.
 */
public class FetchWeather {

    /**
     * View attached to this Presenter
     */
     private WeatherActivity mWeatherActivity;

    /**
     * Constructor of the presenter, will attached the view with the presenter
     * @param mWeatherActivity
     */
    public FetchWeather(WeatherActivity mWeatherActivity)
    {

        //Set the activity
        this.mWeatherActivity = mWeatherActivity;

    }

    /**
     * Ask background to get 5 days forecast
     */
    public void get5DaysForecast() {

        //Initialize retrofit to make a call to weather rest api
        RestWeatherInterface mRestWeatherInterface = RestWeatherManager.init();

        //Send a request to weather rest api to get 5 days forecast
        // and initialize an observer to send the data to the view when is available
        mRestWeatherInterface
                .getWeather5DaysForecast()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Show a generic error trying
                    //    mWeatherActivity.showGenericError();
                    }

                    @Override
                    public void onNext(WeatherResponse response) {
                        //Show 5 days weather forecast
                        if(response.getCod().equals("200")) {
                            mWeatherActivity.show5DaysForecast(response.getList());
                        }
                        else{

                          //  mWeatherActivity.showGenericError();
                        }

                    }
                });
    }

}
