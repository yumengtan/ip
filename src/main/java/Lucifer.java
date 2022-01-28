//Author: Yu Meng
//A0218371H

import LuciferExceptions.*;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.Arrays;
import java.util.Scanner;

public class Lucifer {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final ParseCommands parser;

    //private static ArrayList<Task> task = new ArrayList<>();
    private static final String underscore = "____________________________________________________________";
    private static final String[] wordCommands = {"todo", "deadline", "event", "list", "mark", "unmark", "delete"};

    public Lucifer() throws IOException {
        ui = new Ui();
        storage = new Storage(System.getProperty("user.dir"));
        tasks = new TaskList(storage.loadList());
        parser = new ParseCommands(ui, tasks);

    }

    public void run() throws IOException {
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
                    parser.parseCommand(command, tasks.getTaskList());
                }
            } catch (EmptyInputException | IOException e) {
                e.getMessage();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Lucifer().run();
    }

}
