package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.clubs.Club;
import com.btssio.models.clubs.ClubManager;
import com.btssio.models.tarif.TarifManager;
import jakarta.xml.bind.JAXBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.btssio.models.tarif.TarifManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ClubController {
    @FXML
    private TableView<Club> tableView; // Ensure this ID matches the TableView ID in FXML
    @FXML
    private TableColumn<Club, String> Nom; // Make sure fx:id matches this field name
    @FXML
    private TableColumn<Club, String> Adresse; // And so on for the rest of the columns
    @FXML
    private TableColumn<Club, String> Contact;
    @FXML
    private TableColumn<Club, String> Téléphone;
    @FXML
    private TableColumn<Club, String> Mail;
    @FXML
    private TableColumn<Club, String> Site;
    @FXML
    private TarifManager tarifManager ;
    private List<Adherent> listeAdherents;

    private final ClubManager clubManager = new ClubManager(); // Initialize the ClubManager


    @FXML
    private void initialize() {
        Nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        Adresse.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        Contact.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
        Téléphone.setCellValueFactory(cellData -> cellData.getValue().telProperty());
        Mail.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
        Site.setCellValueFactory(cellData -> cellData.getValue().siteProperty());

        tableView.setItems(getClubData());
    }



    private ObservableList<Club> getClubData() {
        ObservableList<Club> clubs = FXCollections.observableArrayList();
        try {
            File xmlFile = new File("clubs.xml"); // Remplacez par le chemin d'accès réel à votre fichier XML
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


    // Menu item actions
    @FXML
    private MenuBar mainMenuBar;


    @FXML
    private void handleGoToInscriptionView(ActionEvent event) {
        try {
            // Chargez la vue d'inscription FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription-view.fxml"));
            Parent inscriptionView = loader.load();

            // Configurez la scène et la fenêtre pour afficher la vue d'inscription
            Scene currentScene = mainMenuBar.getScene(); // Obtenez la scène actuelle via mainMenuBar
            Stage window = (Stage) currentScene.getWindow(); // Obtenez la fenêtre (Stage) associée à la scène
            window.setScene(new Scene(inscriptionView));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception, peut-être la loguer ou afficher un message d'erreur
        }
    }
    @FXML
    private void handleGoToClubsView(ActionEvent event) {
        try {
            // Chargez la vue des clubs FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("annuaire-club-view.fxml")); // Assurez-vous que le chemin est correct
            Parent clubsView = loader.load();

            // Configurez la scène et la fenêtre pour afficher la vue des clubs
            Scene currentScene = mainMenuBar.getScene(); // Obtenez la scène actuelle via mainMenuBar
            Stage window = (Stage) currentScene.getWindow(); // Obtenez la fenêtre (Stage) associée à la scène
            window.setScene(new Scene(clubsView));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception, peut-être la loguer ou afficher un message d'erreur
        }
    }

    @FXML
    private void handleGoToMainView(ActionEvent event) {
        try {
            // Check if the tarifManager is already initialized, if not initialize and load data
            if (tarifManager == null) {
                tarifManager = new TarifManager();
                tarifManager.loadFromXml("tarifs.xml"); // Load tariff data
            }

            // Check if listeAdherents is already loaded, if not, load from XML
            if (listeAdherents == null || listeAdherents.isEmpty()) {
                listeAdherents = AdherentManager.chargerAdherents("adherents.xml"); // Load adherent data
            }

            // Load the main view FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent mainView = loader.load();

            // Get the controller for the main view and set its data
            MainController mainController = loader.getController();

            // Set the loaded data in mainController

            mainController.setListeAdherents(listeAdherents, tarifManager);

            // Show the main view in the current stage
            Scene mainScene = new Scene(mainView);
            Stage window = (Stage) ((MenuItem) event.getTarget()).getParentPopup().getOwnerWindow();
            window.setScene(mainScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, maybe log it or show an error message
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }





    // Any other event handlers for menu items can be defined here...
}
