
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

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load data from XML files
            List<Adherent> listeAdherents = AdherentManager.chargerAdherents("adherents.xml");
            // List<Club> listeClubs = ClubManager.chargerClubs(); // To be implemented
            TarifManager tarifManager = new TarifManager("tarifs.xml");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();

            // Pass the loaded data to the controller
            controller.setListeAdherents(listeAdherents, tarifManager);
            // controller.setListeClubs(listeClubs); // To be implemented

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des Adhérents");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
