package sio.neurolink.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sio.neurolink.navigation.SceneManager;

import java.io.IOException;

/**
 * Navigation commune à tous les écrans de l'administrateur.
 * Les contrôleurs d'écrans héritent de cette classe : pas de logique
 * d'authentification ou de base de données ici.
 */
public abstract class NavigationController {

    @FXML
    public final void ouvrirDashboard(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_dashboard.fxml");
    }

    @FXML
    public final void ouvrirPersonnel(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_personnel.fxml");
    }

    @FXML
    public final void ouvrirCreationPersonnel(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_creation_personnel.fxml");
    }

    @FXML
    public final void ouvrirSalles(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_salles.fxml");
    }

    @FXML
    public final void ouvrirCreationSalle(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_creation_salle.fxml");
    }

    @FXML
    public final void ouvrirModificationPersonnel(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_modification_personnel.fxml");
    }

    @FXML
    public final void ouvrirModificationSalle(ActionEvent event) {
        changerPage(event, "/fxml/admin/admin_modification_salle.fxml");
    }

    @FXML
    public final void deconnexion(ActionEvent event) {
        // Navigation provisoire : lors de l'intégration de l'authentification,
        // effacer aussi la session avant de revenir à la connexion.
        changerPage(event, "/fxml/auth/login.fxml");
    }

    private void changerPage(ActionEvent event, String cheminFxml) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            new SceneManager(stage).navigateTo(cheminFxml);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NeuroLink Clinic");
            alert.setHeaderText("Impossible d'ouvrir la page");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
