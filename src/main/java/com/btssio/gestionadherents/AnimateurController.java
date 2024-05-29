package com.btssio.gestionadherents;
import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.animateur.Animateur;
import com.btssio.models.animateur.AnimateurManager;
import com.btssio.models.tarif.TarifManager;
import jakarta.xml.bind.JAXBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimateurController {

    @FXML
    private TableView<Animateur> animateursTable;

    @FXML
    private TableColumn<Animateur, String> nomColumn;

    @FXML
    private TableColumn<Animateur, String> prenomColumn;

    @FXML
    private TableColumn<Animateur, String> adressePostaleColumn;

    @FXML
    private TableColumn<Animateur, String> armeColumn;

    @FXML
    private TableColumn<Animateur, String> categorieEleveSuivieColumn;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField adressePostaleField;

    @FXML
    private TextField armeField;

    @FXML
    private TextField categorieEleveSuivieField;

    @FXML
    private TextField searchField;

    private List<Animateur> listeAnimateurs;
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private TarifManager tarifManager;
    private List<Adherent> listeAdherents;


    public void initialize() {
        // Ajouter un écouteur à la sélection de la table
        animateursTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateInputFields(newSelection);
            }
        });

        // Initialiser les colonnes de la table
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressePostaleColumn.setCellValueFactory(new PropertyValueFactory<>("adressePostale"));
        armeColumn.setCellValueFactory(new PropertyValueFactory<>("arme"));
        categorieEleveSuivieColumn.setCellValueFactory(new PropertyValueFactory<>("categorieEleveSuivie"));

        // Charger les animateurs depuis le fichier XML
        listeAnimateurs = AnimateurManager.chargerAnimateurs("animateurs.xml");
        if (listeAnimateurs == null) {
            listeAnimateurs = new ArrayList<>();
        }

        // Mettre à jour la table avec la liste des animateurs
        updateAnimateursTable();
    }

    private void updateInputFields(Animateur animateur) {
        nomField.setText(animateur.getNom());
        prenomField.setText(animateur.getPrenom());
        adressePostaleField.setText(animateur.getAdressePostale());
        armeField.setText(animateur.getArme());
        categorieEleveSuivieField.setText(animateur.getCategorieEleveSuivie());
    }

    public void updateAnimateursTable() {
        ObservableList<Animateur> animateurs = FXCollections.observableArrayList(listeAnimateurs);
        animateursTable.setItems(animateurs);
    }

    @FXML
    public void handleAddAction() {
        Animateur newAnimateur = new Animateur(
                nomField.getText(),
                prenomField.getText(),
                adressePostaleField.getText(),
                armeField.getText(),
                categorieEleveSuivieField.getText()
        );
        listeAnimateurs.add(newAnimateur);
        updateAnimateursTable();
        handleClearAction();
        AnimateurManager.sauvegarderAnimateurs(listeAnimateurs, "animateurs.xml");
    }

    @FXML
    public void handleEditAction() {
        Animateur selectedAnimateur = animateursTable.getSelectionModel().getSelectedItem();
        if (selectedAnimateur != null) {
            selectedAnimateur.setNom(nomField.getText());
            selectedAnimateur.setPrenom(prenomField.getText());
            selectedAnimateur.setAdressePostale(adressePostaleField.getText());
            selectedAnimateur.setArme(armeField.getText());
            selectedAnimateur.setCategorieEleveSuivie(categorieEleveSuivieField.getText());
            updateAnimateursTable();
            handleClearAction();
            animateursTable.refresh();
            AnimateurManager.sauvegarderAnimateurs(listeAnimateurs, "animateurs.xml");
        }
    }

    @FXML
    public void handleDeleteAction() {
        Animateur selectedAnimateur = animateursTable.getSelectionModel().getSelectedItem();
        if (selectedAnimateur != null) {
            listeAnimateurs.remove(selectedAnimateur);
            updateAnimateursTable();
            handleClearAction();
            AnimateurManager.sauvegarderAnimateurs(listeAnimateurs, "animateurs.xml");
        }
    }

    @FXML
    public void handleClearAction() {
        nomField.clear();
        prenomField.clear();
        adressePostaleField.clear();
        armeField.clear();
        categorieEleveSuivieField.clear();
        animateursTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleSearchAction() {
        String searchString = searchField.getText().trim().toLowerCase();
        if (!searchString.isEmpty()) {
            ObservableList<Animateur> matchingAnimateurs = FXCollections.observableArrayList();
            for (Animateur animateur : listeAnimateurs) {
                if (animateur.getNom().toLowerCase().contains(searchString) ||
                        animateur.getPrenom().toLowerCase().contains(searchString)) {
                    matchingAnimateurs.add(animateur);
                }
            }
            animateursTable.setItems(matchingAnimateurs);
        } else {
            animateursTable.setItems(FXCollections.observableArrayList(listeAnimateurs));
        }
    }

    @FXML
    public void handleClearSearchAction() {
        searchField.clear();
        animateursTable.setItems(FXCollections.observableArrayList(listeAnimateurs));
    }

    @FXML
    private void handleGoToMainView(ActionEvent event) {
        try {
            // Vérifie et initialise le tarifManager
            if (tarifManager == null) {
                tarifManager = new TarifManager();
                tarifManager.loadFromXml("tarifs.xml"); // Charger les données de tarifs
            }
            // Charger la liste des adhérents depuis le fichier XML, garantissant qu'elle n'est jamais nulle
            listeAdherents = AdherentManager.chargerAdherents("adherents.xml");
            if (listeAdherents == null) {
                listeAdherents = new ArrayList<>(); // Assure une liste non nulle
            }
            // Charger le FXML de la vue principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent mainView = loader.load();

            // Obtenir le contrôleur pour la vue principale et définir ses données
            MainController mainController = loader.getController();

            // Définir les données chargées dans mainController
            mainController.setListeAdherents(listeAdherents,tarifManager); // Assurez-vous que cette méthode gère aussi les listes vides

            // Afficher la vue principale dans le stage actuel
            Scene mainScene = new Scene(mainView);
            Stage window = (Stage) ((MenuItem) event.getTarget()).getParentPopup().getOwnerWindow();
            window.setScene(mainScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception, peut-être la logger ou afficher un message d'erreur
        } catch (JAXBException e) {
            e.printStackTrace(); // Traiter les exceptions JAXB
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
    public void handleGoToAnimateursView(ActionEvent actionEvent) {
        try {
            // Chargez la vue des animateurs FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("animateur-view.fxml"));
            Parent animateursView = loader.load();

            // Configurez la scène et la fenêtre pour afficher la vue des animateurs
            Scene currentScene = mainMenuBar.getScene(); // Obtenez la scène actuelle via mainMenuBar
            Stage window = (Stage) currentScene.getWindow(); // Obtenez la fenêtre (Stage) associée à la scène
            window.setScene(new Scene(animateursView));
            window.show();

        }catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception, peut-être la loguer ou afficher un message d'erreur

        }
    }
}
