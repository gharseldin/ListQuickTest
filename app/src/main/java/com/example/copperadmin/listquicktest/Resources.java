package com.example.copperadmin.listquicktest;

import android.content.Context;

/**
 * Created by Copper Admin on 10/9/2014.
 */
public class Resources {

    private static Resources mResources;
    private Context mContext;


    private Resources(Context context){
        mContext = context;

    }

    public static Resources get(Context context){
        if(mResources == null){
            mResources = new Resources(context);
        }
        return mResources;
    }
}
