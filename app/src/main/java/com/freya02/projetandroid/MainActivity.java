package com.freya02.projetandroid;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.FOREGROUND_SERVICE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isServiceStarted = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.handlePermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtils.checkPermission(this, this::tryStartService, () -> {
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