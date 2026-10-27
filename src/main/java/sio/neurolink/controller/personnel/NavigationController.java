package sio.neurolink.controller.personnel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sio.neurolink.navigation.SceneManager;
import java.io.IOException;

/** Navigation commune aux écrans du personnel administratif. */
public abstract class NavigationController {
    @FXML public final void ouvrirDashboard(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_dashboard.fxml"); }
    @FXML public final void ouvrirPatients(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_patients.fxml"); }
    @FXML public final void ouvrirMedecins(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_medecins.fxml"); }
    @FXML public final void ouvrirSalles(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_salles.fxml"); }
    @FXML public final void ouvrirDemandes(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_rdv.fxml"); }
    @FXML public final void ouvrirPlanning(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_planning.fxml"); }
    @FXML public final void ouvrirCreationPatient(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_creation_patient.fxml"); }
    @FXML public final void ouvrirFichePatient(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_fiche_patient.fxml"); }
    @FXML public final void ouvrirCreationMedecin(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_creation_medecin.fxml"); }
    @FXML public final void ouvrirFicheMedecin(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_fiche_medecin.fxml"); }
    @FXML public final void ouvrirDetailDemande(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_detail_demande.fxml"); }
    @FXML public final void ouvrirStatutSalle(ActionEvent event) { changerPage(event, "/fxml/personnel/personnel_statut_salle.fxml"); }
    @FXML public final void deconnexion(ActionEvent event) {
        // TODO: fermer la session authentifiée avant de revenir au login.
        changerPage(event, "/fxml/auth/login.fxml");
    }
    private void changerPage(ActionEvent event, String cheminFxml) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            new SceneManager(stage).navigateTo(cheminFxml);
        } catch (IOException erreur) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("NeuroLink Clinic");
            alerte.setHeaderText("Impossible d'ouvrir la page");
            alerte.setContentText(erreur.getMessage());
            alerte.showAndWait();
        }
    }
}
