package com.wipro.weather;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wipro.weather.activities.WeatherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class WeatherActivityTest {
    @Rule
    public ActivityTestRule<WeatherActivity> activityTestRule = new ActivityTestRule<WeatherActivity>(WeatherActivity.class);

//TODO: Write more test cases for UI/UX interaction
    @Test
    public void checkUINotNull() throws InterruptedException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withId(R.id.weather_data_time))));
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withId(R.id.weather_data_image))));
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withId(R.id.weather_data_temperature))));
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withId(R.id.weather_data_wind))));
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withId(R.id.weather_data_pressure))));

    }

    @Test
    public void checkRecyclerViewIsVisible() throws InterruptedException {
        onView(withId(R.id.activity_weather_forecast_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerView(){

        onView(withId(R.id.activity_weather_forecast_list)).perform(scrollToPosition(10))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_weather_forecast_list)).perform(scrollToPosition(4));
        //Espresso seems to need some time before it can register an item within the
        //Recycler View, thus the need to putt the thread to sleep after scrolling to a position
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withText("282.73 F"))));
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withText("1.66 m/s"))));
        onView(withId(R.id.activity_weather_forecast_list))
                .check(matches(hasDescendant(withText("1019.79"))));


    }

}
