package com.btssio.models.adherent;


import com.btssio.models.utils.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.List;

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
    private double montantAdhesion;
    private double montantOption;
    private double montantTotal;
    private String categorieName;

    private String nomNaissance;
    private String genre; // Peut être "Masculin" ou "Féminin", à déterminer en fonction de la logique de l'application
    private String paysVilleNaissance;
    private String nationalite;
    private String codePostal;
    private String ville;
    private String deuxiemeTelephone;
    // Supposant que "armes" représente un ensemble d'armes préférées ou utilisées par l'adhérent
    private List<String> armes; // Pourrait être une liste d'enum ou de String selon les armes disponibles
    private String pratique; // Peut être "Loisir" ou "Compétition"
    private String lateralite; // Peut être "gaucher" ou "droitier"

    private String responsableLegal;
    private boolean sansAssurance;
    private boolean avecAssurance;
    private boolean carte10Seances;
    int nbAdherents;

    // Constructeur
    public Adherent(String email, String telephone, String nom, String prenom, String adresse, LocalDate dateNaissance,
                    LocalDate dateInscription, LocalDate dateFinAdhesion, double montantAdhesion, double montantDon,
                    double montantTotal, String categorieName, String nomNaissance, String genre, String PaysVilleNaissance,
                    String nationalite, String codePostal, String ville, String deuxiemeTelephone,
                    List<String> armes, String pratique, String lateralite, String responsableLegal , boolean sansAssurance, boolean avecAssurance, boolean carte10Seances , int nbAdherents) {
        this.email = email;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.dateFinAdhesion = dateFinAdhesion;
        this.montantAdhesion = montantAdhesion;
        this.montantOption = montantOption;
        this.montantTotal = montantTotal;
        this.categorieName = categorieName;

        // Initialiser les nouveaux champs avec les paramètres
        this.nomNaissance = nomNaissance;
        this.genre = genre;
        this.paysVilleNaissance = PaysVilleNaissance;
        this.nationalite = nationalite;
        this.codePostal = codePostal;
        this.ville = ville;
        this.deuxiemeTelephone = deuxiemeTelephone;
        this.armes = armes;
        this.pratique = pratique;
        this.lateralite = lateralite;
        this.responsableLegal = responsableLegal;
        this.sansAssurance = sansAssurance;
        this.avecAssurance = avecAssurance;
        this.carte10Seances = carte10Seances;
        this.nbAdherents = nbAdherents;
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
    public double getMontantAdhesion() {
        return montantAdhesion;
    }

    public void setMontantAdhesion(double montantAdhesion) {
        this.montantAdhesion = montantAdhesion;
    }

    @XmlElement
    public double getMontantOption() {
        return montantOption;
    }

    public void setMontantOption(double montantOption) {
        this.montantOption = montantOption;
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

    @XmlElement
    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement
    public String getPaysVilleNaissance() {
        return paysVilleNaissance;
    }

    public void setPaysVilleNaissance(String paysVilleNaissance) {
        this.paysVilleNaissance = paysVilleNaissance;
    }

    @XmlElement
    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @XmlElement
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @XmlElement
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @XmlElement
    public String getDeuxiemeTelephone() {
        return deuxiemeTelephone;
    }

    public void setDeuxiemeTelephone(String deuxiemeTelephone) {
        this.deuxiemeTelephone = deuxiemeTelephone;
    }

    @XmlElement
    public List<String> getArmes() {
        return armes;
    }

    public void setArmes(List<String> armes) {
        this.armes = armes;
    }

    @XmlElement
    public String getPratique() {
        return pratique;
    }

    public void setPratique(String pratique) {
        this.pratique = pratique;
    }

    @XmlElement
    public String getLateralite() {
        return lateralite;
    }

    public void setLateralite(String lateralite) {
        this.lateralite = lateralite;
    }

    @XmlElement
    public String getResponsableLegal() {
        return responsableLegal;
    }

    public void setResponsableLegal(String responsableLegal) {
        this.responsableLegal = responsableLegal;
    }

    public boolean isSansAssurance() {
        return sansAssurance;
    }

    public void setSansAssurance(boolean sansAssurance) {
        this.sansAssurance = sansAssurance;
    }

    public boolean isAvecAssurance() {
        return avecAssurance;
    }

    public void setAvecAssurance(boolean avecAssurance) {
        this.avecAssurance = avecAssurance;
    }

    public boolean isCarte10Seances() {
        return carte10Seances;
    }

    public void setCarte10Seances(boolean carte10Seances) {
        this.carte10Seances = carte10Seances;
    }

    public int getNbAdherents() {
        return nbAdherents;
    }

    public void setNbAdherents(int nbAdherents) {
        this.nbAdherents = nbAdherents;
    }



    @Override
    public String toString() {
        return super.toString() + ", Nom de naissance: " + nomNaissance + ", Genre: " + genre +
                ", Pays et Ville de naissance: " + paysVilleNaissance + ", Nationalité: " + nationalite +
                ", Code Postal: " + codePostal + ", Ville: " + ville + ", Deuxième téléphone: " + deuxiemeTelephone +
                ", Fleuret: " + armes.contains("Fleuret") + ", Epée: " + armes.contains("Epée") + ", Sabre: " + armes.contains("Sabre") +
                ", Pratique: " + pratique + ", Latéralité: " + lateralite;
    }
}
