package com.niconico.dataserviceapp.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.niconico.dataserviceapp.Consts;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.WebServices.IAPIListener;
import com.niconico.dataserviceapp.WebServices.UserProvider;

public class GeolocService extends Service {

    // Delay between updates set to 15 minutes
    private static long DELAY = 1000 * 60 * 15;

    private long userId;

    private final Handler handler = new Handler();

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            getCurrentLocation();
            handler.postDelayed(this, GeolocService.DELAY);
        }
    };

    private LocationManager locationManager;
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            Double longitude = location.getLongitude();
            Double latitude = location.getLatitude();

            Long currentStoreId = getSharedPreferences(Consts.SHARED_PREFERENCES, Context.MODE_PRIVATE).getLong(Consts.PREF_CURRENT_STORE_ID, 0);

            updateLocation(userId, latitude, longitude, currentStoreId);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        userId = getSharedPreferences(Consts.SHARED_PREFERENCES, Context.MODE_PRIVATE).getLong(Consts.PREF_USER_ID, 0);
        Log.d("CURRENT_USER_ID", Long.toString(userId));

        start();
        return Service.START_NOT_STICKY;
    }

    private void start() {
        handler.post(runnableCode);
    }

    private void stop() {
        handler.removeCallbacks(runnableCode);
    }

    /**
     *  Update location in database
     */
    private void updateLocation(long idUser, Double latitude, Double longitude, long currentStoreId) {
        UserProvider.getInstance().updateLocation(idUser, latitude, longitude, currentStoreId, new IAPIListener<APIResult<Long>>() {
            @Override
            public void onSuccess(APIResult<Long> response) {
                Log.d("UPDATE_LOCATION_SUCCESS", response.data.toString());

                getSharedPreferences(Consts.SHARED_PREFERENCES, MODE_PRIVATE)
                        .edit()
                        .putLong(Consts.PREF_CURRENT_STORE_ID, response.data)
                        .apply();

                locationManager.removeUpdates(locationListener);
            }

            @Override
            public void onError(String s) {
                Log.e("UPDATE_LOCATION_ERROR", s);
                locationManager.removeUpdates(locationListener);
            }
        });
    }

    /**
     * Get current location
     */
    private void getCurrentLocation() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.e("LOCATION_ERROR", "Need permission !");
                return;
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }
}
