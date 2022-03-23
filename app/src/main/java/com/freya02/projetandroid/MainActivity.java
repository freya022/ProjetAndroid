package com.freya02.projetandroid;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.FOREGROUND_SERVICE;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static class CheckPermHolder {
        private final Runnable onGranted, onRefused;

        private CheckPermHolder(Runnable onGranted, Runnable onRefused) {
            this.onGranted = onGranted;
            this.onRefused = onRefused;
        }
    }

    private int id = 0;
    private final Map<Integer, CheckPermHolder> permMap = new HashMap<>();

    private boolean isServiceStarted = false;

    public void checkPermission(Activity activity, Runnable onGranted, Runnable onRefused, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                int requestCode = ++id;

                permMap.put(requestCode, new CheckPermHolder(onGranted, onRefused));

                ActivityCompat.requestPermissions(activity, permissions, requestCode);

                return;
            }
        }

        //No missing permissions
        onGranted.run();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        CheckPermHolder holder = permMap.get(requestCode);

        if (holder != null) {
            if (grantResults.length == 0) {
                holder.onRefused.run();

                return;
            }

            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    holder.onRefused.run();

                    return;
                }
            }

            holder.onGranted.run();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission(this, this::tryStartService, () -> {
            Toast.makeText(this, "Requiert les permissions demandées", Toast.LENGTH_SHORT).show();

            finish();
        }, FOREGROUND_SERVICE, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_BACKGROUND_LOCATION, CAMERA);
    }

    private void tryStartService() {
        if (!isServiceStarted) {
            isServiceStarted = true;

            Intent intent = new Intent(this, LocationService.class);
            getApplicationContext().startForegroundService(intent);
        }
    }
}