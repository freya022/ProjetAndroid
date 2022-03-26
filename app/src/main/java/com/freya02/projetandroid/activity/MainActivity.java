package com.freya02.projetandroid.activity;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.FOREGROUND_SERVICE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.freya02.projetandroid.LocationService;
import com.freya02.projetandroid.PermissionUtils;

public class MainActivity extends BaseActivity {
    private boolean isServiceStarted = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.handlePermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PermissionUtils.checkPermission(this, () -> {
            final TextView name = findViewById(R.id.nom2);
            final TextView prenom = findViewById(R.id.prenom2);

            name.setText(getIntent().getStringExtra("personne_nom"));
            prenom.setText(getIntent().getStringExtra("personne_prenom"));

            tryStartService();
        }, () -> {
            Toast.makeText(this, "Requiert les permissions demandées", Toast.LENGTH_SHORT).show();

            finish();
        }, FOREGROUND_SERVICE, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_BACKGROUND_LOCATION, CAMERA);
    }

    public void onTodayFoodClicked(View v) {
        Intent intent = new Intent(this, TodayFoodActivity.class);

        startActivity(intent);
    }

    public void onInfoPageClicked(View v) {
        Intent intent = new Intent(this, InfoActivity.class);

        startActivity(intent);
    }

    public void onStatsPageClicked(View v) {
        Intent intent = new Intent(this, StatisticsActivity.class);

        startActivity(intent);
    }

    public void onAdvicesPageClicked(View v) {
        Intent intent = new Intent(this, AdvicesActivity.class);

        startActivity(intent);
    }

    private void tryStartService() {
        if (!isServiceStarted) {
            isServiceStarted = true;

            Intent intent = new Intent(this, LocationService.class);
            getApplicationContext().startForegroundService(intent);
        }
    }
}