
package sio.neurolink;

import javafx.application.Application;
import javafx.stage.Stage;
import sio.neurolink.navigation.SceneManager;

import java.io.IOException;

public class NeuroLinkApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("NeuroLink Clinic");

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        SceneManager sceneManager = new SceneManager(stage);

        sceneManager.navigateTo("/fxml/auth/login.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
