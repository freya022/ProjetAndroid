package com.freya02.projetandroid.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.other.Database;
import com.freya02.projetandroid.other.Utilisateur;

public class ChangeInfoActivity extends AppCompatActivity {
    private final Database h = new Database(this);

    private TextView name;
    private TextView prenom;
    private TextView genre;
    private TextView age;
    private TextView poids;
    private TextView taille;
    private TextView loco;
    private Button valider;

    private EditText newNom;
    private EditText newPrenom;
    private EditText newAge;
    private EditText newTaille;
    private EditText newPoids;
    private Spinner spinnerGenre;
    private Spinner spinnerLocomotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);

        valider = (Button) findViewById(R.id.boutonValider);
        String email = preferences.getString("email", "");
        String mdp = preferences.getString("mdp", "");
        name = (TextView) findViewById(R.id.nomF);
        prenom = (TextView) findViewById(R.id.prenom);
        genre = (TextView) findViewById(R.id.genre);
        age = (TextView) findViewById(R.id.age);
        poids = (TextView) findViewById(R.id.poids);
        taille = (TextView) findViewById(R.id.taille);
        loco = (TextView) findViewById(R.id.loco);
        this.spinnerGenre = findViewById(R.id.spinnerGenre);
        this.spinnerLocomotion = findViewById(R.id.spinnerLoco);

        newNom = (EditText) findViewById(R.id.newNom);
        newPrenom = (EditText) findViewById(R.id.newPrenom);
        newAge = (EditText) findViewById(R.id.newAge);
        newPoids = (EditText) findViewById(R.id.newPoids);
        newTaille = (EditText) findViewById(R.id.newTaille);

        Utilisateur utilisateur = h.getOneWithMail(email, mdp);
        name.setText("Nom :"+utilisateur.getNom());
        prenom.setText("Prenom :"+utilisateur.getPrenom());
        genre.setText("Genre :"+utilisateur.getGenre());
        age.setText("Age :"+utilisateur.getAge());
        poids.setText("Poids :"+utilisateur.getPoids());
        taille.setText("Taille :"+utilisateur.getTaille());
        loco.setText(utilisateur.getLocomotion());

        newNom.setHint(utilisateur.getNom());
        newPrenom.setHint(utilisateur.getPrenom());
        newAge.setHint(""+utilisateur.getAge());
        newPoids.setHint(""+utilisateur.getPoids());
        newTaille.setHint(""+utilisateur.getTaille());
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInfo();
            }
        });
    }

    public void changeInfo(){

        SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String mdp = preferences.getString("mdp", "");

        Utilisateur utilisateur = h.getOneWithMail(email, mdp);

        boolean modifier = false;
        int age,poids,taille;
        String name,prenom;
        String text1 = spinnerGenre.getSelectedItem().toString();
        String text21 = spinnerLocomotion.getSelectedItem().toString();


        if(newNom.getText().toString().isEmpty()){
            name = utilisateur.getNom();
        } else {
            modifier = true;
            name = newNom.getText().toString();
        }
        if(newPrenom.getText().toString().isEmpty()){
             prenom = utilisateur.getPrenom();
        } else {
            modifier = true;
             prenom = newPrenom.getText().toString();
        }
        if(newAge.getText().toString().isEmpty()){
             age = utilisateur.getAge();
        } else {
            modifier = true;
             age = Integer.parseInt(newAge.getText().toString());
        }
        if(newPoids.getText().toString().isEmpty()){
             poids = utilisateur.getPoids();
        } else {
            modifier = true;
             poids = Integer.parseInt(newPoids.getText().toString());
        }
        if(newTaille.getText().toString().isEmpty()){
             taille = utilisateur.getTaille();
        } else {
            modifier = true;
             taille = Integer.parseInt(newTaille.getText().toString());
        }

        Utilisateur modifUser = new Utilisateur(name,prenom,email,mdp,text1,age,poids,taille,text21);
        h.updateUtilisateur(modifUser,email);
        if(modifier = true){
            Toast.makeText(this, "Vous avez modifié votre compte.", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}