package com.wipro.weather;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wipro.weather.activities.WeatherActivity;
import com.wipro.weather.activities.controllers.WeatherDetailActivity;


public class WeatherScreenTest extends AbstractSystemTestBase<WeatherActivity> {

    public WeatherScreenTest() {
        super(WeatherActivity.class);
    }

    public void testClickOnList() throws InterruptedException {
        assertTrue("Activity never started", solo.waitForActivity(WeatherActivity.class, WAIT_FOR_ACTIVITY_TIMEOUT));
        solo.waitForDialogToClose(10000);
        //TODO Improve iterating through the list clicking on every element
        RecyclerView ListView=(RecyclerView)solo.getView(R.id.activity_weather_forecast_list);
        View view=ListView.getChildAt(0);
        solo.clickOnView(view);
        assertTrue("Activity never started", solo.waitForActivity(WeatherDetailActivity.class, WAIT_FOR_ACTIVITY_TIMEOUT));

    }
    public void testViews() throws InterruptedException {
        assertTrue("Activity never started", solo.waitForActivity(WeatherActivity.class, WAIT_FOR_ACTIVITY_TIMEOUT));
        View list = solo.getView("activity_weather_forecast_list");
        assertNotNull(list);
    }


}
