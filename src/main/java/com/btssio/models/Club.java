package com.btssio.models;

public class Club {

    // Attributs
    private String nom;
    private String adresse;

    // Constructeurs

    public Club(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }


    // Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Méthodes

}