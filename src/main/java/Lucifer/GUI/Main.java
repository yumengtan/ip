package Lucifer.GUI;

import java.io.IOException;

import Lucifer.Lucifer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A Lucifer.GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lucifer lucifer = new Lucifer();
    private Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Lucifer");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setLucifer(lucifer);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}