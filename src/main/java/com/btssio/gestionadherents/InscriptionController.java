package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.tarif.Categorie;
import com.btssio.models.tarif.TarifManager;
import jakarta.xml.bind.JAXBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.awt.*;

public class InscriptionController {
    @FXML
    private javafx.scene.control.TextField nomField;

    @FXML
    private javafx.scene.control.TextField prenomField;

    @FXML
    private javafx.scene.control.TextField emailField;

    @FXML
    private javafx.scene.control.TextField telephoneField;

    @FXML
    private javafx.scene.control.TextField adresseField;
    @FXML
    private Button clearButton;

    @FXML
    private javafx.scene.control.TextField categorieField;

    @FXML
    private Label price;

    @FXML
    private ChoiceBox<String> categorieChoiceBox;
    @FXML
    private TarifManager tarifManager ;

    @FXML
    private javafx.scene.control.TextField InputRecherche;

    @FXML
    private javafx.scene.control.TextField nomNaissanceField;

    @FXML
    private CheckBox masculinCheckBox;

    @FXML
    private CheckBox femininCheckBox;

    @FXML
    private javafx.scene.control.TextField naissanceJourField;

    @FXML
    private javafx.scene.control.TextField naissanceMoisField;

    @FXML
    private javafx.scene.control.TextField naissanceAnneeField;

    @FXML
    private javafx.scene.control.TextField paysVilleNaissanceField;

    @FXML
    private javafx.scene.control.TextField nationaliteField;

    @FXML
    private javafx.scene.control.TextField codePostalField;

    @FXML
    private javafx.scene.control.TextField villeField;

    @FXML
    private javafx.scene.control.TextField deuxiemeTelField;

    @FXML
    private CheckBox fleuretCheckBox;

    @FXML
    private CheckBox epeeCheckBox;

    @FXML
    private CheckBox sabreCheckBox;

    @FXML
    private CheckBox loisirCheckbox;

    @FXML
    private CheckBox competitionCheckbox;

    @FXML
    private CheckBox gaucherCheckbox;

    @FXML
    private CheckBox droitierCheckbox;



    @FXML
    private TextField responsableLegalField;

    private List<Adherent> listeAdherents;


    private com.btssio.gestionadherents.MainController MainController;

    private MainController mainController;

    @FXML
    private void handleAddAction(ActionEvent event) {
        if (ValidInputs()) {
            List<String> armes = new ArrayList<>();
            if (fleuretCheckBox.isSelected()) armes.add("Fleuret");
            if (epeeCheckBox.isSelected()) armes.add("Epée");
            if (sabreCheckBox.isSelected()) armes.add("Sabre");
            String pratique = loisirCheckbox.isSelected() && competitionCheckbox.isSelected() ? "Loisir et Compétition"
                    : loisirCheckbox.isSelected() ? "Loisir"
                    : competitionCheckbox.isSelected() ? "Compétition"
                    : "";

            String lateralite = gaucherCheckbox.isSelected() && droitierCheckbox.isSelected() ? "Ambidextre"
                    : gaucherCheckbox.isSelected() ? "Gaucher"
                    : droitierCheckbox.isSelected() ? "Droitier"
                    : "";
            // Collect values from form inputs, validate them, and construct an adherent object
            Adherent newAdherent = new Adherent(
                    emailField.getText(),
                    telephoneField.getText(),
                    nomField.getText(),  // Replace with actual field if different
                    prenomField.getText(), // Replace with actual field if different
                    adresseField.getText(),
                    LocalDate.of(
                            Integer.parseInt(naissanceAnneeField.getText()),
                            Integer.parseInt(naissanceMoisField.getText()),
                            Integer.parseInt(naissanceJourField.getText())
                    ),
                    LocalDate.now(), // You might need a DatePicker for this if you want a different date
                    LocalDate.now().plusYears(1), // Assuming the adhesion lasts for 1 year
                    0.0, // You might need to get this from a TextField or a different input
                    0.0, // Same as above for montantDon
                    0.0, // Same as above for montantTotal
                    "", // You will need a way to set categorieName
                    nomNaissanceField.getText(),
                    masculinCheckBox.isSelected() ? "Masculin" : femininCheckBox.isSelected() ? "Féminin" : "",
                    paysVilleNaissanceField.getText(), // Assuming the country and city are comma-separated
                    nationaliteField.getText(),
                    codePostalField.getText(),
                    villeField.getText(),
                    deuxiemeTelField.getText(),
                    armes,
                    pratique,
                    lateralite,
                    responsableLegalField.getText()
            );

            // Add new adherent to the list
            if (listeAdherents == null) {
                listeAdherents = new ArrayList<>();
            }
            listeAdherents.add(newAdherent);

            // Save the new list to XML
            try {
                AdherentManager.sauvegarderAdherents(listeAdherents, "adherents.xml");
                // Inform the user of success and/or clear form fields as necessary
                showAlert("Adhérent ajouté", "L'adhérent a été ajouté avec succès !");
                clearFormFields();
            } catch (Exception e) {
                // Handle exceptions and inform the user of any errors
                showAlert("Erreur", "Une erreur est survenue lors de l'ajout de l'adhérent.");
                e.printStackTrace();
            }
        }
    }

    private boolean ValidInputs() {
        // Validate inputs and return true if they are valid, false otherwise
            if (nomField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le nom est requis.");
                return false;
            }
            else if (!nomField.getText().matches("[a-zA-Z\\s-']+")) { // Regex pour les lettres, espaces, tirets et apostrophes.
                showAlert("Erreur de validation", "Le nom contient des caractères non autorisés.");
                return false;
            }
            if (prenomField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le prénom est requis.");
                return false;
            }
            else if (!nomField.getText().matches("[a-zA-Z\\s-']+")) { // Regex pour les lettres, espaces, tirets et apostrophes.
        showAlert("Erreur de validation", "Le nom contient des caractères non autorisés.");
        return false;
    }
            if (emailField.getText().isEmpty()) {
                showAlert("Erreur de validation", "L'email est requis.");
                return false;
            }
            else if (!emailField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Regex pour les emails
                showAlert("Erreur de validation", "L'email est invalide.");
                return false;
            }
            if (telephoneField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le numéro de téléphone est requis.");
                return false;
            }
            else if (!telephoneField.getText().matches("^(\\+33|0)[1-9]([-. ]?\\d{2}){4}$")) {
                showAlert("Erreur de validation", "Le numéro de téléphone est invalide.");
                return false;
            }
            if (adresseField.getText().isEmpty()) {
                showAlert("Erreur de validation", "L'adresse est requise.");
                return false;
            }

            if (nomNaissanceField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le nom de naissance est requis.");
                return false;
            }

            if (masculinCheckBox.isSelected() == false && femininCheckBox.isSelected() == false) {
                showAlert("Erreur de validation", "Le sexe est requis.");
                return false;
            }
            if (naissanceJourField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le jour de naissance est requis.");
                return false;
            }
            else if (!naissanceJourField.getText().matches("^(0?[1-9]|[12][0-9]|3[01])$")) { // Regex pour les jours de 1 à 31
                showAlert("Erreur de validation", "Le jour de naissance est invalide.");
                return false;
            }
            if (naissanceMoisField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le mois de naissance est requis.");
                return false;
            }
            else if (!naissanceMoisField.getText().matches("^(0?[1-9]|1[012])$")) { // Regex pour les mois de 1 à 12
                showAlert("Erreur de validation", "Le mois de naissance est invalide.");
                return false;
            }
            if (naissanceAnneeField.getText().isEmpty()) {
                showAlert("Erreur de validation", "L'année de naissance est requise.");
                return false;
            }
            else if (!naissanceAnneeField.getText().matches("^\\d{4}$")) { // Regex pour les années de 4 chiffres
                showAlert("Erreur de validation", "L'année de naissance est invalide.");
                return false;
            }
            if (paysVilleNaissanceField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le pays et la ville de naissance sont requis.");
                return false;
            }

            if (nationaliteField.getText().isEmpty()) {
                showAlert("Erreur de validation", "La nationalité est requise.");
                return false;
            }
            if (codePostalField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le code postal est requis.");
                return false;
            }
            else if (!codePostalField.getText().matches("^\\d{5}$")) { // Regex pour les codes postaux de 5 chiffres
                showAlert("Erreur de validation", "Le code postal est invalide.");
                return false;
            }
            if (villeField.getText().isEmpty()) {
                showAlert("Erreur de validation", "La ville est requise.");
                return false;
            }

            if (deuxiemeTelField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le deuxième numéro de téléphone est requis.");
                return false;
            }
            else if (!deuxiemeTelField.getText().matches("^(\\+33|0)[1-9]([-. ]?\\d{2}){4}$")) {
                showAlert("Erreur de validation", "Le deuxième numéro de téléphone est invalide.");
                return false;
            }
            if (fleuretCheckBox.isSelected() == false && epeeCheckBox.isSelected() == false && sabreCheckBox.isSelected() == false) {
                showAlert("Erreur de validation", "L'arme est requise.");
                return false;
            }
            if (loisirCheckbox.isSelected() == false && competitionCheckbox.isSelected() == false) {
                showAlert("Erreur de validation", "La pratique est requise.");
                return false;
            }
            if (gaucherCheckbox.isSelected() == false && droitierCheckbox.isSelected() == false) {
                showAlert("Erreur de validation", "La latéralité est requise.");
                return false;
            }
            if (responsableLegalField.getText().isEmpty()) {
                showAlert("Erreur de validation", "Le responsable légal est requis.");
                return false;
            }
            return true;
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private LocalDate parseDate(String year, String month, String day) {
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    private void clearFormFields() {
        // Logic to clear all form fields
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
    private MenuBar mainMenuBar;
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



    private Stage getStageFromEvent(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }





    public void setListeAdherents(List<Adherent> listeAdherents) {
        this.listeAdherents = listeAdherents;
    }
}
