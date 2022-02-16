package Lucifer.GUI;

import java.util.Objects;

import Lucifer.Lucifer;
import Lucifer.UserInterface.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for Lucifer.GUI.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Lucifer lucifer;
    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private Image luciferImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Lucifer.jpg")));
    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = Ui.greeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, luciferImage)
        );
    }

    public void setLucifer(Lucifer l) {
        lucifer = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lucifer.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, luciferImage)
        );
        if (input.startsWith("bye")) {
            Ui.farewell();
            Platform.exit();
        }
        userInput.clear();
    }
}
