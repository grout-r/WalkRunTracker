package com.example.roman.walkruntracker;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Roman on 11/04/2017.
 */

public class StatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_activity);
        ArrayList<Integer> data = getIntent().getExtras().getIntegerArrayList("history");
        StatView custom = (StatView)findViewById(R.id.custom);
        custom.setValues(data);
    }

}
