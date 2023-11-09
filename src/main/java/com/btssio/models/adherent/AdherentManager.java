package com.btssio.models.adherent;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class AdherentManager {


    public static List<Adherent> chargerAdherents(String pathToXml) {
        try {
            File adherentsXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Adherents.class); // Chargement de la classe Adherents au lieu de Adherent
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Adherents adherentsContainer = (Adherents) unmarshaller.unmarshal(adherentsXml); // Utilisation de Adherents comme conteneur
            return adherentsContainer.getAdherentsList(); // Récupération de la liste directement depuis le conteneur Adherents

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
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Adherents wrapper = new Adherents();
            wrapper.setAdherentsList(adherents);

            marshaller.marshal(wrapper, adherentsXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
