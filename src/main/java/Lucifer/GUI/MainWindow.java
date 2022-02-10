package Lucifer.GUI;

import Lucifer.Lucifer;
import Lucifer.LuciferExceptions.EmptyInputException;
import Lucifer.UserInterface.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;


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
    private Image background = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Background.jpg")));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = Ui.greeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, luciferImage)
        );
        BackgroundImage backgrnd = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, false, false));
        dialogContainer.setBackground(new Background(backgrnd));
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