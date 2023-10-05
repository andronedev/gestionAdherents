package com.btssio.gestionadherents;

import com.btssio.models.Adherent;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.btssio.models.AdherentManager;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Managers
        AdherentManager manager = new AdherentManager();

        // Chargement des données
        manager.loadAdherentsFromXML("adherents.xml");
        manager.loadClubsFromXML("clubs.xml");

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        MainController controller = fxmlLoader.getController();
        controller.nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        controller.prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        controller.adherentsTable.getColumns().setAll(controller.nomColumn, controller.prenomColumn);


        ObservableList<Adherent> adherentsList = manager.getAdherentsObservableList("");
        System.out.println("Nombre d'adhérents dans la liste : " + adherentsList.size());
        controller.adherentsTable.setItems(adherentsList);


    }

    public static void main(String[] args) {
        launch();
    }
}