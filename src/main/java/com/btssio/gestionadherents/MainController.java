package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.tarif.Categorie;
import com.btssio.models.tarif.OptionManager;
import com.btssio.models.tarif.Options;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Adherent> adherentsTable;

    @FXML
    private TableColumn<Adherent, String> nomColumn;

    @FXML
    private TableColumn<Adherent, String> prenomColumn;

    @FXML
    private TableColumn<Adherent, String> adresseColumn;

    @FXML
    private TableColumn<Adherent, String> telephoneColumn;

    @FXML
    private TableColumn<Adherent, String> emailColumn;

    @FXML
    private TableColumn<Adherent, LocalDate> dateNaissanceColumn;

    @FXML
    private TableColumn<Adherent, LocalDate> dateInscriptionColumn;

    @FXML
    private TableColumn<Adherent, LocalDate> dateFinAdhesionColumn;

    @FXML
    private TableColumn<Adherent, Double> montantAdhesionColumn;

    @FXML
    private TableColumn<Adherent, Double> montantOptionColumn;

    @FXML
    private TableColumn<Adherent, Double> montantTotalColumn;

    @FXML
    private TableColumn<Adherent, String> categorieColumn;

    @FXML
    private TableColumn<Adherent, String> nomNaissanceColumn;

    @FXML
    private TableColumn<Adherent, String> genreColumn;

    @FXML
    private TableColumn<Adherent, LocalDate> naissanceColumn;

    @FXML
    private TableColumn<Adherent, String> paysVilleNaissanceColumn;

    @FXML
    private TableColumn<Adherent, String> nationaliteColumn;

    @FXML
    private TableColumn<Adherent, String> codePostalColumn;

    @FXML
    private TableColumn<Adherent, String> villeColumn;

    @FXML
    private TableColumn<Adherent, String> deuxiemeTelColumn;

    @FXML
    private TableColumn<Adherent, String> responsableLegalColumn;

    @FXML
    private TableColumn<Adherent, String> armesColumn;

    @FXML
    private TableColumn<Adherent, String> pratiqueColumn;

    @FXML
    private TableColumn<Adherent, String> lateraliteColumn;
    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField adresseField;
    @FXML
    private Button clearButton;

    @FXML
    private TextField categorieField;

    @FXML
    private Label price;

    @FXML
    private ChoiceBox<String> categorieChoiceBox;
    @FXML
    private TarifManager tarifManager;

    @FXML
    private TextField InputRecherche;

    @FXML
    private TextField nomNaissanceField;

    @FXML
    private CheckBox masculinCheckBox;

    @FXML
    private CheckBox femininCheckBox;


    @FXML
    private TextField naissanceJourField;

    @FXML
    private TextField naissanceMoisField;

    @FXML
    private TextField naissanceAnneeField;

    @FXML
    private TextField paysVilleNaissanceField;

    @FXML
    private TextField nationaliteField;

    @FXML
    private TextField codePostalField;

    @FXML
    private TextField villeField;

    @FXML
    private TextField deuxiemeTelField;

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
    private boolean rechercheActive = false;
    private List<Adherent> listeAdherents;
    @FXML
    private MenuBar mainMenuBar; // Assurez-vous que le MenuBar a un fx:id et est lié ici

    public void initialize() {
        // Ajouter un écouteur à la sélection de la table
        adherentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateInputFields(newSelection);
            }
        });
    }

    private void updateInputFields(Adherent adherent) {
        nomField.setText(adherent.getNom());
        prenomField.setText(adherent.getPrenom());
        emailField.setText(adherent.getEmail());
        telephoneField.setText(adherent.getTelephone());
        adresseField.setText(adherent.getAdresse());
        categorieChoiceBox.setValue(adherent.getCategorieName());
        price.setText(String.valueOf(adherent.getMontantAdhesion()));
        nomNaissanceField.setText(adherent.getNomNaissance());
        masculinCheckBox.setSelected(adherent.getGenre().equals("Masculin"));
        femininCheckBox.setSelected(adherent.getGenre().equals("Féminin"));
        naissanceJourField.setText(String.valueOf(adherent.getDateNaissance().getDayOfMonth()));
        naissanceMoisField.setText(String.valueOf(adherent.getDateNaissance().getMonthValue()));
        naissanceAnneeField.setText(String.valueOf(adherent.getDateNaissance().getYear()));
        paysVilleNaissanceField.setText(adherent.getPaysVilleNaissance());
        nationaliteField.setText(adherent.getNationalite());
        codePostalField.setText(adherent.getCodePostal());
        villeField.setText(adherent.getVille());
        deuxiemeTelField.setText(adherent.getDeuxiemeTelephone());
        fleuretCheckBox.setSelected(adherent.getArmes().contains("Fleuret"));
        epeeCheckBox.setSelected(adherent.getArmes().contains("Epée"));
        sabreCheckBox.setSelected(adherent.getArmes().contains("Sabre"));
        loisirCheckbox.setSelected(adherent.getPratique().contains("Loisir"));
        competitionCheckbox.setSelected(adherent.getPratique().contains("Compétition"));
        gaucherCheckbox.setSelected(adherent.getLateralite().contains("Gaucher"));
        droitierCheckbox.setSelected(adherent.getLateralite().contains("Droitier"));
        responsableLegalField.setText(adherent.getResponsableLegal());
        sansAssuranceCheckbox.setSelected(adherent.isSansAssurance());
        avecAssuranceRenfCheckbox.setSelected(adherent.isAvecAssurance());
        avec10SeanceCheckbox.setSelected(adherent.isCarte10Seances());
        nbAdherentFamille.setText(String.valueOf(adherent.getNbAdherents()));

        // set the categorieField text to the adherent's categorieName
        if (adherent.getCategorieName() != null) {
            // check if in the list of categories there category exist
            if (tarifManager.getCategorieByName(adherent.getCategorieName()) != null) {
                categorieChoiceBox.setValue(adherent.getCategorieName());
                price.setText(String.valueOf(tarifManager.getCategorieByName(adherent.getCategorieName()).getFraisInscription()));
            }
        } else {
            clearCategorie();
        }
    }

    public void setListeAdherents(List<Adherent> listeAdherents, TarifManager tarifManager) {
        this.listeAdherents = listeAdherents;
        this.tarifManager = tarifManager;
        updateAdherentsTable();

        tarifManager.getCategories().forEach(categorie -> categorieChoiceBox.getItems().add(categorie.getNom()));
    }

    public void updateAdherentsTable() {


        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        dateInscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        dateFinAdhesionColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinAdhesion"));
        montantAdhesionColumn.setCellValueFactory(new PropertyValueFactory<>("montantAdhesion"));
        montantOptionColumn.setCellValueFactory(new PropertyValueFactory<>("montantOption"));
        montantTotalColumn.setCellValueFactory(new PropertyValueFactory<>("montantTotal"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorieName"));

        nomNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("nomNaissance"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        naissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance")); // Assurez-vous que c'est la bonne propriété
        paysVilleNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("paysVilleNaissance"));
        nationaliteColumn.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        codePostalColumn.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        deuxiemeTelColumn.setCellValueFactory(new PropertyValueFactory<>("deuxiemeTelephone"));
        responsableLegalColumn.setCellValueFactory(new PropertyValueFactory<>("responsableLegal"));
        armesColumn.setCellValueFactory(new PropertyValueFactory<>("armes"));
        pratiqueColumn.setCellValueFactory(new PropertyValueFactory<>("pratique"));
        lateraliteColumn.setCellValueFactory(new PropertyValueFactory<>("lateralite"));


        adherentsTable.getItems().setAll(listeAdherents);
    }

    // Placeholder for methods to handle user actions (add, edit, delete)
    @FXML
    public void handleEditAction() {
        Adherent selectedAdherent = adherentsTable.getSelectionModel().getSelectedItem();
        if (selectedAdherent != null) {
            // Mettre à jour l'adhérent sélectionné avec les valeurs des champs d'entrée
            selectedAdherent.setNom(nomField.getText());
            selectedAdherent.setPrenom(prenomField.getText());
            selectedAdherent.setEmail(emailField.getText());
            selectedAdherent.setTelephone(telephoneField.getText());
            selectedAdherent.setAdresse(adresseField.getText());
            selectedAdherent.setNomNaissance(nomNaissanceField.getText());
            selectedAdherent.setDateNaissance(LocalDate.of(
                    Integer.parseInt(naissanceAnneeField.getText()),
                    Integer.parseInt(naissanceMoisField.getText()),
                    Integer.parseInt(naissanceJourField.getText())
            ));
            selectedAdherent.setGenre(masculinCheckBox.isSelected() ? "Masculin" : femininCheckBox.isSelected() ? "Féminin" : "");
            selectedAdherent.setPaysVilleNaissance(paysVilleNaissanceField.getText());
            selectedAdherent.setNationalite(nationaliteField.getText());
            selectedAdherent.setCodePostal(codePostalField.getText());
            selectedAdherent.setVille(villeField.getText());
            selectedAdherent.setDeuxiemeTelephone(deuxiemeTelField.getText());

            List<String> armes = new ArrayList<>();
            if (fleuretCheckBox.isSelected()) armes.add("Fleuret");
            if (epeeCheckBox.isSelected()) armes.add("Epée");
            if (sabreCheckBox.isSelected()) armes.add("Sabre");
            selectedAdherent.setArmes(armes);

            selectedAdherent.setPratique(loisirCheckbox.isSelected() && competitionCheckbox.isSelected() ? "Loisir et Compétition"
                    : loisirCheckbox.isSelected() ? "Loisir"
                    : competitionCheckbox.isSelected() ? "Compétition"
                    : "");
            selectedAdherent.setLateralite(gaucherCheckbox.isSelected() && droitierCheckbox.isSelected() ? "Ambidextre"
                    : gaucherCheckbox.isSelected() ? "Gaucher"
                    : droitierCheckbox.isSelected() ? "Droitier"
                    : "");
            selectedAdherent.setResponsableLegal(responsableLegalField.getText());
            selectedAdherent.setSansAssurance(sansAssuranceCheckbox.isSelected());
            selectedAdherent.setAvecAssurance(avecAssuranceRenfCheckbox.isSelected());
            selectedAdherent.setCarte10Seances(avec10SeanceCheckbox.isSelected());
            selectedAdherent.setNbAdherents(Integer.parseInt(nbAdherentFamille.getText()));

            try {
                // Charger les données des tarifs et des options
                TarifManager tarifManager = new TarifManager();
                tarifManager.loadFromXml("tarifs.xml");

                // Calculer les frais d'adhésion en fonction de la date de naissance
                int birthYear = Integer.parseInt(naissanceAnneeField.getText());
                Categorie laCat = tarifManager.getCategorieForBirthYear(birthYear);
                selectedAdherent.setCategorieName(laCat.getNom());

                double montantTotal = tarifManager.getFraisTotal(birthYear);

                Options options = tarifManager.getOptions();
                OptionManager optionManager = new OptionManager(options);

                // Calculer la réduction familiale
                int nbAdherents;
                try {
                    nbAdherents = Integer.parseInt(nbAdherentFamille.getText());
                } catch (NumberFormatException e) {
                    // Si l'utilisateur n'a pas saisi de nombre, on considère qu'il y a 1 seul adhérent
                    nbAdherents = 1;
                }
                double reduction = OptionManager.calculerReduction(nbAdherents, laCat.getNom());
                System.out.println("Reduction : " + reduction + " Categorie : " + laCat.getNom());

                // Calculer les montants d'assurance et de carte de 10 séances
                boolean sansAssurance = sansAssuranceCheckbox.isSelected();
                boolean avecAssurance = avecAssuranceRenfCheckbox.isSelected();
                boolean carte10Seances = avec10SeanceCheckbox.isSelected();
                double montantLicence = optionManager.getLicenceAmount(sansAssurance, avecAssurance);
                double montantCarte10Seances = optionManager.getCarte10SeancesAmount(carte10Seances);

                // Calculer le montant total d'inscription
                double montantTotalInscription = optionManager.calculerMontantTotal(montantTotal, montantLicence, reduction, montantCarte10Seances);
                selectedAdherent.setMontantAdhesion(montantTotal);
                selectedAdherent.setMontantOption(montantLicence + montantCarte10Seances + reduction);
                selectedAdherent.setMontantTotal(montantTotalInscription);

                // Mise à jour de la table pour refléter les modifications
                updateAdherentsTable();
                handleClearAction();
                saveAdherents();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }


//    @FXML
//    public void handleAddAction() {
//            // si un adhérent est sélectionné, on clear les champs et on attend
//            if (adherentsTable.getSelectionModel().getSelectedItem() != null) {
//                handleClearAction();
//                return;
//            }
//            List<String> armes = new ArrayList<>();
//        if (fleuretCheckBox.isSelected()) armes.add("Fleuret");
//        if (epeeCheckBox.isSelected()) armes.add("Epée");
//        if (sabreCheckBox.isSelected()) armes.add("Sabre");
//
//        String pratique = loisirCheckbox.isSelected() && competitionCheckbox.isSelected() ? "Loisir et Compétition"
//                : loisirCheckbox.isSelected() ? "Loisir"
//                : competitionCheckbox.isSelected() ? "Compétition"
//                : "";
//
//        String lateralite = gaucherCheckbox.isSelected() && droitierCheckbox.isSelected() ? "Ambidextre"
//                : gaucherCheckbox.isSelected() ? "Gaucher"
//                : droitierCheckbox.isSelected() ? "Droitier"
//                : "";
//
//        Adherent newAdherent = new Adherent(
//                emailField.getText(),
//                telephoneField.getText(),
//                nomField.getText(),  // Replace with actual field if different
//                prenomField.getText(), // Replace with actual field if different
//                adresseField.getText(),
//                LocalDate.of(
//                        Integer.parseInt(naissanceAnneeField.getText()),
//                        Integer.parseInt(naissanceMoisField.getText()),
//                        Integer.parseInt(naissanceJourField.getText())
//                ),
//                LocalDate.now(), // You might need a DatePicker for this if you want a different date
//                LocalDate.now().plusYears(1), // Assuming the adhesion lasts for 1 year
//                0.0, // You might need to get this from a TextField or a different input
//                0.0, // Same as above for montantDon
//                0.0, // Same as above for montantTotal
//                "", // You will need a way to set categorieName
//                nomNaissanceField.getText(),
//                masculinCheckBox.isSelected() ? "Masculin" : femininCheckBox.isSelected() ? "Féminin" : "",
//                paysVilleNaissanceField.getText(), // Assuming the country and city are comma-separated
//                nationaliteField.getText(),
//                codePostalField.getText(),
//                villeField.getText(),
//                deuxiemeTelField.getText(),
//                armes,
//                pratique,
//                lateralite,
//                responsableLegalField.getText()
//        );
//
//
//        listeAdherents.add(newAdherent);
//        updateAdherentsTable();
//        handleClearAction();
//        saveAdherents();
//
//    }

    @FXML
    public void handleDeleteAction() {
        Adherent selectedAdherent = adherentsTable.getSelectionModel().getSelectedItem();
        if (selectedAdherent != null) {
            listeAdherents.remove(selectedAdherent);
            updateAdherentsTable();
            handleClearAction();
            clearCategorie();
            saveAdherents();
        }
    }

    @FXML
    public void handleClearAction() {
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        telephoneField.setText("");
        adresseField.setText("");
        nomNaissanceField.setText("");
        masculinCheckBox.setSelected(false);
        femininCheckBox.setSelected(false);
        naissanceJourField.setText("");
        naissanceMoisField.setText("");
        naissanceAnneeField.setText("");
        paysVilleNaissanceField.setText("");
        nationaliteField.setText("");
        codePostalField.setText("");
        villeField.setText("");
        deuxiemeTelField.setText("");
        fleuretCheckBox.setSelected(false);
        epeeCheckBox.setSelected(false);
        sabreCheckBox.setSelected(false);
        loisirCheckbox.setSelected(false);
        competitionCheckbox.setSelected(false);
        gaucherCheckbox.setSelected(false);
        droitierCheckbox.setSelected(false);
        responsableLegalField.setText("");
        // unfocus the table
        adherentsTable.getSelectionModel().clearSelection();
    }

    public void saveAdherents() {
        AdherentManager.sauvegarderAdherents(listeAdherents, "adherents.xml");
    }

    @FXML
    public void handleSetCategorie() {
        if (categorieChoiceBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        String selectedCategorie = categorieChoiceBox.getSelectionModel().getSelectedItem();
        Categorie categorie = tarifManager.getCategorieByName(selectedCategorie);
        // set this categorie's to the adherent's categorie
        adherentsTable.getSelectionModel().getSelectedItem().setCategorieName(categorie.getNom());

        // set the adherent's montantCotisation to the categorie's fraisInscription
        adherentsTable.getSelectionModel().getSelectedItem().setMontantAdhesion(categorie.getFraisInscription());

        // update montantTotal to the sum of montantCotisation and montantDon
        adherentsTable.getSelectionModel().getSelectedItem().setMontantTotal(adherentsTable.getSelectionModel().getSelectedItem().getMontantAdhesion() + adherentsTable.getSelectionModel().getSelectedItem().getMontantOption());

        saveAdherents();
        updateAdherentsTable();

    }

    @FXML
    public void handleDeleteCategorie() {
        // set the adherent's categorieName to ""
        adherentsTable.getSelectionModel().getSelectedItem().setCategorieName("");
        // set the adherent's montantCotisation to 0
        adherentsTable.getSelectionModel().getSelectedItem().setMontantAdhesion(0.0);
        // update montantTotal to the sum of montantCotisation and montantDon
        adherentsTable.getSelectionModel().getSelectedItem().setMontantTotal(adherentsTable.getSelectionModel().getSelectedItem().getMontantAdhesion() + adherentsTable.getSelectionModel().getSelectedItem().getMontantOption());
        saveAdherents();
        updateAdherentsTable();


    }

    @FXML
    public void handleSelectCategorie() {
        // get the selected categorie
        String selectedCategorie = categorieChoiceBox.getSelectionModel().getSelectedItem();
        // get the categorie object from the tarifManager
        if (tarifManager.getCategorieByName(selectedCategorie) == null) {
            return;
        }
        Categorie categorie = tarifManager.getCategorieByName(selectedCategorie);
        // set the categorieField text to the categorie's name
        categorieChoiceBox.setValue(categorie.getNom());
        // set the tarifField text to the categorie's tarif
        price.setText(String.valueOf(categorie.getFraisInscription()));


    }

    @FXML
    public void clearCategorie() {
        categorieChoiceBox.getSelectionModel().clearSelection();
        price.setText("");
    }

    @FXML
    private void handleSearchAction() {
        String searchString = InputRecherche.getText().trim().toLowerCase();

        if (!searchString.isEmpty()) {
            // Créez une liste temporaire pour stocker les adhérents correspondants à la recherche.
            ObservableList<Adherent> matchingAdherents = FXCollections.observableArrayList();

            // Parcourez la liste des adhérents et ajoutez ceux qui correspondent à la recherche.
            for (Adherent adherent : listeAdherents) {
                String nom = adherent.getNom().toLowerCase();
                String prenom = adherent.getPrenom().toLowerCase();
                //implémenter d'autres éléments de recherche ville
                String ville = adherent.getVille().toLowerCase();

                // Vérifiez si le nom ou le prénom contient la chaîne de recherche.
                if (nom.contains(searchString) || prenom.contains(searchString) || ville.contains(searchString)) {
                    matchingAdherents.add(adherent);
                }
            }

            // Si au moins un adhérent correspond à la recherche, affichez uniquement celui-ci.
            if (!matchingAdherents.isEmpty()) {
                adherentsTable.setItems(matchingAdherents);
            } else {//                rechercheActive = true; // Mettez à jour l'état de la recherche
                adherentsTable.setItems(FXCollections.observableArrayList(new ArrayList<>())); // Aucun adhérent trouvé, affichez une liste vide
                rechercheActive = false; // Aucun adhérent trouvé, mettez à jour l'état de la recherche
            }
        } else {
            adherentsTable.setItems(FXCollections.observableArrayList(listeAdherents));
            rechercheActive = false;// La recherche est vide, mettez à jour l'état de la recherche


        }
    }

    @FXML
    private void handleClearSearchAction() {
        InputRecherche.clear();

        if (!rechercheActive) {
            adherentsTable.setItems(FXCollections.observableArrayList(listeAdherents));
            rechercheActive = false; // Mise à jour l'état de la recherche
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


}
