
package sio.neurolink.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = Objects.requireNonNull(stage);
    }

    public void navigateTo(String fxmlPath) throws IOException {

        URL resource = SceneManager.class.getResource(fxmlPath);

        if (resource == null) {
            throw new IOException(
                    "Fichier FXML introuvable : " + fxmlPath
            );
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        // Chargement du CSS commun
        URL cssResource = SceneManager.class.getResource(
                "/css/dashboard.css"
        );

        if (cssResource == null) {
            throw new IOException(
                    "Feuille CSS introuvable : /css/dashboard.css"
            );
        }

        if (stage.getScene() == null) {
            Scene scene = new Scene(root, 1300, 800);

            scene.getStylesheets().add(
                    cssResource.toExternalForm()
            );

            stage.setScene(scene);

        } else {
            stage.getScene().setRoot(root);

            String css = cssResource.toExternalForm();

            if (!stage.getScene().getStylesheets().contains(css)) {
                stage.getScene().getStylesheets().add(css);
            }
        }

        stage.show();
    }
}
