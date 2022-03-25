package com.freya02.projetandroid.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;

public class OublierMDP extends AppCompatActivity {

    public TextView email;
    public Button boutonEnvoyer;
    Database h = new Database(OublierMDP.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oublier_mdp);

        email = findViewById(R.id.email);
        boutonEnvoyer = findViewById(R.id.envoyer);

        boutonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envoyerMail();
            }
        });
    }

    public void envoyerMail(){
        String envoyerA = email.getText().toString(); /* On utilise le mail pour l'envoie */
        Utilisateur existe = h.getuserWithoutMDP(envoyerA);
        if(existe==null){
            Toast.makeText(OublierMDP.this, "Il n'ya pas de compte pour cette adresse.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);  /* On crée un nouveau Intent */
            intent.putExtra(Intent.EXTRA_EMAIL, envoyerA); /* On envoie les données */
            intent.putExtra(Intent.EXTRA_SUBJECT, "Vous avez oubliez votre mot de passe."); /* On envoie les données */
            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour, vous avez oublié votre mot de passe. Pas de soucis voiçi votre mot de passe.\n\n MOT DE PASSE : " + existe.getMdp()); /* On envoie les données et on utilise le 'name' en fin de message*/
            intent.setType("message/rfc822"); /* Permet d'envoyer un e-mail */
            startActivity(Intent.createChooser(intent, "Choose an email client")); /* On change d'Intent */
        }
    }


}