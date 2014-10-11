package com.example.copperadmin.listquicktest;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Copper Admin on 10/10/2014.
 */
public class FeedDeserializer implements JsonDeserializer<ArrayList<WeatherFeed>> {


    @Override
    public ArrayList<WeatherFeed> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        ArrayList<WeatherFeed> weatherFeeds = new ArrayList<WeatherFeed>();

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement hourlyJsonElement = jsonObject.get("hourly_forecast");
        JsonArray hourlyJsonArray = hourlyJsonElement.getAsJsonArray();

        //Loop through JsonArray
        for (int i = 0; i < hourlyJsonArray.size(); i++) {

            JsonElement element = hourlyJsonArray.get(i);
            JsonObject elementObject = element.getAsJsonObject();

            //lets get the icon
            String iconUrl = elementObject.get("icon").toString();

            //lets get the time from the element object
            JsonElement timeJsonElement = elementObject.get("FCTTIME");
            JsonObject timeJsonObject = timeJsonElement.getAsJsonObject();


            Calendar c = Calendar.getInstance();
            int year = timeJsonObject.get("year").getAsInt();
            int month = timeJsonObject.get("mon").getAsInt();
            int day = timeJsonObject.get("mday").getAsInt();
            int hour = timeJsonObject.get("hour").getAsInt();
//            int minute = timeJsonObject.get("min").getAsInt();

            c = getCalender(year, month, day, hour, 0);


            //lets get the Temperatures

            JsonElement TemperatureJsonElement = elementObject.get("temp");
            JsonObject TemperatureJsonObject = TemperatureJsonElement.getAsJsonObject();
            int fahrenheitTemperature = TemperatureJsonObject.get("english").getAsInt();
            int celsiusTemperature = TemperatureJsonObject.get("metric").getAsInt();

            //Now lets create a WeatherFeed Object and populate it then add it to the ArrayList<WeatherFeed>
            WeatherFeed weatherFeed = new WeatherFeed(c, iconUrl, fahrenheitTemperature, celsiusTemperature);
            weatherFeeds.add(weatherFeed);

        }

        return weatherFeeds;

    }

    private Calendar getCalender(int year, int month, int day, int hour, int minute) {

        Calendar c = Calendar.getInstance();

        switch (month) {
            case 1:
                c.set(year, Calendar.JANUARY, day, hour, minute);
                break;
            case 2:
                c.set(year, Calendar.FEBRUARY, day, hour, minute);
                break;
            case 3:
                c.set(year, Calendar.MARCH, day, hour, minute);
                break;
            case 4:
                c.set(year, Calendar.APRIL, day, hour, minute);
                break;
            case 5:
                c.set(year, Calendar.MAY, day, hour, minute);
                break;
            case 6:
                c.set(year, Calendar.JUNE, day, hour, minute);
                break;
            case 7:
                c.set(year, Calendar.JULY, day, hour, minute);
                break;
            case 8:
                c.set(year, Calendar.AUGUST, day, hour, minute);
                break;
            case 9:
                c.set(year, Calendar.SEPTEMBER, day, hour, minute);
                break;
            case 10:
                c.set(year, Calendar.OCTOBER, day, hour, minute);
                break;
            case 11:
                c.set(year, Calendar.NOVEMBER, day, hour, minute);
                break;
            case 12:
                c.set(year, Calendar.DECEMBER, day, hour, minute);
                break;
            default:
                return null;
        }
        return c;
    }
}
