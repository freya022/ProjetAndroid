package com.freya02.projetandroid.other;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText prenom;
    private EditText name;
    private EditText mail;
    private EditText mdp;
    private EditText age;
    private EditText poids;
    private EditText taille;
    private Spinner spinnerGenre;
    private Spinner spinnerLocomotion;

    private final Database h = new Database(CreateAccountActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo);

        this.prenom = findViewById(R.id.creerPrenom);
        this.name = findViewById(R.id.creerNom);
        this.mail = findViewById(R.id.creerMail);
        this.mdp = findViewById(R.id.creerMotPasse);
        this.age = findViewById(R.id.creerAge);
        this.poids = findViewById(R.id.creerPoids);
        this.taille = findViewById(R.id.creerTaille);
        this.spinnerGenre = findViewById(R.id.spinner);
        this.spinnerLocomotion = findViewById(R.id.spinnerLoco);
    }

    public void onCreateClicked(View view) {
        String text1 = spinnerGenre.getSelectedItem().toString();
        String text21 = spinnerLocomotion.getSelectedItem().toString();
        String email_ = mail.getText().toString();

        Utilisateur existe = h.getuserWithoutMDP(email_);

        if (existe == null) {
            if (!name.getText().toString().isEmpty() && !prenom.getText().toString().isEmpty() && !mail.getText().toString().isEmpty() && !mdp.getText().toString().isEmpty() && !text1.isEmpty() && !age.getText().toString().isEmpty() && !poids.getText().toString().isEmpty() && !taille.getText().toString().isEmpty() && !text21.isEmpty()) {
                Utilisateur utilisateur = new Utilisateur(name.getText().toString(), prenom.getText().toString(), mail.getText().toString(), mdp.getText().toString(), text1, Integer.parseInt(age.getText().toString()), Integer.parseInt(poids.getText().toString()), Integer.parseInt(taille.getText().toString()), text21);
                h.insertUtilisateur(utilisateur);

                Toast.makeText(this, "Félicitation " + prenom.getText().toString() + " avez créer votre compte", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Il faut remplir toutes les zones de saisies.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Il existe déjà un compte avec cette adresse.", Toast.LENGTH_LONG).show();
        }
    }

    public void onCancelClicked(View v) {
        finish();
    }
}