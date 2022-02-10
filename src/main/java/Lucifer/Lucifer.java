package Lucifer;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import Lucifer.Commands.ParseCommands;
import Lucifer.GUI.DialogBox;
import Lucifer.LuciferExceptions.EmptyInputException;
import Lucifer.Storage.Storage;
import Lucifer.TaskList.TaskList;
import Lucifer.UserInterface.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * Lucifer.GUI.Main class which runs the Lucifer.Lucifer chatbot
 * @author Yu Meng
 */
public class Lucifer extends Application {
    /**
    * Lucifer.Storage which saves or load tasks on hand.
    **/
    private final Storage storage;
    /**
     * Lucifer.TaskList which stores the list of user tasks.
     **/
    private final TaskList tasks;
    /**
     * Lucifer.TaskList which stores the list of user tasks.
     **/
    private final Ui ui;
    /**
     * Ui that represents the interaction messages that user will have with Lucifer.Lucifer chatbot
     **/
    private final ParseCommands parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image lucifer = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Lucifer.jpg")));
    /**
       * Constructor for Lucifer.Lucifer chatbot.
       */
    public Lucifer() throws IOException {
        ui = new Ui();
        storage = new Storage(System.getProperty("user.dir"));
        tasks = new TaskList(storage.loadList());
        parser = new ParseCommands(tasks);
    }

    /**
     * Starts the Lucifer.Lucifer chatbot.
     */
    private void run() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String command = sc.nextLine();
                if (command.isBlank()) {
                    throw new EmptyInputException();
                }
                if (command.equals("bye")) {
                    ui.farewell();
                    storage.saveFileList(tasks.getTaskList());
                    break;
                } else {
                    parser.parseCommand(command);
                }
            } catch (EmptyInputException | IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * The main method of Lucifer.Lucifer chatbot.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) throws IOException {
        new Lucifer().run();
    }
    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Formatting the window to look as expected
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent");

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        //Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException | EmptyInputException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException | EmptyInputException e) {
                e.printStackTrace();
            }
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    /**
       * Iteration 1:
       * Creates a label with the specified text and adds it to the dialog container.
       *
       * @param text String containing text to add
       * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException, EmptyInputException {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, lucifer)
        );
        userInput.clear();
    }
    /**
     * Retrieves the user input and parses the command given by user
     */
    public String getResponse(String input) {
        String output;
        try {
            if (input.isBlank() || input == null) {
                throw new EmptyInputException();
            } else if (input.equals("bye")) {
                output = ui.farewell() + "\nYour desires have been etched into my memories my love.";
                storage.saveFileList(tasks.getTaskList());
            } else {
                output = parser.parseCommand(input);
            }

        } catch (EmptyInputException | IOException e) {
            output = e.getMessage();
        }
        return output;
    }
}

