package com.freya02.projetandroid.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;

public class ResetPasswordActivity extends AppCompatActivity {
    private TextView email;

    private final Database h = new Database(ResetPasswordActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        this.email = findViewById(R.id.email);
    }

    public void envoyerMail(View v) {
        String envoyerA = email.getText().toString();/* On utilise le mail pour l'envoie */

        Utilisateur existe = h.getuserWithoutMDP(envoyerA);
        if (existe == null) {
            Toast.makeText(ResetPasswordActivity.this, "Il n'ya pas de compte pour cette adresse.", Toast.LENGTH_LONG).show();
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