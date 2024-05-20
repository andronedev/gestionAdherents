package com.btssio.models.tarif;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class TarifManager {
    private Tarifs tarifs;
    private List<Categorie> categories;

    public TarifManager() {
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    //getOptions
    public Options getOptions() {
        return tarifs.getOptions();
    }

    public Categorie getCategorieByName(String nom) {
        for (Categorie categorie : categories) {
            if (categorie.getNom().equals(nom)) {
                return categorie;
            }
        }
        return null;
    }

    public Categorie getCategorieForBirthYear(int birthYear) {
        for (Categorie categorie : categories) {
            if (birthYear >= categorie.getAnneeDebut() && birthYear <= categorie.getAnneeFin()) {
                return categorie;
            }
        }
        return null;
    }
    //getFraisInscription from birthYear
    public double getFraisInscription(int birthYear) {
        Categorie categorie = getCategorieForBirthYear(birthYear);
        if (categorie != null) {
            return categorie.getFraisInscription();
        }
        return 0;
    }
    //getfraisliscence from birthYear
    public double getFraisLicence(int birthYear) {
        Categorie categorie = getCategorieForBirthYear(birthYear);
        if (categorie != null) {
            return categorie.getFraisLicence();
        }
        return 0;
    }
    //getFraisTotal from birthYear
    public double getFraisTotal(int birthYear) {
        return getFraisInscription(birthYear) + getFraisLicence(birthYear);
    }

    public void loadFromXml(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        this.tarifs = (Tarifs) unmarshaller.unmarshal(new File(pathToXml));
        this.categories = tarifs.getCategories();
    }
}
