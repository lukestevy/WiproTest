package com.wipro.weather;



import com.wipro.weather.activities.WeatherActivity;

import junit.framework.TestSuite;


public class TestApp extends AbstractSystemTestBase<WeatherActivity> {

    public TestApp() {
        super(WeatherActivity.class);
    }

    public static TestSuite suite() {
        //TODO Test parcelable classes
        //TODO Test RESTWeather
        TestSuite t = new TestSuite();

        t.addTestSuite(WeatherScreenTest.class);
        t.addTestSuite(WeatherDetailScreen.class);


        return t;
    }

}
