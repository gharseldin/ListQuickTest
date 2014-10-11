package com.example.copperadmin.listquicktest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListTestFragment extends Fragment {

    private static final String API_KEY = "b6a97fd130cc4366";
    private static final String FEATURES = "hourly10day";
    private static final String QUERY_ZIP_CODE = "75251";
    private static final String FORMAT_JSON = "json";
    private static final String TAG = "ListFragment";

    private ListView mListView;
    private WeatherListAdapter mListAdapter;

    public ListTestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_list, container, false);

        mListView = (ListView) view.findViewById(R.id.listView);
        getUrlJson("http://api.wunderground.com/api/" + API_KEY + "/" + FEATURES + "/q/" + QUERY_ZIP_CODE + "." + FORMAT_JSON);

        return view;
    }

    private class WeatherListAdapter extends ArrayAdapter<WeatherFeed> {

        private class ViewHolder {
            TextView date;
            TextView Temperature;
            ImageView icon;
        }

        public WeatherListAdapter(ArrayList<WeatherFeed> resources) {

            super(getActivity(), 0, resources);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            WeatherFeed weatherFeed = getItem(position);

            ViewHolder viewHolder;

            // If we weren't given a view, inflate one
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.weather_list_unit_item, parent, false);
                viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                viewHolder.Temperature = (TextView) convertView.findViewById(R.id.temperature);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(viewHolder);

            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            String month = weatherFeed.getDateOfFeed().getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.US);
            String day = weatherFeed.getDateOfFeed().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
            int hour = weatherFeed.getDateOfFeed().get(Calendar.HOUR_OF_DAY);
            int dayNumber = weatherFeed.getDateOfFeed().get(Calendar.DAY_OF_MONTH);

            viewHolder.date.setText(hour+":00  "+day+" "+dayNumber+" "+month);
            viewHolder.Temperature.setText(weatherFeed.getFahrenheitTemperature()+"F  |  "+weatherFeed.getCelsiusTemperature()+"C");

            int drawable;

            if(weatherFeed.getIconUrl().equals("clear")){
                drawable=R.drawable.clear;
            }else if(weatherFeed.getIconUrl().contains("tstorms")){
                drawable=R.drawable.storm;
            }else if((weatherFeed.getIconUrl().contains("rain"))){
                drawable=R.drawable.rain;
            }else if((weatherFeed.getIconUrl().contains("chancerain"))){
                drawable=R.drawable.rain;
            }else if((weatherFeed.getIconUrl().contains("partlycloudy"))){
                drawable=R.drawable.somecloud;
            }else if((weatherFeed.getIconUrl().contains("mostlycloudy"))){
                drawable=R.drawable.cloudy;
            }else if((weatherFeed.getIconUrl().contains("cloudy"))){
                drawable=R.drawable.cloudy;
            }else if((weatherFeed.getIconUrl().contains("chancetstorms"))){
                drawable=R.drawable.storm;
            }else{
                drawable = R.drawable.clear;
            }

            viewHolder.icon.setImageResource(drawable);

            return convertView;
        }
    }

    public void getUrlJson(String url) {

        JSONObject JSONObjectresponse;
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, response.toString());
                Feeds.get(getActivity()).setWeatherFeeds(response);

                ArrayList<String> icons =new ArrayList<String>();

                  mListAdapter = new WeatherListAdapter(Feeds.get(getActivity()).getWeatherFeeds());
                  mListView.setAdapter(mListAdapter);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());

            }

        });

        VolleyApplication.getInstance().getRequestQueue().add(request);
    }
}