package com.btssio.models.adherent;


import com.btssio.models.utils.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlRootElement(name = "adherent")
public class Adherent {

    private String email;
    private String telephone;
    private String nom;
    private String prenom;
    private String adresse;
    private LocalDate dateNaissance;
    private LocalDate dateInscription;
    private LocalDate dateFinAdhesion;
    private double montantCotisation;
    private double montantDon;
    private double montantTotal;
    private String categorieName;


    // Constructeur
    public Adherent(String email, String telephone, String nom, String prenom, String adresse, LocalDate dateNaissance,
                    LocalDate dateInscription, LocalDate dateFinAdhesion, double montantCotisation, double montantDon, double montantTotal, String categorieName) {
        this.email = email;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.dateFinAdhesion = dateFinAdhesion;
        this.montantCotisation = montantCotisation;
        this.montantDon = montantDon;
        this.montantTotal = montantTotal;
        this.categorieName = categorieName;
    }

    public Adherent() {
    }

    // Getters et Setters pour chaque attribut
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String phoneNumber) {
        this.telephone = phoneNumber;
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
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateFinAdhesion() {
        return dateFinAdhesion;
    }

    public void setDateFinAdhesion(LocalDate dateFinAdhesion) {
        this.dateFinAdhesion = dateFinAdhesion;
    }
    @XmlElement
    public double getMontantCotisation() {
        return montantCotisation;
    }

    public void setMontantCotisation(double montantCotisation) {
        this.montantCotisation = montantCotisation;
    }
    @XmlElement
    public double getMontantDon() {
        return montantDon;
    }

    public void setMontantDon(double montantDon) {
        this.montantDon = montantDon;
    }
    @XmlElement
    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    @XmlElement
    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorie) {
        this.categorieName = categorie;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + ", Email: " + email + ", Phone: " + telephone + ", Adresse: " + adresse +
                ", Date de Naissance: " + dateNaissance + ", Date d'Inscription: " + dateInscription + ", Date de Fin d'Adhésion: " + dateFinAdhesion +
                ", Montant de Cotisation: " + montantCotisation + ", Montant de Don: " + montantDon + ", Montant Total: " + montantTotal;
    }
}
