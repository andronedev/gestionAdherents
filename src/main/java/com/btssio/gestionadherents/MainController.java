package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    public void setListeAdherents(List<Adherent> listeAdherents) {
        this.listeAdherents = listeAdherents;
        updateAdherentsTable();
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
        Adherent newAdherent = new Adherent(getEmail(), getTelephone(), getNom(), getPrenom(), getAdresse(), LocalDate.now(), LocalDate.now(), LocalDate.now(), 0.0, 0.0, 0.0);
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

}
