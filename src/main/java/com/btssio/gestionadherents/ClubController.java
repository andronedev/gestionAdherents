package com.btssio.gestionadherents;

import com.btssio.models.adherent.Adherent;
import com.btssio.models.adherent.AdherentManager;
import com.btssio.models.clubs.Club;
import com.btssio.models.clubs.ClubManager;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClubController {
    private final ClubManager clubManager = new ClubManager(); // Initialize the ClubManager
    @FXML
    private TableView<Club> tableView; // Ensure this ID matches the TableView ID in FXML
    @FXML
    private TableColumn<Club, String> Nom; // Make sure fx:id matches this field name
    @FXML
    private TableColumn<Club, String> Adresse; // And so on for the rest of the columns
    @FXML
    private TableColumn<Club, String> Contact;
    @FXML
    private TableColumn<Club, String> Téléphone;
    @FXML
    private TableColumn<Club, String> Mail;
    @FXML
    private TableColumn<Club, String> Site;
    @FXML
    private TarifManager tarifManager;
    private List<Adherent> listeAdherents;
    // Menu item actions
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private void initialize() {
        // Set the cell value factories for each column
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Téléphone.setCellValueFactory(new PropertyValueFactory<>("tel"));
        Mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        Site.setCellValueFactory(new PropertyValueFactory<>("site"));

        // Load the data into the table view
        tableView.setItems(getClubData());
    }

    private ObservableList<Club> getClubData() {
        ObservableList<Club> clubs = FXCollections.observableArrayList();
        try {
            clubs.addAll(ClubManager.chargerClubs("clubs.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
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

            // Charger la liste des adhérents depuis le fichier XML, garantissant qu'elle n'est jamais nulle
            listeAdherents = AdherentManager.chargerAdherents("adherents.xml");
            if (listeAdherents == null) {
                listeAdherents = new ArrayList<>(); // Assure une liste non nulle
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

    @FXML
    private void handleGeneratePDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF documents", "*.pdf"));
        fileChooser.setInitialFileName("AnnuaireClubs.pdf");
        Stage stage = (Stage) tableView.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
                document.addPage(page);
                try {
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);

                    // Définir les marges et la hauteur de la ligne
                    final float margin = 50;
                    final float yStart = page.getMediaBox().getHeight() - margin;
                    final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
                    final float rowHeight = 50;
                    float yPosition = yStart;

                    // Définir les noms des colonnes et la largeur des cellules
                    String[] columns = {"Nom", "Adresse", "Contact", "Téléphone", "Mail", "Site"};
                    float cellWidth = tableWidth / columns.length;

                    // Dessiner les en-têtes de colonnes
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    drawRow(contentStream, margin, yPosition, cellWidth, rowHeight, columns, true);
                    yPosition -= rowHeight;

                    // Dessiner les lignes de contenu
                    for (Club club : tableView.getItems()) {
                        if (yPosition <= margin + rowHeight) {
                            contentStream.close();
                            PDPage newPage = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
                            document.addPage(newPage);
                            contentStream = new PDPageContentStream(document, newPage);
                            yPosition = yStart;
                        }
                        String[] clubInfo = {
                                club.getNom(),
                                club.getAdresse(),
                                club.getContact(),
                                club.getTel(),
                                club.getMail(),
                                club.getSite()
                        };
                        drawRow(contentStream, margin, yPosition, cellWidth, rowHeight, clubInfo, false);
                        yPosition -= rowHeight;
                    }

                    contentStream.close();
                    document.save(file);
                    showAlert(Alert.AlertType.INFORMATION, stage, "Succès", "Le fichier PDF a été sauvegardé avec succès à l'emplacement : " + file.getAbsolutePath());

                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, stage, "Erreur", "Une erreur est survenue lors de la création du PDF : " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, stage, "Erreur", "Une erreur est survenue lors de la création du PDF : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private float calculateRowHeight(String[] content, float cellWidth, PDFont font, float fontSize) throws IOException {
        float maxRowHeight = 0;
        for (String text : content) {
            List<String> lines = wrapText(text, (int) (cellWidth - 2), font, fontSize);
            float cellHeight = (lines.size() * 12) + 5; // 12 est la taille de la police utilisée, 5 pour un peu d'espace supplémentaire
            maxRowHeight = Math.max(maxRowHeight, cellHeight);
        }
        return maxRowHeight;
    }

    private void drawRow(PDPageContentStream contentStream, float margin, float y, float cellWidth, float initialRowHeight, String[] content, boolean header) throws IOException {
        float rowHeight = calculateRowHeight(content, cellWidth, header ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 12);
        float nextx = margin;

        for (int i = 0; i < content.length; i++) {
            String text = content[i] != null ? content[i] : "";
            List<String> lines = wrapText(text, (int) (cellWidth - 2), header ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 12);

            float cellHeight = (lines.size() * 12) + 5;
            float textY = y - 15; // Commencez à écrire du texte un peu en dessous du haut de la cellule

            // Écrivez le texte pour chaque cellule
            for (String line : lines) {
                contentStream.beginText();
                contentStream.setFont(header ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(nextx + 2, textY);
                contentStream.showText(line);
                contentStream.endText();
                textY -= 12; // Déplacez le texte vers le bas pour la prochaine ligne
            }

            // Dessinez les bordures de la cellule
            contentStream.moveTo(nextx, y);
            contentStream.lineTo(nextx, y - rowHeight);
            contentStream.stroke();

            nextx += cellWidth; // Déplacez-vous à la position x de la prochaine cellule
        }

        // Dessinez la ligne du bas pour la rangée entière
        contentStream.moveTo(margin, y - rowHeight);
        contentStream.lineTo(margin + cellWidth * content.length, y - rowHeight);
        contentStream.stroke();

        // Réinitialisez la position y pour la prochaine rangée
        y -= rowHeight;
    }

    private List<String> wrapText(String text, float width, PDFont font, float fontSize) throws IOException {
        List<String> lines = new ArrayList<>();
        while (text.length() > 0) {
            int split = text.length();
            while (split > 0) {
                String subString = text.substring(0, split);
                float size = fontSize * font.getStringWidth(subString) / 1000;
                if (size <= width) {
                    lines.add(subString);
                    text = text.substring(split).trim(); // Supprimez la partie du texte ajoutée à la ligne
                    break;
                }
                split--; // Réduisez le point de découpe jusqu'à ce que la largeur soit suffisante
            }
            if (split == 0) { // Dans le cas où même un seul caractère est trop large, forcer le split pour éviter une boucle infinie
                lines.add(text.substring(0, 1));
                text = text.substring(1).trim();
            }
        }
        return lines;
    }

    private void showAlert(Alert.AlertType alertType, Stage owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }
}
