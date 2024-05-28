package com.btssio.models.clubs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "club")
public class Club {
    private String nom;
    private String adresse;
    private String contact;
    private String tel;
    private String mail;
    private String site;

    // Constructeur par défaut requis pour JAXB
    public Club() {

    }

    public Club(String nom, String adresse, String contact, String tel, String mail, String site) {
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.tel = tel;
        this.mail = mail;
        this.site = site;
    }

    @XmlElement(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name = "adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @XmlElement(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @XmlElement(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @XmlElement(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlElement(name = "site")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
