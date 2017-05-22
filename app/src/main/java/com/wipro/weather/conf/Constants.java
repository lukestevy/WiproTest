package com.wipro.weather.conf;

/**
 * Static constants variables.
 */
public class Constants {

    /**
     * API KEY
     */
    public final static String API_KEY = "643a2a5ee16c6a6f9613ff51e0c5edfd";

    /**
     * Weather webserver
     */
    public final static String URL_WEATHER = "http://api.openweathermap.org/data/2.5/";

    /**
     * Weather webserver
     */
    public final static String URL_IMG_WEATHER = "http://openweathermap.org/img/w/";
    /**
     * Weather extension
     */
    public final static String EXT_WEATHER = ".png";

    /**
     * 5 day forecast is available at any location or city
     * 5 day forecast includes weather data every 3 hours
     * Forecast is available in JSON
     */
    public final static String URL_5_DAYS = "forecast?q=London&appid=";



}
