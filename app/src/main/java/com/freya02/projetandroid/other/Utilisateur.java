package com.freya02.projetandroid.other;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String genre;
    private int age;
    private int poids;
    private int taille;
    private String locomotion;

    public Utilisateur(int id,String nom, String prenom, String email, String mdp, String genre, int age, int poids, int taille, String locomotion) {
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
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setMdp(mdp);
        this.setGenre(genre);
        this.setAge(age);
        this.setPoids(poids);
        this.setTaille(taille);
        this.setLocomotion(locomotion);
    }

    public Utilisateur(){}



    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", mdp='" + getMdp() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", age=" + getAge() +
                ", poids=" + getPoids() +
                ", taille=" + getTaille() +
                ", locomotion='" + getLocomotion() + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getLocomotion() {
        return locomotion;
    }

    public void setLocomotion(String locomotion) {
        this.locomotion = locomotion;
    }
}
