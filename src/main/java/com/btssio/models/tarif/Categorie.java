package com.btssio.models.tarif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Categorie {
    private String nom;
    private int anneeDebut;
    private int anneeFin;
    private double fraisInscription;
    private double fraisLicence;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(int anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public int getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(int anneeFin) {
        this.anneeFin = anneeFin;
    }

    public double getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(double fraisInscription) {
        this.fraisInscription = fraisInscription;
    }

    public double getFraisLicence() {
        return fraisLicence;
    }

    public void setFraisLicence(double fraisLicence) {
        this.fraisLicence = fraisLicence;
    }

    // getters et setters...
}
