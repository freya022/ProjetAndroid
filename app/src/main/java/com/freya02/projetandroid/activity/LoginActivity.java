package com.freya02.projetandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.freya02.projetandroid.other.Database;
import com.freya02.projetandroid.other.Utilisateur;

public class LoginActivity extends BaseActivity {
    private final Database h = new Database(this);

    private TextView email;
    private TextView motDePasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.motDePasse = findViewById(R.id.motDePasse);
        this.email = findViewById(R.id.email);
    }

    public void connexionCompte(View view) {
        String mail_ = email.getText().toString();
        String mdp_ = motDePasse.getText().toString();
        if (mail_.isEmpty() || mdp_.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir les zones de saisies.", Toast.LENGTH_LONG).show();
        } else {
            Utilisateur user = h.getOneWithMail(mail_, mdp_);
            if (user != null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("personne_nom", user.getNom());
                intent.putExtra("personne_prenom", user.getPrenom());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Connexion impossible.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void changerPage(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void pageModifier(View view) {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }
}
