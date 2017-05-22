package com.wipro.weather.presenters;

import com.wipro.weather.data.WeatherData;
import com.wipro.weather.mvp.BasePresenter;
import com.wipro.weather.mvp.BaseView;

import java.util.ArrayList;

public interface ILoadWeather {

     interface IPresenter extends BasePresenter {
         void get5DaysWeather();
         void unSubscription();
     }

     interface IView extends BaseView<IPresenter> {
         void onDataFetchLoading();
         void onDataFetchSuccess();
         void onDataFetchError();
         void show5DaysForecast(ArrayList<WeatherData> mWeatherList) ;
    }
}
