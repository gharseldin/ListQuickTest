package com.example.copperadmin.listquicktest;


import android.support.v4.app.Fragment;

public class ListActivity extends SingleFragmentActivity {

    public Fragment createFragement(){
        return new ListTestFragment();
    }
}
