package com.example.roman.walkruntracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speed_tv = (TextView) findViewById(R.id.speed_tv);
        distance_tv = (TextView) findViewById(R.id.distance_tv);
        average_tv = (TextView) findViewById(R.id.average_tv);
        status_tv = (TextView) findViewById(R.id.status_tv);
        stat_bt = (Button) findViewById(R.id.stat_bt);
        start_bt = (Button) findViewById(R.id.start_bt);
        stop_bt = (Button) findViewById(R.id.stop_bt);
        chrono = (Chronometer) findViewById(R.id.chrono);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        previousLongitude = 0.0;
        previousLatitude = 0.0;
        buffer = new float[3];
        totalDistance = 0f;
        avgCounter = 0;
        totalSpeed = 0;
        df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);

        ll = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                if (previousLatitude != 0.0 || previousLongitude != 0.0)
                {
                    Location.distanceBetween(previousLatitude, previousLongitude, location.getLatitude(), location.getLongitude(), buffer);
                    totalDistance = totalDistance + buffer[0];
                }

                totalSpeed += location.getSpeed() * 3.6;
                avgCounter += 1;
                average_tv.setText(String.format(getResources().getString(R.string.kmh_unit), Math.round(totalSpeed / avgCounter)));


                distance_tv.setText(String.format(getResources().getString(R.string.km_unit), df.format(totalDistance / 1000)));
                previousLatitude = location.getLatitude();
                previousLongitude = location.getLongitude();
                speed_tv.setText(String.format(getResources().getString(R.string.kmh_unit), Math.round(location.getSpeed() * 3.6)));
            }
            @Override
            public void onProviderDisabled(String provider) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
        };

        start_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission( c, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission( c, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, ll);
                }
                else
                {
                    Toast.makeText(c, "Please grant access to G.P.S.", Toast.LENGTH_LONG).show();
                }
                chrono.setVisibility(View.VISIBLE);
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                stat_bt.setEnabled(false);
                previousLongitude = 0.0;
                previousLatitude = 0.0;
                totalDistance = 0f;
                avgCounter = 0;
                totalSpeed = 0;
            }
        });

        stop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.removeUpdates(ll);
                previousLongitude = 0.0;
                previousLatitude = 0.0;
                chrono.stop();
                stat_bt.setEnabled(true);
            }
        });

        stat_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatActivity.class);
                //intent.putExtra();
                startActivity(intent);
            }
        });

    }

    Context c = this;
    TextView speed_tv;
    TextView distance_tv;
    TextView average_tv;
    TextView status_tv;
    Chronometer chrono;
    Button stat_bt;
    Button start_bt;
    Button stop_bt;
    LocationManager lm;
    LocationListener ll;
    DecimalFormat df;

    double previousLatitude;
    double previousLongitude;
    float[] buffer;

    float totalDistance;
    float totalSpeed;
    float avgCounter;
}
