package com.niconico.dataserviceapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.niconico.dataserviceapp.WebServices.IAPIListener;
import com.niconico.dataserviceapp.WebServices.UserAuthProvider;
import com.niconico.dataserviceapp.service.GeolocService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.emailTxtConnection)
    TextInputEditText emailInput;
    @BindView(R.id.passwordTxtConnection)
    TextInputEditText passwordInput;

    private static final int LOCATION_PERMISSION_REQUEST = 1736;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupLocation();

        if (getUserId()!= 0)
            navigateToSearchActivity();
    }

    // Callback popup ask for location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                } else {
                    setupLocation();
                }
            }
        }
    }

    @OnClick(R.id.connectionBtn)
    public void connectionClick() {
        UserAuthProvider.getInstance().login(emailInput.getText().toString(), passwordInput.getText().toString(), new IAPIListener<Long>() {
            @Override
            public void onSuccess(Long response) {
                Log.d("TEST", "data: " + response);
                getSharedPreferences(Consts.SHARED_PREFERENCES, MODE_PRIVATE)
                        .edit()
                        .putLong(Consts.PREF_USER_ID, response)
                        .apply();

                navigateToSearchActivity();
            }

            @Override
            public void onError(String s) {
                Log.d("TEST", "error => " + s);
            }
        });
    }

    private long getUserId() {
        return getSharedPreferences(Consts.SHARED_PREFERENCES, MODE_PRIVATE).getLong(Consts.PREF_USER_ID, 0);
    }

    private void navigateToSearchActivity() {
        startActivity(new Intent(this, SearchActivity.class));
        supportFinishAfterTransition();
    }

    /**
     * Ask for location permission
     */
    private void setupLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                        LOCATION_PERMISSION_REQUEST);
            } else {
                // Request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                        LOCATION_PERMISSION_REQUEST);
            }
        } else {
            // Permission has already been granted
        }
    }
}
