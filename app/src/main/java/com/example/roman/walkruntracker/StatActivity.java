package com.example.roman.walkruntracker;

import android.app.Activity;
import android.app.IntentService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        data = new ArrayList<Integer>();
        float time = getIntent().getExtras().getFloat("time");
        float distance = getIntent().getExtras().getFloat("distance");
        float speed = getIntent().getExtras().getFloat("speed");
        time_tv = (TextView)findViewById(R.id.stat_time);
        distance_tv = (TextView)findViewById(R.id.stat_distance_tv);
        speed_tv = (TextView)findViewById(R.id.stat_average_tv);
        time_tv.setText(String.valueOf(time));
        distance_tv.setText(String.valueOf(distance));
        speed_tv.setText(String.valueOf(speed));
        back = (Button)findViewById(R.id.back_bt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        data.add(10);
        data.add(18);
        data.add(52);
        data.add(23);
        data.add(12);
        data.add(18);
        data.add(5);
        data.add(44);

        custom.setValues(data);
    }

    TextView time_tv;
    TextView distance_tv;
    TextView speed_tv;
    Button back;
}
