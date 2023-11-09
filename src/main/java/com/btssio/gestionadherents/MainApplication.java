package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.tarif.TarifManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            List<Adherent> listeAdherents = AdherentManager.chargerAdherents("adherents.xml");
            TarifManager tarifManager = new TarifManager();
            tarifManager.loadFromXml("tarifs.xml");

            FXMLLoader inscriptionLoader = new FXMLLoader(getClass().getResource("inscription-view.fxml"));
            Parent inscriptionRoot = inscriptionLoader.load();
            InscriptionController inscriptionController = inscriptionLoader.getController();

            // Pass the loaded data to the inscription controller
            inscriptionController.setListeAdherents(listeAdherents); // Cette ligne passe la liste au contrôleur d'inscription

            Scene inscriptionScene = new Scene(inscriptionRoot);
            primaryStage.setScene(inscriptionScene);
            primaryStage.setTitle("Inscription des Adhérents");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
