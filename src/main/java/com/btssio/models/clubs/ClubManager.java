package com.btssio.models.clubs;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClubManager {

    public static List<Club> chargerClubs(String pathToXml) {
        try {
            File clubsXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Clubs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (clubsXml.exists()) {
                Clubs clubsContainer = (Clubs) unmarshaller.unmarshal(clubsXml);
                return clubsContainer.getClubsList();
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Si une exception est levée ou que quelque chose se passe mal, on retourne null.
    }

    public static void sauvegarderClubs(List<Club> clubs, String pathToXml) {
        try {
            File clubsXml = new File(pathToXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Clubs.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            Clubs clubsContainer = new Clubs();
            clubsContainer.setClubsList(clubs);

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(clubsContainer, clubsXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
