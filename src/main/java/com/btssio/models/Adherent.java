package com.btssio.models;

public class Adherent {

    // Attributs
    private String nom;
    private String prenom;

    // Constructeurs

    public Adherent(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters et setters

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

    // Méthodes

}