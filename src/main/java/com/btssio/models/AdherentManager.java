
package com.btssio.models;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AdherentManager {

    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Adherent adherent, Club club, Date date, String period) {
        Reservation reservation = new Reservation(adherent, club, date, period);
        reservations.add(reservation);
    }

    public List<Reservation> getReservationsForAdherent(Adherent adherent) {
        return reservations.stream().filter(r -> r.getAdherent().equals(adherent)).collect(Collectors.toList());
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public double calculateFeeForAdherent(Adherent adherent) {
        List<Reservation> adherentReservations = getReservationsForAdherent(adherent);
        double totalFee = 0.0;
        for (Reservation reservation : adherentReservations) {
            totalFee += reservation.getClub().getCategory().getTarif();
        }
        return totalFee;
    }


    public static List<Adherent> chargerAdherents() {
        List<Adherent> listeAdherents = new ArrayList<>();
        
        try {
            File adherentsXml = new File("adherents.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(adherentsXml);
            doc.getDocumentElement().normalize();
            
            NodeList adherentNodes = doc.getElementsByTagName("adherent");
            
            for (int i = 0; i < adherentNodes.getLength(); i++) {
                Node adherentNode = adherentNodes.item(i);
                
                if (adherentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element adherentElement = (Element) adherentNode;
                    String nom = adherentElement.getElementsByTagName("nom").item(0).getTextContent();
                    String prenom = adherentElement.getElementsByTagName("prenom").item(0).getTextContent();
                    Adherent adherent = new Adherent(nom, prenom);
                    listeAdherents.add(adherent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listeAdherents;
    }
    
    // Additional methods for saving, updating, and deleting adherents can be added here.

}
