package com.freya02.projetandroid;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.FOREGROUND_SERVICE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.other.Helper;
import com.freya02.projetandroid.other.HomeActivity;
import com.freya02.projetandroid.other.OublierMDP;
import com.freya02.projetandroid.other.Utilisateur;
import com.freya02.projetandroid.other.creerCompte;

public class MainActivity extends AppCompatActivity {
    private boolean isServiceStarted = false;

    public TextView email;
    public TextView motDePasse;
    public Button connexion;
    public Button creer;
    public Button modifier;
    Helper h = new Helper(MainActivity.this);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.handlePermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo);

        PermissionUtils.checkPermission(this, this::tryStartService, () -> {
            Toast.makeText(this, "Requiert les permissions demandées", Toast.LENGTH_SHORT).show();

            finish();
        }, FOREGROUND_SERVICE, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_BACKGROUND_LOCATION, CAMERA);

        this.motDePasse = (TextView) findViewById(R.id.motDePasse);
        this.email = (TextView) findViewById(R.id.email);
        this.modifier = (Button) findViewById(R.id.modifierMDP);
        this.connexion = (Button) findViewById(R.id.connexion);
        this.creer = (Button) findViewById(R.id.creer);

        this.connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { connexionCompte(email); }
        });
        this.creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerPage();
            }
        });
        this.modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageModifier();
            }
        });
    }

    private void connexionCompte(TextView mail) {
        String mail_ = email.getText().toString();
        String mdp_ = motDePasse.getText().toString();
        if(mail_.isEmpty() || mdp_.isEmpty()){
            Toast.makeText(MainActivity.this, "Veuillez remplir les zones de saisies.", Toast.LENGTH_LONG).show();
        } else {
            Utilisateur user = h.getOneWithMail(mail_,mdp_);
            if(user!=null){
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("personne_nom", user.getNom());
                intent.putExtra("personne_prenom", user.getPrenom());
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Connexion impossible.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void changerPage(){
        Intent intent = new Intent(MainActivity.this, creerCompte.class);
        startActivity(intent);
    }

    private void pageModifier(){
        Intent intent = new Intent(MainActivity.this, OublierMDP.class);
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