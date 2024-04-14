package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.tarif.Categorie;
import com.btssio.models.tarif.Options;
import com.btssio.models.tarif.TarifManager;
import com.btssio.models.tarif.OptionManager;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private TarifManager tarifManager;

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
    private CheckBox sansAssuranceCheckbox;

    @FXML
    private CheckBox avecAssuranceRenfCheckbox;

    @FXML
    private TextField nbAdherentFamille;

    @FXML
    private CheckBox avec10SeanceCheckbox;

    @FXML
    private TextField responsableLegalField;

    private List<Adherent> listeAdherents;




    private com.btssio.gestionadherents.MainController MainController;

    private MainController mainController;
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private void handleAddAction(ActionEvent event) {
        if (ValidInputs()) {
            List<String> armes = new ArrayList<>();
            if (fleuretCheckBox.isSelected()) armes.add("Fleuret");
            if (epeeCheckBox.isSelected()) armes.add("Épée");
            if (sabreCheckBox.isSelected()) armes.add("Sabre");
            String pratique = loisirCheckbox.isSelected() && competitionCheckbox.isSelected() ? "Loisir et Compétition"
                    : loisirCheckbox.isSelected() ? "Loisir"
                    : competitionCheckbox.isSelected() ? "Compétition"
                    : "";

            String lateralite = gaucherCheckbox.isSelected() && droitierCheckbox.isSelected() ? "Ambidextre"
                    : gaucherCheckbox.isSelected() ? "Gaucher"
                    : droitierCheckbox.isSelected() ? "Droitier"
                    : "";

            // Collecter les valeurs des champs du formulaire, les valider et construire un objet Adherent
            Adherent newAdherent = new Adherent(
                    emailField.getText(),
                    telephoneField.getText(),
                    nomField.getText(),
                    prenomField.getText(),
                    adresseField.getText(),
                    LocalDate.of(
                            Integer.parseInt(naissanceAnneeField.getText()),
                            Integer.parseInt(naissanceMoisField.getText()),
                            Integer.parseInt(naissanceJourField.getText())
                    ),
                    LocalDate.now(),
                    LocalDate.now().plusYears(1),
                    0.0,
                    0.0,
                    0.0,
                    "",
                    nomNaissanceField.getText(),
                    masculinCheckBox.isSelected() ? "Masculin" : femininCheckBox.isSelected() ? "Féminin" : "",
                    paysVilleNaissanceField.getText(),
                    nationaliteField.getText(),
                    codePostalField.getText(),
                    villeField.getText(),
                    deuxiemeTelField.getText(),
                    armes,
                    pratique,
                    lateralite,
                    responsableLegalField.getText());
                    TarifManager tarifManager = new TarifManager();
                    try {
                        tarifManager.loadFromXml("tarifs.xml");
                    } catch (JAXBException e) {
                        throw new RuntimeException(e);
                    }
                    //from datenaissance use getFraisTotal
                    int birthYear = Integer.parseInt(naissanceAnneeField.getText());
                    Categorie laCat = tarifManager.getCategorieForBirthYear(birthYear);
                    //affect category to adherent
                    newAdherent.setCategorieName(laCat.getNom());
                    double montantTotal = tarifManager.getFraisTotal(birthYear);
                    System.out.println("Montant total pour l'année " + birthYear + " : " + montantTotal);
                    //set montantAdhesion to adheren and tableview
                    newAdherent.setMontantAdhesion(montantTotal);
                    //initialise optionManager
                    Options options = new Options();
                    OptionManager optionManager = new OptionManager(options);
                    try {
                        optionManager.loadFromXml("tarifs.xml");
                    } catch (JAXBException e) {
                        throw new RuntimeException(e);
                    }
                    //calculer la reduction
                    int nbAdherents;
                    try {
                        nbAdherents = Integer.parseInt(nbAdherentFamille.getText());
                    } catch (NumberFormatException e) {
                        // Si l'utilisateur n'a pas saisi de nombre, on considère qu'il y a 1 seul adhérent
                        nbAdherents = 1;
                    }
                    double reduction = OptionManager.calculerReduction(nbAdherents, laCat.getNom());
                    newAdherent.setMontantOption(reduction);
                    System.out.println("Réduction pour " + nbAdherents + " adhérent(s) : " + reduction);
                    boolean sansAssurance = sansAssuranceCheckbox.isSelected();
                    boolean avecAssurance = avecAssuranceRenfCheckbox.isSelected();
                    boolean Carte10Seances = avec10SeanceCheckbox.isSelected();
                    double montantLicence = optionManager.getLicenceAmount(sansAssurance, avecAssurance);
                    double montantCarte10Seances = optionManager.getCarte10SeancesAmount(Carte10Seances);
                    double montantTotalInscription = optionManager.calculerMontantTotal(montantTotal, montantLicence, reduction , montantCarte10Seances);
                    newAdherent.setMontantOption(montantLicence+montantCarte10Seances+reduction);
                    newAdherent.setMontantTotal(montantTotalInscription);


            try {
                // Charger la liste existante des adhérents
                List<Adherent> existingAdherents = AdherentManager.chargerAdherents("adherents.xml");

                // Ajouter le nouvel adhérent à la liste existante
                existingAdherents.add(newAdherent);

                // Sauvegarder la liste complète des adhérents
                AdherentManager.sauvegarderAdherents(existingAdherents, "adherents.xml");

                // Informer l'utilisateur du succès et/ou effacer les champs du formulaire si nécessaire
                showAlert("Adhérent ajouté", "L'adhérent a été ajouté avec succès !");
                clearFormFields();
            } catch (Exception e) {
                // Gérer les exceptions et informer l'utilisateur de toute erreur
                showAlert("Erreur", "Une erreur est survenue lors de l'ajout de l'adhérent.");
                e.printStackTrace();
            }
        }
    }


    private boolean ValidInputs() {
        // Valider les saisies et renvoyer true si elles sont valides, sinon false
        if (nomField.getText().isEmpty()) {
            showAlert("Erreur de validation", "Le nom est requis.");
            return false;
        } else if (!nomField.getText().matches("[a-zA-Z\\s-']+")) { // Regex pour les lettres, espaces, tirets et apostrophes.
            showAlert("Erreur de validation", "Le nom contient des caractères non autorisés.");
            return false;
        }
        if (prenomField.getText().isEmpty()) {
            showAlert("Erreur de validation", "Le prénom est requis.");
            return false;
        } else if (!prenomField.getText().matches("[a-zA-Z\\s-']+")) { // Regex pour les lettres, espaces, tirets et apostrophes.
            showAlert("Erreur de validation", "Le prénom contient des caractères non autorisés.");
            return false;
        }
        if (emailField.getText().isEmpty()) {
            showAlert("Erreur de validation", "L'email est requis.");
            return false;
        } else if (!emailField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Regex pour les emails
            showAlert("Erreur de validation", "L'email est invalide.");
            return false;
        }
        if (telephoneField.getText().isEmpty()) {
            showAlert("Erreur de validation", "Le numéro de téléphone est requis.");
            return false;
        } else if (!telephoneField.getText().matches("^(\\+33|0)[1-9]([-. ]?\\d{2}){4}$")) {
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
        if (!masculinCheckBox.isSelected() && !femininCheckBox.isSelected()) {
            showAlert("Erreur de validation", "Le sexe est requis.");
            return false;
        }
        if (naissanceJourField.getText().isEmpty()) {
            showAlert("Erreur de validation", "Le jour de naissance est requis.");
            return false;
        } else if (!naissanceJourField.getText().matches("^(0?[1-9]|[12][0-9]|3[01])$")) { // Regex pour les jours de 1 à 31
            showAlert("Erreur de validation", "Le jour de naissance est invalide.");
            return false;
        }
        if (naissanceMoisField.getText().isEmpty()) {
            showAlert("Erreur de validation", "Le mois de naissance est requis.");
            return false;
        } else if (!naissanceMoisField.getText().matches("^(0?[1-9]|1[012])$")) { // Regex pour les mois de 1 à 12
            showAlert("Erreur de validation", "Le mois de naissance est invalide.");
            return false;
        }
        if (naissanceAnneeField.getText().isEmpty()) {
            showAlert("Erreur de validation", "L'année de naissance est requise.");
            return false;
        } else if (!naissanceAnneeField.getText().matches("^\\d{4}$")) { // Regex pour les années de 4 chiffres
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
        } else if (!codePostalField.getText().matches("^\\d{5}$")) { // Regex pour les codes postaux de 5 chiffres
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
        } else if (!deuxiemeTelField.getText().matches("^(\\+33|0)[1-9]([-. ]?\\d{2}){4}$")) {
            showAlert("Erreur de validation", "Le deuxième numéro de téléphone est invalide.");
            return false;
        }
        if (!fleuretCheckBox.isSelected() && !epeeCheckBox.isSelected() && !sabreCheckBox.isSelected()) {
            showAlert("Erreur de validation", "L'arme est requise.");
            return false;
        }
        if (!loisirCheckbox.isSelected() && !competitionCheckbox.isSelected()) {
            showAlert("Erreur de validation", "La pratique est requise.");
            return false;
        }
        if (!gaucherCheckbox.isSelected() && !droitierCheckbox.isSelected()) {
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

    @FXML
    private void handleGenderSelection() {
        if (masculinCheckBox.isSelected() && femininCheckBox.isSelected()) {
            if (masculinCheckBox.isFocused()) {
                femininCheckBox.setSelected(false);
            } else if (femininCheckBox.isFocused()) {
                masculinCheckBox.setSelected(false);
            }
        }
    }

    @FXML
    private void handleLicenceAssuranceSelection() {
        if (sansAssuranceCheckbox.isSelected() && avecAssuranceRenfCheckbox.isSelected()) {
            if (avecAssuranceRenfCheckbox.isFocused()) {
                sansAssuranceCheckbox.setSelected(false);
            } else if (sansAssuranceCheckbox.isFocused()) {
                avecAssuranceRenfCheckbox.setSelected(false);
            }
        }
    }

    @FXML
    private void handleArmeSelection() {
        if (fleuretCheckBox.isFocused()) {
            epeeCheckBox.setSelected(false);
            sabreCheckBox.setSelected(false);
        } else if (epeeCheckBox.isFocused()) {
            fleuretCheckBox.setSelected(false);
            sabreCheckBox.setSelected(false);
        } else if (sabreCheckBox.isFocused()) {
            fleuretCheckBox.setSelected(false);
            epeeCheckBox.setSelected(false);
        }
    }
    @FXML
    private void handlePratiqueSelection() {
        if (loisirCheckbox.isSelected() && competitionCheckbox.isSelected()) {
            if (loisirCheckbox.isFocused()) {
                competitionCheckbox.setSelected(false);
            } else if (competitionCheckbox.isFocused()) {
                loisirCheckbox.setSelected(false);
            }
        }
    }


    @FXML
    private void clearFormFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        telephoneField.clear();
        adresseField.clear();
        nomNaissanceField.clear();
        masculinCheckBox.setSelected(false);
        femininCheckBox.setSelected(false);
        naissanceJourField.clear();
        naissanceMoisField.clear();
        naissanceAnneeField.clear();
        paysVilleNaissanceField.clear();
        nationaliteField.clear();
        codePostalField.clear();
        villeField.clear();
        deuxiemeTelField.clear();
        fleuretCheckBox.setSelected(false);
        epeeCheckBox.setSelected(false);
        sabreCheckBox.setSelected(false);
        loisirCheckbox.setSelected(false);
        competitionCheckbox.setSelected(false);
        gaucherCheckbox.setSelected(false);
        droitierCheckbox.setSelected(false);
        responsableLegalField.clear();
    }

    @FXML
    private void handleGoToMainView(ActionEvent event) {
        try {
            // Vérifie si le tarifManager est déjà initialisé, sinon l'initialiser et charger les données
            if (tarifManager == null) {
                tarifManager = new TarifManager();
                tarifManager.loadFromXml("tarifs.xml"); // Charger les données de tarifs
            }

            // Vérifie si la listeAdherents est déjà chargée, sinon, charger à partir du XML
            if (listeAdherents == null || listeAdherents.isEmpty()) {
                listeAdherents = AdherentManager.chargerAdherents("adherents.xml"); // Charger les données des adhérents
            }

            // Charger le FXML de la vue principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent mainView = loader.load();

            // Obtenir le contrôleur pour la vue principale et définir ses données
            MainController mainController = loader.getController();

            // Définir les données chargées dans mainController

            mainController.setListeAdherents(listeAdherents, tarifManager);

            // Afficher la vue principale dans le stage actuel
            Scene mainScene = new Scene(mainView);
            Stage window = (Stage) ((MenuItem) event.getTarget()).getParentPopup().getOwnerWindow();
            window.setScene(mainScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception, peut-être la logger ou afficher un message d'erreur
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

    public void setListeAdherents(List<Adherent> listeAdherents ) {
        this.listeAdherents = listeAdherents;
    }
}
