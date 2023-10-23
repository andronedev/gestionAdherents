
package com.btssio.models.adherent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdherentManager {


    public static List<Adherent> chargerAdherents() {
        List<Adherent> listeAdherents = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

                    String email = adherentElement.getElementsByTagName("email").item(0).getTextContent();
                    String phoneNumber = adherentElement.getElementsByTagName("telephone").item(0).getTextContent();
                    String nom = adherentElement.getElementsByTagName("nom").item(0).getTextContent();
                    String prenom = adherentElement.getElementsByTagName("prenom").item(0).getTextContent();
                    String adresse = adherentElement.getElementsByTagName("adresse").item(0).getTextContent();
                    LocalDate dateNaissance = LocalDate.parse(adherentElement.getElementsByTagName("dateNaissance").item(0).getTextContent(), formatter);
                    LocalDate dateInscription = LocalDate.parse(adherentElement.getElementsByTagName("dateInscription").item(0).getTextContent(), formatter);
                    LocalDate dateFinAdhesion = LocalDate.parse(adherentElement.getElementsByTagName("dateFinAdhesion").item(0).getTextContent(), formatter);
                    double montantCotisation = Double.parseDouble(adherentElement.getElementsByTagName("montantCotisation").item(0).getTextContent());
                    double montantDon = Double.parseDouble(adherentElement.getElementsByTagName("montantDon").item(0).getTextContent());
                    double montantTotal = Double.parseDouble(adherentElement.getElementsByTagName("montantTotal").item(0).getTextContent());

                    Adherent adherent = new Adherent(email, phoneNumber, nom, prenom, adresse, dateNaissance, dateInscription, dateFinAdhesion, montantCotisation, montantDon, montantTotal);
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
