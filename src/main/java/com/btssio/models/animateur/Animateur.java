package com.btssio.models.animateur;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animateur")
public class Animateur {

    private String nom;
    private String prenom;
    private String adressePostale;
    private String arme;
    private String categorieEleveSuivie;

    // Constructeur
    public Animateur(String nom, String prenom, String adressePostale, String arme, String categorieEleveSuivie) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressePostale = adressePostale;
        this.arme = arme;
        this.categorieEleveSuivie = categorieEleveSuivie;
    }

    public Animateur() {
    }

    @XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlElement
    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    @XmlElement
    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    @XmlElement
    public String getCategorieEleveSuivie() {
        return categorieEleveSuivie;
    }

    public void setCategorieEleveSuivie(String categorieEleveSuivie) {
        this.categorieEleveSuivie = categorieEleveSuivie;
    }

    @Override
    public String toString() {
        return "Animateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adressePostale='" + adressePostale + '\'' +
                ", arme='" + arme + '\'' +
                ", categorieEleveSuivie='" + categorieEleveSuivie + '\'' +
                '}';
    }
}
