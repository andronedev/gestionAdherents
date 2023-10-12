
package com.btssio.models;

public class Categorie {
    private String description;

    // Constructors, getters, and setters for the new attribute will be added here...

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String libelle;
    private double tarif;

    public Categorie(String libelle, double tarif) {
        this.libelle = libelle;
        this.tarif = tarif;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return libelle + " (" + tarif + "€)";
    }
}
