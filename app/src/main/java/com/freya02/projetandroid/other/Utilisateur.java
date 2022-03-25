package com.freya02.projetandroid.other;

public class Utilisateur {
    private final int id;
    private final String nom;
    private final String prenom;
    private final String email;
    private final String mdp;
    private final String genre;
    private final int age;
    private final int poids;
    private final int taille;
    private final String locomotion;

    public Utilisateur(int id, String nom, String prenom, String email, String mdp, String genre, int age, int poids, int taille, String locomotion) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.genre = genre;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.locomotion = locomotion;
    }

    public Utilisateur(String nom, String prenom, String email, String mdp, String genre, int age, int poids, int taille, String locomotion) {
        this.id = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.genre = genre;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.locomotion = locomotion;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", genre='" + genre + '\'' +
                ", age=" + age +
                ", poids=" + poids +
                ", taille=" + taille +
                ", locomotion='" + locomotion + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getGenre() {
        return genre;
    }

    public int getAge() {
        return age;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public String getLocomotion() {
        return locomotion;
    }
}
