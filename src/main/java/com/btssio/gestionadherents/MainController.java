
package com.btssio.gestionadherents;

import com.btssio.models.Adherent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Adherent> adherentsTable;

    @FXML
    private TableColumn<Adherent, String> nomColumn;

    @FXML
    private TableColumn<Adherent, String> prenomColumn;

    private List<Adherent> listeAdherents;

    public void setListeAdherents(List<Adherent> listeAdherents) {
        this.listeAdherents = listeAdherents;
        updateAdherentsTable();
    }

    private void updateAdherentsTable() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adherentsTable.getItems().setAll(listeAdherents);
    }

    // Placeholder for methods to handle user actions (add, edit, delete)
}
