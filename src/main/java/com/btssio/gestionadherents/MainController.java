package com.btssio.gestionadherents;

import com.btssio.models.Adherent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
    @FXML
    public TableView<Adherent> adherentsTable;

    @FXML
    TableColumn<Adherent, String> nomColumn;

    @FXML
    TableColumn<Adherent, String> prenomColumn;

}