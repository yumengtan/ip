import java.io.IOException;
import java.util.Scanner;

import Commands.ParseCommands;
import GUI.DialogBox;
import LuciferExceptions.EmptyInputException;
import Storage.Storage;
import TaskList.TaskList;
import UserInterface.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * Main class which runs the Lucifer chatbot
 * @author Yu Meng
 */
 public class Lucifer extends Application {

      /**
       * Storage which saves or load tasks on hand.
       **/
      private final Storage storage;
      /**
       * TaskList which stores the list of user tasks.
       **/
      private final TaskList tasks;
      /**
       * TaskList which stores the list of user tasks.
       **/
      private final Ui ui;
      /**
       * Ui that represents the interaction messages that user will have with Lucifer chatbot
       **/
      private final ParseCommands parser;

      private ScrollPane scrollPane;
      private VBox dialogContainer;
      private TextField userInput;
      private Button sendButton;
      private Scene scene;
      private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
      private Image lucifer = new Image(this.getClass().getResourceAsStream("/images/Lucifer.jpg"));

      /**
       * Constructor for Lucifer chatbot.
       */
      public Lucifer() throws IOException {
          ui = new Ui();
          storage = new Storage(System.getProperty("user.dir"));
          tasks = new TaskList(storage.loadList());
          parser = new ParseCommands(tasks);

      }

      /**
       * Starts the Lucifer chatbot.
       */
      private void run() {
          ui.greeting();
          Scanner sc = new Scanner(System.in);
          while (sc.hasNext()) {
              try {
                  String command = sc.nextLine();
                  if (command.isBlank() || command == null) {
                      throw new EmptyInputException();
                  }
                  if (command.equals("bye")) {
                      ui.farewell();
                      storage.saveFileList(tasks.getTaskList());
                      break;
                  } else {
                      parser.parseCommand(command, ui);
                  }
              } catch (EmptyInputException | IOException e) {
                  e.getMessage();
              }
          }
      }

      /**
       * The main method of Lucifer chatbot.
       *
       * @param args The input arguments.
       */
      public static void main(String[] args) throws IOException {
          new Lucifer().run();
      }


      @Override
      public void start(Stage stage) {
          //Step 1. Setting up required components

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

          //Step 2. Formatting the window to look as expected
          stage.setTitle("Lucifer");
          stage.setResizable(false);
          stage.setMinHeight(600.0);
          stage.setMinWidth(400.0);

          mainLayout.setPrefSize(400.0, 600.0);

          scrollPane.setPrefSize(385, 535);
          scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
          scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

          scrollPane.setVvalue(1.0);
          scrollPane.setFitToWidth(true);

          // You will need to import `javafx.scene.layout.Region` for this.
          dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

          userInput.setPrefWidth(325.0);

          sendButton.setPrefWidth(55.0);

          AnchorPane.setTopAnchor(scrollPane, 1.0);

          AnchorPane.setBottomAnchor(sendButton, 1.0);
          AnchorPane.setRightAnchor(sendButton, 1.0);

          AnchorPane.setLeftAnchor(userInput, 1.0);
          AnchorPane.setBottomAnchor(userInput, 1.0);

          //Step 3. Add functionality to handle user input.
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
          // You will need to import `javafx.scene.control.Label`.
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
       * You should have your own function to generate a response to user input.
       * Replace this stub with your completed method.
       */
      String getResponse(String input) throws EmptyInputException {
          String output;
          try {
              if (input.isBlank() || input == null) {
                  throw new EmptyInputException();
              } else if (input.equals("bye")) {
                  output = ui.farewell() + "\nYour desires have been etched into my memories my love.";
                  storage.saveFileList(tasks.getTaskList());
              } else {
                  output = parser.parseCommand(input, ui);
              }

          } catch (EmptyInputException | IOException e) {
              output = e.getMessage();
          }
          return output;
      }
  }

