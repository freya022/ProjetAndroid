package com.freya02.projetandroid;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private boolean isServiceStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityResultLauncher<String[]> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                if (result.containsValue(false)) {
                    Toast.makeText(this, "Requiert la localisation", Toast.LENGTH_SHORT).show();

                    finish();
                }
            });

            requestPermissionLauncher.launch(new String[]{ACCESS_COARSE_LOCATION, ACCESS_BACKGROUND_LOCATION, ACCESS_FINE_LOCATION});
        }

        if (!isServiceStarted) {
            isServiceStarted = true;

            Intent intent = new Intent(this, LocationService.class);
            System.out.println("getApplicationContext().startForegroundService(intent) = " + getApplicationContext().startForegroundService(intent));
        }
    }
}