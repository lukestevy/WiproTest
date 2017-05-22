package com.wipro.weather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wipro.weather.R;
import com.wipro.weather.activities.controllers.WeatherDetailActivity;
import com.wipro.weather.adapters.viewholders.WeatherViewHolder;
import com.wipro.weather.conf.Constants;
import com.wipro.weather.data.Weather;
import com.wipro.weather.data.WeatherData;
import com.wipro.weather.utilities.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A Recycler View Adapter which inflate into a recycler view a given list of weather forecast
 */
public class WeatherRecyclerViewAdapter
        extends RecyclerView.Adapter<WeatherViewHolder> {

    /**
     * List of weather forecast
     */
    private final List<WeatherData> mWeatherDataList;
    /**
     * Context of the activity which created this object
     */
    private Context mContext;

    /**
     * Constructor of the class
     * It will initialize variables of this class
     * @param mWeatherDataList list of weather forecast
     * @param mContext Context of the activity which created this object
     */
    public WeatherRecyclerViewAdapter(List<WeatherData> mWeatherDataList, Context mContext) {
        //Init weather forecast list
        this.mWeatherDataList = mWeatherDataList;
        //Init main context
        this.mContext = mContext;
    }

    /**
     * Called when the view holder is created.
     * Initialize countries view holder
     * @param parent parent view
     * @param viewType type view
     * @return Initialized Countries View Holder
     */
    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_data, parent, false);
        return new WeatherViewHolder(view);
    }

    /**
     * Get number of countries
     * @return number of countries
     */
    @Override
    public int getItemCount() {
        return mWeatherDataList.size();
    }

    /**
     * Called when the view holder is bind
     * It will inflate all the data to the view holder
     * @param holder view holder to be inflate
     * @param position position in the list
     */
    @Override
    public void onBindViewHolder(final WeatherViewHolder holder, int position) {
        //Set countries data into the holder
        holder.mWeatherData = mWeatherDataList.get(position);

        //Show weather image
        ArrayList<Weather> mWeatherList = holder.mWeatherData.getWeather();
        if (mWeatherList != null && mWeatherList.get(0).getIcon() != null) {

            String mWeatherIcon = mWeatherList.get(0).getIcon().toLowerCase();
            ImageUtils.loadImage(holder.mImageWeather,Constants.URL_IMG_WEATHER + mWeatherIcon + Constants.EXT_WEATHER);
        }
        else{
            //Show generic error image
            holder.mImageWeather.setImageDrawable(mContext.getDrawable(android.R.drawable.ic_menu_close_clear_cancel));

        }

        //Set time
        holder.mTime.setText(holder.mWeatherData.getTime());

        //Set Wind
        holder.mWind.setText(holder.mWeatherData.getWind().getSpeed()+" m/s");

        //Set Pressure
        holder.mPressure.setText(holder.mWeatherData.getMain().getPressure());

        //Set temperature
        holder.mTemperature.setText(holder.mWeatherData.getMain().getTemp()+" F");

        //Set on click listener to show details of the weather forecast
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start weather detail activity
                Context context = v.getContext();
                Intent intent = new Intent(context, WeatherDetailActivity.class);
                intent.putExtra("weather", holder.mWeatherData.getWeather().get(0));
                context.startActivity(intent);

            }
        });
    }



}
