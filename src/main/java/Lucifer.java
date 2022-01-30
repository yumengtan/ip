import Commands.ParseCommands;
import LuciferExceptions.EmptyInputException;
import Storage.Storage;
import TaskList.TaskList;
import UserInterface.Ui;

import java.io.IOException;

import java.util.Scanner;

  /**
 * Main class which runs the Lucifer chatbot
 * @author Yu Meng
 */
 public class Lucifer {

     /** Storage which saves or load tasks on hand. **/
    private final Storage storage;
      /** TaskList which stores the list of user tasks. **/
    private final TaskList tasks;
      /** TaskList which stores the list of user tasks. **/
    private final Ui ui;
      /**Ui that represents the interaction messages that user will have with Lucifer chatbot **/
    private final ParseCommands parser;

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
                    parser.parseCommand(command);
                }
            } catch (EmptyInputException | IOException e) {
                e.getMessage();
            }
        }
    }

      /**
       * The main method of Lucifer chatbot.
       * @param args The input arguments.
       */
    public static void main(String[] args) throws IOException {
        new Lucifer().run();
    }

}
