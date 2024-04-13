package com.btssio.models.adherent;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdherentManager {


    public static List<Adherent> chargerAdherents(String pathToXml) {
        try {
            File adherentsXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Adherents.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (adherentsXml.exists()) {
                Adherents adherentsContainer = (Adherents) unmarshaller.unmarshal(adherentsXml);
                return adherentsContainer.getAdherentsList();
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Si une exception est levée ou que quelque chose se passe mal, on retourne null.
    }

    public static void sauvegarderAdherents(List<Adherent> adherents, String pathToXml) {
        try {
            File adherentsXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Adherents.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            Adherents adherentsContainer = new Adherents();
            adherentsContainer.setAdherentsList(adherents);

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(adherentsContainer, adherentsXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //ajout d'un adherent dans la liste
    public static void ajouterAdherent(List<Adherent> adherent, String pathToXml) {
        try {
            List<Adherent> adherents = chargerAdherents(pathToXml);
            Objects.requireNonNull(adherents).add((Adherent) adherent);
            sauvegarderAdherents(adherents, pathToXml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


