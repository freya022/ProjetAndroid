package com.freya02.projetandroid;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.freya02.projetandroid.activity.R;

public class LocationService extends Service {
    private boolean isServiceStarted = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String channelId = "Health and Me";
        Notification.Builder builder = new Notification.Builder(this, channelId)
                .setOngoing(false)
                .setContentText("Localisation active en arrière plan")
                .setSmallIcon(R.mipmap.logo);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_LOW);
        channel.setDescription(channelId);
        channel.setSound(null, null);

        notificationManager.createNotificationChannel(channel);

        startForeground(1, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isServiceStarted) {
            return START_STICKY;
        }

        isServiceStarted = true;

        new LocationHelper().startLocationListener(getApplicationContext());

        return START_STICKY;
    }
}
