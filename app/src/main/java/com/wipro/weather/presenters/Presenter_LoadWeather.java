package com.wipro.weather.presenters;

import com.wipro.weather.data.WeatherResponse;
import com.wipro.weather.interfaces.RestWeatherInterface;
import com.wipro.weather.services.RestWeatherManager;
import com.wipro.weather.utilities.RxUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Presenter_LoadWeather implements ILoadWeather.IPresenter {
    private RestWeatherInterface mRestWeatherInterface = RestWeatherManager.init();
    private CompositeSubscription compositeSubscription= new CompositeSubscription();
    /**
     * View attached to this Presenter
     */
    //private WeatherActivity iView;
    private ILoadWeather.IView iView;
    /**
     * Constructor of the presenter, will attached the view with the presenter
     * @param mWeatherActivity
     */
    public Presenter_LoadWeather(ILoadWeather.IView mWeatherActivity)
    {
        //Set the activity
        this.iView = mWeatherActivity;

    }
    @Override
    public void start() {
        compositeSubscription = RxUtils.getNewCompositeSubIfUnsubscribed(compositeSubscription);
        iView.setPresenter(Presenter_LoadWeather.this);
    }

    @Override
    public void get5DaysWeather() {

        //Initialize retrofit to make a call to weather rest api
        iView.onDataFetchLoading();

        //Send a request to weather rest api to get 5 days forecast
        // and initialize an observer to send the data to the view when is available
        compositeSubscription.add(mRestWeatherInterface
                .getWeather5DaysForecast()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        iView.onDataFetchSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Show a generic error trying
                        iView.onDataFetchError();
                    }

                    @Override
                    public void onNext(WeatherResponse response) {
                        //Show 5 days weather forecast
                        if(response.getCod().equals("200")) {
                            iView.show5DaysForecast(response.getList());
                        }
                        else{
                            iView.onDataFetchError();
                        }

                    }
                }));
    }

    @Override
    public void unSubscription() {
        RxUtils.unsubscribeIfNotNull(compositeSubscription);

    }
}
