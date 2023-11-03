package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.tarif.Categorie;
import com.btssio.models.tarif.TarifManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Adherent, Double> montantCotisationColumn;

    @FXML
    private TableColumn<Adherent, Double> montantDonColumn;

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
    private TarifManager tarifManager ;

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
    private TextField responsableLegalField;
    private boolean rechercheActive = false;

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
        price.setText(String.valueOf(adherent.getMontantCotisation()));
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

    private List<Adherent> listeAdherents;

    public void setListeAdherents(List<Adherent> listeAdherents, TarifManager tarifManager) {
        this.listeAdherents = listeAdherents;
        this.tarifManager = tarifManager;
        updateAdherentsTable();

        tarifManager.getCategories().forEach(categorie -> categorieChoiceBox.getItems().add(categorie.getNom()));
    }

    private void updateAdherentsTable() {


        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        dateInscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        dateFinAdhesionColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinAdhesion"));
        montantCotisationColumn.setCellValueFactory(new PropertyValueFactory<>("montantCotisation"));
        montantDonColumn.setCellValueFactory(new PropertyValueFactory<>("montantDon"));
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
            selectedAdherent.setCategorieName(categorieChoiceBox.getSelectionModel().getSelectedItem());
            selectedAdherent.setMontantCotisation(Double.parseDouble(price.getText()));
            selectedAdherent.setMontantTotal(selectedAdherent.getMontantCotisation() + selectedAdherent.getMontantDon());
            selectedAdherent.setNomNaissance(nomNaissanceField.getText());
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

            responsableLegalField.setText(selectedAdherent.getResponsableLegal());

            // Mise à jour de la table pour refléter les modifications
            updateAdherentsTable();
            handleClearAction();
            saveAdherents();

        }
    }

    @FXML
    public void handleAddAction() {
        // si un adhérent est sélectionné, on clear les champs et on attend
        if (adherentsTable.getSelectionModel().getSelectedItem() != null) {
            handleClearAction();
            return;
        }
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



        listeAdherents.add(newAdherent);
        updateAdherentsTable();
        handleClearAction();
        saveAdherents();

    }

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
        // unfocus the table
        adherentsTable.getSelectionModel().clearSelection();
    }

    public void saveAdherents() {
        AdherentManager.sauvegarderAdherents(listeAdherents, "adherents.xml");
    }


    @FXML
    public void handleSetCategorie() {
        if(categorieChoiceBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        String selectedCategorie = categorieChoiceBox.getSelectionModel().getSelectedItem();
        Categorie categorie = tarifManager.getCategorieByName(selectedCategorie);
        // set this categorie's to the adherent's categorie
        adherentsTable.getSelectionModel().getSelectedItem().setCategorieName(categorie.getNom());

        // set the adherent's montantCotisation to the categorie's fraisInscription
        adherentsTable.getSelectionModel().getSelectedItem().setMontantCotisation(categorie.getFraisInscription());

        // update montantTotal to the sum of montantCotisation and montantDon
        adherentsTable.getSelectionModel().getSelectedItem().setMontantTotal(adherentsTable.getSelectionModel().getSelectedItem().getMontantCotisation() + adherentsTable.getSelectionModel().getSelectedItem().getMontantDon());

        saveAdherents();
        updateAdherentsTable();

    }

    @FXML
    public void handleDeleteCategorie() {
        // set the adherent's categorieName to ""
        adherentsTable.getSelectionModel().getSelectedItem().setCategorieName("");
        // set the adherent's montantCotisation to 0
        adherentsTable.getSelectionModel().getSelectedItem().setMontantCotisation(0.0);
        // update montantTotal to the sum of montantCotisation and montantDon
        adherentsTable.getSelectionModel().getSelectedItem().setMontantTotal(adherentsTable.getSelectionModel().getSelectedItem().getMontantCotisation() + adherentsTable.getSelectionModel().getSelectedItem().getMontantDon());
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

                // Vérifiez si le nom ou le prénom contient la chaîne de recherche.
                if (nom.contains(searchString) || prenom.contains(searchString)) {
                    matchingAdherents.add(adherent);
                }
            }

            // Si au moins un adhérent correspond à la recherche, affichez uniquement celui-ci.
            if (!matchingAdherents.isEmpty()) {
                adherentsTable.setItems(matchingAdherents);
                rechercheActive = true; // Mettez à jour l'état de la recherche
            } else {
                rechercheActive = false; // Aucun adhérent trouvé, mettez à jour l'état de la recherche
            }
        } else {
            rechercheActive = false; // La recherche est vide, mettez à jour l'état de la recherche
        }
    }

    @FXML
    private void handleClearSearchAction() {
        InputRecherche.clear(); // Effacez le contenu du champ de recherche

        // Si la recherche était active, rétablissez la liste complète des adhérents.
        if (rechercheActive) {
            adherentsTable.setItems(FXCollections.observableArrayList(listeAdherents));
            rechercheActive = false; // Mettez à jour l'état de la recherche
        }
    }
}
