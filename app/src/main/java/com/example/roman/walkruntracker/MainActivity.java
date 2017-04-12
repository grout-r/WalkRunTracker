package com.example.roman.walkruntracker;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speed_tv = (TextView) findViewById(R.id.speed_tv);
        distance_tv = (TextView) findViewById(R.id.distance_tv);
        average_tv = (TextView) findViewById(R.id.average_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        status_tv = (TextView) findViewById(R.id.status_tv);
        stat_bt = (Button) findViewById(R.id.stat_bt);
        start_bt = (Button) findViewById(R.id.start_bt);
        stop_bt = (Button) findViewById(R.id.stop_bt);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        stop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        stat_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            speed_tv.setText(String.valueOf(Math.round(location.getSpeed() * 3.6)) + " km/h");
                            Log.d("LOCATION", location.getLatitude() + " " +location.getLatitude());
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }
                    }
            );
        }
    }
    TextView speed_tv;
    TextView distance_tv;
    TextView average_tv;
    TextView time_tv;
    TextView status_tv;
    Button stat_bt;
    Button start_bt;
    Button stop_bt;
    LocationManager lm;
}
