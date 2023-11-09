package com.btssio.models.clubs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Club {
    // Ensure this ID matches the TableView ID in FXML
    private SimpleStringProperty nom;
    private SimpleStringProperty adresse;
    private SimpleStringProperty contact;
    private SimpleStringProperty tel;
    private SimpleStringProperty mail;
    private SimpleStringProperty site;

    public Club(String nom, String adresse, String contact, String tel, String mail, String site) {
        this.nom = new SimpleStringProperty(nom);
        this.adresse = new SimpleStringProperty(adresse);
        this.contact = new SimpleStringProperty(contact);
        this.tel = new SimpleStringProperty(tel);
        this.mail = new SimpleStringProperty(mail);
        this.site = new SimpleStringProperty(site);
    }

    public String getNom() {
        return nom.get();
    }

    public String getAdresse() {
        return adresse.get();
    }

    public String getContact() {
        return contact.get();
    }

    public String getTel() {
        return tel.get();
    }

    public String getMail() {
        return mail.get();
    }

    public String getSite() {
        return site.get();
    }

    public void setNom(String value) { nom.set(value); }
    public void setAdresse(String value) { adresse.set(value); }
    public void setContact(String value) { contact.set(value); }
    public void setTel(String value) { tel.set(value); }
    public void setMail(String value) { mail.set(value); }
    public void setSite(String value) { site.set(value); }

    // Property getters
    public StringProperty nomProperty() { return nom; }
    public StringProperty adresseProperty() { return adresse; }
    public StringProperty contactProperty() { return contact; }
    public StringProperty telProperty() { return tel; }
    public StringProperty mailProperty() { return mail; }
    public StringProperty siteProperty() { return site; }



    @Override
    public String toString() {
        return "club{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", contact='" + contact + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
