package com.freya02.projetandroid.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView name = findViewById(R.id.nom2);
        final TextView prenom = findViewById(R.id.prenom2);

        name.setText(getIntent().getStringExtra("personne_nom"));
        prenom.setText(getIntent().getStringExtra("personne_prenom"));
    }

    public void onInfoPageClicked(View v) {
        Intent intent = new Intent(this, infoSurNous.class);

        startActivity(intent);
    }

    public void onStatsPageClicked(View v) {
        Intent intent = new Intent(this, Statistique.class);

        startActivity(intent);
    }

    public void onAdvicesPageClicked(View v) {
        Intent intent = new Intent(this, AdvicesActivity.class);

        startActivity(intent);
    }
}