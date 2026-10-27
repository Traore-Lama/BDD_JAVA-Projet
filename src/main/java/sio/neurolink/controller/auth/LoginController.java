package sio.neurolink.controller.auth;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sio.neurolink.NeuroLinkApplication; // Vérifie bien le nom de ta classe main
import sio.neurolink.entity.Admin;
import sio.neurolink.entity.PersAdmin;
import sio.neurolink.entity.User;
import sio.neurolink.security.AuthService;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtLogin;
    @FXML private PasswordField txtMotDePasse;
    @FXML private Label txtErreur;
    @FXML private Button btnConnexion;

    @FXML
    public void btnConnexionClicked(Event event) throws IOException {

        // 1. Vérification des champs vides
        if (txtLogin.getText().isEmpty()) {
            txtErreur.setText("Saisir votre login");
            return;
        }
        if (txtMotDePasse.getText().isEmpty()) {
            txtErreur.setText("Saisir votre mot de passe");
            return;
        }

        // 2. Authentification
        try {
            AuthService authService = new AuthService();
            User user = authService.login(txtLogin.getText(), txtMotDePasse.getText());

            // 3. Ouverture de la nouvelle fenêtre
            Stage stage = new Stage();

            if (user instanceof Admin) {
                FXMLLoader fxmlLoader = new FXMLLoader(NeuroLinkApplication.class.getResource("/fxml/admin/admin_dashboard.fxml"));
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Administrateur");
            } else if (user instanceof PersAdmin) {
                FXMLLoader fxmlLoader = new FXMLLoader(NeuroLinkApplication.class.getResource("/fxml/personnel/personnel_dashboard.fxml"));
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Personnel Administratif");
            } else {
                txtErreur.setText("Profil non reconnu");
                return;
            }

            stage.show();

            // 4. Fermeture de la fenêtre de connexion
            ((Stage) btnConnexion.getScene().getWindow()).close();

        } catch (Exception e) {
            // Affichage de l'erreur dans le Label (comme le prof)
            txtErreur.setText(e.getMessage());
        }
    }
}