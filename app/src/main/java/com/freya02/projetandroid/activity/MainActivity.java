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
import com.freya02.projetandroid.other.Database;
import com.freya02.projetandroid.other.Utilisateur;

public class MainActivity extends BaseActivity {
    private final Database h = new Database(MainActivity.this);

    private boolean isServiceStarted = false;

    private TextView email;
    private TextView motDePasse;

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

        this.motDePasse = findViewById(R.id.motDePasse);
        this.email = findViewById(R.id.email);
    }

    public void connexionCompte(View view) {
        String mail_ = email.getText().toString();
        String mdp_ = motDePasse.getText().toString();
        if (mail_.isEmpty() || mdp_.isEmpty()) {
            Toast.makeText(MainActivity.this, "Veuillez remplir les zones de saisies.", Toast.LENGTH_LONG).show();
        } else {
            Utilisateur user = h.getOneWithMail(mail_, mdp_);
            if (user != null) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("personne_nom", user.getNom());
                intent.putExtra("personne_prenom", user.getPrenom());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Connexion impossible.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void changerPage(View view) {
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void pageModifier(View view) {
        Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
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