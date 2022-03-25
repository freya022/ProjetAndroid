package com.freya02.projetandroid.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.MainActivity;
import com.freya02.projetandroid.R;

public class creerCompte extends AppCompatActivity {

    public EditText prenom;
    public EditText name;
    public EditText mail;
    public EditText mdp;
    public EditText age;
    public EditText poids;
    public EditText taille;
    public Spinner spinnerGenre;
    public Spinner spinnerLocomotion;
    public Button creation;
    public Button retour;
    Helper h = new Helper(creerCompte.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        this.prenom = (EditText) findViewById(R.id.creerPrenom);
        this.name = (EditText) findViewById(R.id.creerNom);
        this.mail = (EditText) findViewById(R.id.creerMail);
        this.mdp = (EditText) findViewById(R.id.creerMotPasse);
        this.age = (EditText) findViewById(R.id.creerAge);
        this.poids = (EditText) findViewById(R.id.creerPoids);
        this.taille = (EditText) findViewById(R.id.creerTaille);
        this.spinnerGenre = (Spinner) findViewById(R.id.spinner);
        this.spinnerLocomotion = (Spinner) findViewById(R.id.spinnerLoco);
        String text2 = spinnerLocomotion.getSelectedItem().toString();
        this.creation = (Button) findViewById(R.id.creerCompte);
        this.retour = (Button) findViewById(R.id.annuler);

        this.retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerPage();
            }
        });

        this.creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = spinnerGenre.getSelectedItem().toString();
                String text2 = spinnerLocomotion.getSelectedItem().toString();
                String email_ = mail.getText().toString();
                Utilisateur existe = h.getuserWithoutMDP(email_);
                if(existe==null){
                    if(!name.getText().toString().isEmpty() && !prenom.getText().toString().isEmpty() && !mail.getText().toString().isEmpty() && !mdp.getText().toString().isEmpty() && !text1.toString().isEmpty() && !age.getText().toString().isEmpty() && !poids.getText().toString().isEmpty() && !taille.getText().toString().isEmpty() && !text2.toString().isEmpty()){
                        Utilisateur utilisateur = new Utilisateur(name.getText().toString(),prenom.getText().toString(),mail.getText().toString(),mdp.getText().toString(),text1.toString(),Integer.parseInt(age.getText().toString()),Integer.parseInt(poids.getText().toString()),Integer.parseInt(taille.getText().toString()),text2.toString());
                        h.insertUtilisateur(utilisateur);
                        Toast.makeText(creerCompte.this, "Félicitation "+prenom.getText().toString()+" avez créer votre compte", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(creerCompte.this, "Il faut remplir toutes les zones de saisies.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(creerCompte.this, "Il existe déjà un compte avec cette adresse.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void changerPage(){
        //h.getOne(1);
        //Cursor c = h.getAll();
        //if(c==null){
        //    Toast.makeText(creerCompte.this, "Aucun élément ! ", Toast.LENGTH_LONG).show();
        //}
        String text1 = spinnerGenre.getSelectedItem().toString();
        String text2 = spinnerLocomotion.getSelectedItem().toString();
        System.out.println(this.prenom.getText() + " " + this.name.getText() + " " + this.mail.getText() + " " + this.mdp.getText() + " " + this.age.getText() + " " + this.poids.getText() + " " + this.taille.getText() + " " + text1 + " " + text2);
        Intent intent = new Intent(creerCompte.this, MainActivity.class);
        startActivity(intent);
    }
}