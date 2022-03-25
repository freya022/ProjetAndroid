package com.freya02.projetandroid.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;

public class Acc extends AppCompatActivity {

    public Button conseil;
    public Button infoSurNous;
    public Button statistiques;
    public TextView name;
    public TextView prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        this.name = (TextView) findViewById(R.id.nom2);
        this.prenom = (TextView) findViewById(R.id.prenom2);
        conseil = (Button) findViewById(R.id.conseil);
        this.infoSurNous = (Button) findViewById(R.id.infoSurNous);
        this.statistiques = (Button) findViewById(R.id.statistiques);
        statistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerPageStat();
            }
        });
        infoSurNous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerPageInfo();
            }
        });
        conseil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerPageCons();
            }
        });

        String name_ = getIntent().getStringExtra("personne_nom");
        String prenom_ = getIntent().getStringExtra("personne_prenom");
        this.name.setText(name_);
        this.prenom.setText(prenom_);
    }

    private void changerPageInfo(){
        Intent intent = new Intent(Acc.this,infoSurNous.class);
        startActivity(intent);
    }
    private void changerPageStat(){
        Intent intent = new Intent(Acc.this,Statistique.class);
        startActivity(intent);
    }
    private void changerPageCons(){
        Intent intent = new Intent(Acc.this,Conseils.class);
        startActivity(intent);
    }
}