package com.btssio.models.tarif;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Categorie")
public class Categorie {
    private String nom;
    private int anneeDebut;
    private int anneeFin;
    private int fraisInscription;
    private int fraisLicence;

    @XmlElement(name = "Nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name = "AnneeDebut")
    public int getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(int anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    @XmlElement(name = "AnneeFin")
    public int getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(int anneeFin) {
        this.anneeFin = anneeFin;
    }

    @XmlElement(name = "FraisInscription")
    public int getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(int fraisInscription) {
        this.fraisInscription = fraisInscription;
    }

    @XmlElement(name = "FraisLicence")
    public int getFraisLicence() {
        return fraisLicence;
    }

    public void setFraisLicence(int fraisLicence) {
        this.fraisLicence = fraisLicence;
    }
}
