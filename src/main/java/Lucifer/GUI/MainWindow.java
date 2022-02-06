package GUI;

import LuciferExceptions.EmptyInputException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.TimeUnit;

/**
 * Controller for GUI.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lucifer lucifer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image luciferImage = new Image(this.getClass().getResourceAsStream("/images/Lucifer.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLucifer(Lucifer l) {
        lucifer = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException, EmptyInputException {
        String input = userInput.getText();
        String response = lucifer.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, luciferImage)
        );
        if (input.startsWith("bye")) {
            TimeUnit.SECONDS.sleep(3);
            Platform.exit();
        }
        userInput.clear();
    }
}