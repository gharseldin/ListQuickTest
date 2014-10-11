package com.example.copperadmin.listquicktest;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Copper Admin on 10/9/2014.
 */
public class Feeds {

    private static Feeds sFeeds;
    private Context mContext;

    private ArrayList<WeatherFeed> mWeatherFeeds;

    private Feeds(Context context){
        mContext = context.getApplicationContext();
        mWeatherFeeds = new ArrayList<WeatherFeed>();
    }

    public static Feeds get(Context context){
        if(sFeeds == null){
            sFeeds = new Feeds(context);
        }
        return sFeeds;
    }

    public void setWeatherFeeds(JSONObject jsonString){

        //use the FeedDeserializer to deserialize the string into feeds
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ArrayList.class, new FeedDeserializer());
        Gson gson = gsonBuilder.create();
        mWeatherFeeds = gson.fromJson(jsonString.toString(),ArrayList.class);
    }

    public ArrayList<WeatherFeed> getWeatherFeeds(){
        return mWeatherFeeds;
    }
}
