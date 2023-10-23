package com.btssio.models.adherent;

import java.time.LocalDate;

public class Adherent {

    private String email;
    private String phoneNumber;
    private String nom;
    private String prenom;
    private String adresse;
    private LocalDate dateNaissance;
    private LocalDate dateInscription;
    private LocalDate dateFinAdhesion;
    private double montantCotisation;
    private double montantDon;
    private double montantTotal;

    // Constructeur
    public Adherent(String email, String phoneNumber, String nom, String prenom, String adresse, LocalDate dateNaissance,
                    LocalDate dateInscription, LocalDate dateFinAdhesion, double montantCotisation, double montantDon, double montantTotal) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.dateFinAdhesion = dateFinAdhesion;
        this.montantCotisation = montantCotisation;
        this.montantDon = montantDon;
        this.montantTotal = montantTotal;
    }

    // Getters et Setters pour chaque attribut

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public LocalDate getDateFinAdhesion() {
        return dateFinAdhesion;
    }

    public void setDateFinAdhesion(LocalDate dateFinAdhesion) {
        this.dateFinAdhesion = dateFinAdhesion;
    }

    public double getMontantCotisation() {
        return montantCotisation;
    }

    public void setMontantCotisation(double montantCotisation) {
        this.montantCotisation = montantCotisation;
    }

    public double getMontantDon() {
        return montantDon;
    }

    public void setMontantDon(double montantDon) {
        this.montantDon = montantDon;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + ", Email: " + email + ", Phone: " + phoneNumber + ", Adresse: " + adresse +
                ", Date de Naissance: " + dateNaissance + ", Date d'Inscription: " + dateInscription + ", Date de Fin d'Adhésion: " + dateFinAdhesion +
                ", Montant de Cotisation: " + montantCotisation + ", Montant de Don: " + montantDon + ", Montant Total: " + montantTotal;
    }
}
