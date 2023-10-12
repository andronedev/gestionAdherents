
package com.btssio.models;

public class Club {
    private int numberOfMembers;
    private Categorie category;

    // Constructors, getters, and setters for the new attributes will be added here...

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public Categorie getCategory() {
        return category;
    }

    public void setCategory(Categorie category) {
        this.category = category;
    }

    private String nom;
    private String adresse;

    public Club(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

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

    @Override
    public String toString() {
        return nom + " (" + adresse + ")";
    }
}
