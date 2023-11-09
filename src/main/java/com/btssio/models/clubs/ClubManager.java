package com.btssio.models.clubs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class ClubManager {
    public ObservableList<Club> loadClubsFromXML(String xmlFilePath) {
        ObservableList<Club> clubs = FXCollections.observableArrayList();

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("club");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String nom = eElement.getElementsByTagName("nom").item(0).getTextContent();
                    String adresse = eElement.getElementsByTagName("adresse").item(0).getTextContent();
                    String contact = eElement.getElementsByTagName("contact").item(0).getTextContent();
                    String tel = eElement.getElementsByTagName("tel").item(0).getTextContent();
                    String mail = eElement.getElementsByTagName("mail").item(0).getTextContent();
                    String site = eElement.getElementsByTagName("site").item(0).getTextContent();

                    clubs.add(new Club(nom, adresse, contact, tel, mail, site));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clubs;
    }
}
