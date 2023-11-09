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

    public Categorie getCategorieByName(String nom) {
        for (Categorie categorie : categories) {
            if (categorie.getNom().equals(nom)) {
                return categorie;
            }
        }
        return null;
    }

    public void addCategorie(Categorie categorie) {
        categories.add(categorie);
    }

    public void removeCategorie(String nom) {
        categories.removeIf(categorie -> categorie.getNom().equals(nom));
    }

    public void saveToXml(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(tarifs, new File(pathToXml));
    }

    public void loadFromXml(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        this.tarifs = (Tarifs) unmarshaller.unmarshal(new File(pathToXml));
        this.categories = tarifs.getCategories();
    }
}
