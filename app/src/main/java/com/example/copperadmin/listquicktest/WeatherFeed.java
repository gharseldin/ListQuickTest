package com.example.copperadmin.listquicktest;

import java.util.Calendar;

/**
 * Created by Copper Admin on 10/10/2014.
 */
public class WeatherFeed {

    private Calendar mDateOfFeed;
    private String mIconUrl;
    private int fahrenheitTemperature;
    private int celsiusTemperature;

    public WeatherFeed(Calendar c, String icon, int fT, int cT){
        mDateOfFeed = c;
        mIconUrl = icon;
        fahrenheitTemperature = fT;
        celsiusTemperature = cT;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public Calendar getDateOfFeed() {
        return mDateOfFeed;
    }

    public void setDateOfFeed(Calendar dateOfFeed) {
        mDateOfFeed = dateOfFeed;
    }

    public int getFahrenheitTemperature() {
        return fahrenheitTemperature;
    }

    public void setFahrenheitTemperature(int fahrenheitTemperature) {
        this.fahrenheitTemperature = fahrenheitTemperature;
    }

    public int getCelsiusTemperature() {
        return celsiusTemperature;
    }

    public void setCelsiusTemperature(int celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
    }
}
