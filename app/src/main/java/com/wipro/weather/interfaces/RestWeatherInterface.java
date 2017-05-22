package com.wipro.weather.interfaces;



import com.wipro.weather.conf.Constants;
import com.wipro.weather.data.WeatherResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Interface to make a connection with the webservice using Retrofit
 */
public interface RestWeatherInterface {
    @GET(Constants.URL_5_DAYS+Constants.API_KEY)
    Observable<WeatherResponse> getWeather5DaysForecast();
}
