package com.wipro.weather.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable {

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * @return int
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing weather data to Parcel object
     * @param dest The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(main);
        dest.writeString(description);
        dest.writeString(icon);

    }

    /**
     * Empty constructor
     */
    public Weather(){

    }

    /**
     * Retrieving Weather data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     * @param in
     */
    private Weather(Parcel in){

        this.main = in.readString();
        this.description = in.readString();
        this.icon = in.readString();

    }

    /**
     * Creator class of the parcelable
     */
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {

        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };



    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
