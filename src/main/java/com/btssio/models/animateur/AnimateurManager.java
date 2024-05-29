package com.btssio.models.animateur;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimateurManager {

    public static List<Animateur> chargerAnimateurs(String pathToXml) {
        try {
            File animateursXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Animateurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (animateursXml.exists()) {
                Animateurs animateursContainer = (Animateurs) unmarshaller.unmarshal(animateursXml);
                return animateursContainer.getAnimateursList();
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void sauvegarderAnimateurs(List<Animateur> animateurs, String pathToXml) {
        try {
            File animateursXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Animateurs.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            Animateurs animateursContainer = new Animateurs();
            animateursContainer.setAnimateursList(animateurs);

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(animateursContainer, animateursXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ajouterAnimateur(Animateur animateur, String pathToXml) {
        try {
            List<Animateur> animateurs = chargerAnimateurs(pathToXml);
            Objects.requireNonNull(animateurs).add(animateur);
            sauvegarderAnimateurs(animateurs, pathToXml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
