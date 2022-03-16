package com.freya02.projetandroid;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.google.android.gms.location.LocationResult;

public class MyLocationListener extends IntentService {
    public MyLocationListener() {
        super("nom");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("bind");

        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("intent");

        LocationResult locationResult = LocationResult.extractResult(intent);

        System.out.println();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("start");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        System.out.println("create");

        super.onCreate();
    }
}
