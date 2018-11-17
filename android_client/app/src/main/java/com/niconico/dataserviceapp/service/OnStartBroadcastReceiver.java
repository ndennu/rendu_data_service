package com.niconico.dataserviceapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OnStartBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, GeolocService.class));
    }
}
