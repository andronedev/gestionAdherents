package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.tarif.Categorie;
import com.btssio.models.tarif.TarifManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
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

    public void initialize() {
        // Ajouter un écouteur à la sélection de la table
        adherentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateInputFields(newSelection);
            }
        });
    }

    private void updateInputFields(Adherent adherent) {
        setNom(adherent.getNom());
        setPrenom(adherent.getPrenom());
        setEmail(adherent.getEmail());
        setTelephone(adherent.getTelephone());
        setAdresse(adherent.getAdresse());
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



    public String getNom() {
        return nomField.getText();
    }

    public void setNom(String nom) {
        nomField.setText(nom);
    }

    public String getPrenom() {
        return prenomField.getText();
    }

    public void setPrenom(String prenom) {
        prenomField.setText(prenom);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void setEmail(String email) {
        emailField.setText(email);
    }

    public String getTelephone() {
        return telephoneField.getText();
    }

    public void setTelephone(String telephone) {
        telephoneField.setText(telephone);
    }

    public String getAdresse() {
        return adresseField.getText();
    }

    public void setAdresse(String adresse) {
        adresseField.setText(adresse);
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

        adherentsTable.getItems().setAll(listeAdherents);
    }

    // Placeholder for methods to handle user actions (add, edit, delete)
    @FXML
    public void handleEditAction() {
        Adherent selectedAdherent = adherentsTable.getSelectionModel().getSelectedItem();
        if (selectedAdherent != null) {
            // Mettre à jour l'adhérent sélectionné avec les valeurs des champs d'entrée
            selectedAdherent.setNom(getNom());
            selectedAdherent.setPrenom(getPrenom());
            selectedAdherent.setEmail(getEmail());
            selectedAdherent.setTelephone(getTelephone());
            selectedAdherent.setAdresse(getAdresse());
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
        Adherent newAdherent = new Adherent(getEmail(), getTelephone(), getNom(), getPrenom(), getAdresse(), LocalDate.now(), LocalDate.now(), LocalDate.now(), 0.0, 0.0, 0.0, "");
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
        setNom("");
        setPrenom("");
        setEmail("");
        setTelephone("");
        setAdresse("");
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
}
