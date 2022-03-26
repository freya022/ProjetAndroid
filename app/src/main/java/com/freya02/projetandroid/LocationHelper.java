package com.freya02.projetandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationHelper {
    private double oldLat = -1;
    private double oldLong = -1;

    @SuppressLint("MissingPermission")
    public void startLocationListener(Context context) {
        LocationManager lm = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new MyLocationListener(context);

        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                10000,
                100,
                locationListener);
    }

    public class MyLocationListener implements LocationListener {
        private final DistanceDatabase distanceDatabase;

        public MyLocationListener(Context context) {
            distanceDatabase = new DistanceDatabase(context);
        }

        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Log.i("gps", "changed");

//                System.out.println("loc.getAccuracy() = " + loc.getAccuracy());
//                System.out.println("loc.getAltitude() = " + loc.getAltitude());
//                System.out.println("loc.getLatitude() = " + loc.getLatitude());
//                System.out.println("loc.getLongitude() = " + loc.getLongitude());
//                System.out.println("loc.getSpeed() = " + loc.getSpeed());

                distanceDatabase.addDistance(oldLat, loc.getLatitude(), oldLong, loc.getLongitude());

                oldLat = loc.getLatitude();
                oldLong = loc.getLongitude();
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("gps", "disabled");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("gps", "enabled");
        }

        @Override
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            Log.i("gps", "status changed");
        }
    }
}
