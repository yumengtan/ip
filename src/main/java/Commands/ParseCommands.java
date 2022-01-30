package Commands;

import LuciferExceptions.InvalidException;
import LuciferExceptions.OutOfBoundsException;
import LuciferExceptions.WrongFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import Task.Task;
import TaskList.TaskList;
import UserInterface.Ui;

/**
 * Class ParseCommands which encapsulates the command.
 * @author Yu Meng
 */
public class ParseCommands {
    /** A checker to check if it is within Lucifer chatbot commands **/
    private static final String[] wordCommands = {"todo", "deadline", "event", "list", "mark", "unmark", "delete"};
    /** TaskList which stores the list of user tasks. **/
    private final TaskList task;

    /**
     * Constructor for Class ParseCommands.
     *
     * @param task stores the list of task
     */
    public ParseCommands(TaskList task) {
        this.task = task;
    }

    /**
     * Parses the command from input.
     *
     */
    public void parseCommand(String command, ArrayList<Task> list) {
        try {
            if (command.equals("list")) {
                task.giveList();
            } else if (command.equals("!help")) {
                Ui.helpCommands();
            } else {
                String[] commands = command.split(" ");
                String theCommand = commands[0].toLowerCase();
                if (Arrays.stream(wordCommands).anyMatch(command::contains)) {
                    if (commands.length == 1) {
                        throw new InvalidException();
                    } else if (theCommand.equals("mark") || theCommand.equals("unmark") || theCommand.equals("delete")) {
                        try {
                            int numList = Integer.parseInt(commands[1]);
                            if (numList <= 0 || numList > list.size()) {
                                throw new OutOfBoundsException();
                            }
                            if (theCommand.equals("mark")) {
                                MarkCommands.mark(numList, list);
                            } else if (theCommand.equals("unmark")) {
                                MarkCommands.unMark(numList, list);
                            } else if (theCommand.equals("delete")) {
                                DeleteCommands.delete(numList, list);
                            }
                        } catch (NumberFormatException | OutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (theCommand.equals("todo")) {
                        String toDo = command.substring(command.indexOf(" ") + 1);
                        if (!toDo.isBlank()) {
                            AddCommands.addToDo(toDo, list);
                        } else {
                            throw new WrongFormatException();
                        }
                    } else if (theCommand.equals("deadline")) {
                        String[] dead = command.split("/by ");
                        if (commands[1].equals("")) {
                            throw new InvalidException();
                        }
                        if (dead[1].isBlank()) {
                            throw new InvalidException();
                        } else {
                            try {
                                String deadline = dead[1];
                                String description = commands[1];
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                                LocalDateTime old = LocalDateTime.parse(deadline, formatter);
                                AddCommands.addDeadline(description, old, list);
                            } catch (DateTimeException e) {
                                System.out.println("My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n" +
                                        "for help, type !help to see the list of commands available.");
                            }
                        }

                    } else if (theCommand.equals("event")) {
                        String dateTime = command.substring(command.indexOf("/"));
                        String[] eventTime = command.split("/at ");
                        if (commands[1].equals("")) {
                            throw new InvalidException();
                        }
                        if (eventTime[1].isBlank()) {
                            throw new InvalidException();
                        } else {
                            try {
                                String description = commands[1];
                                String time = eventTime[1];
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                                LocalDateTime old = LocalDateTime.parse(time, formatter);
                                AddCommands.addEvent(description, old, list);
                            } catch (DateTimeException e) {
                                System.out.println("My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n" +
                                        "for help, type !help to see the list of commands available.");
                            }
                        }
                    }
                } else {
                    throw new InvalidException();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("My Love, please give me the correct format! type !help to see the list of commands available");
        } catch (WrongFormatException | InvalidException e) {
            System.out.println(e.getMessage());
        }
    }

}
