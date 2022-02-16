package Lucifer;

import java.io.IOException;
import java.util.Scanner;

import Lucifer.Commands.ParseCommands;
import Lucifer.LuciferExceptions.EmptyInputException;
import Lucifer.LuciferExceptions.InvalidException;
import Lucifer.Storage.Storage;
import Lucifer.TaskList.TaskList;
import Lucifer.UserInterface.Ui;


/**
 * Lucifer.GUI.Main class which runs the Lucifer.Lucifer chatbot
 * @author Yu Meng
 */
public class Lucifer {
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
            } catch (EmptyInputException | IOException | InvalidException e) {
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

        } catch (EmptyInputException | IOException | InvalidException e) {
            output = e.getMessage();
        }
        return output;
    }
}

