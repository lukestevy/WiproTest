package com.wipro.weather.mvp;


public interface BaseView<T> {
    void setPresenter(T presenter);
}
