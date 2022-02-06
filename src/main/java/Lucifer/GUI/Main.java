package Lucifer;

import java.io.IOException;

import Lucifer.GUI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A Lucifer.GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lucifer lucifer = new Lucifer();

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLucifer(lucifer);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}