package Lucifer.Commands;

import Lucifer.LuciferExceptions.InvalidException;
import Lucifer.LuciferExceptions.OutOfBoundsException;
import Lucifer.LuciferExceptions.WrongFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import Lucifer.TaskList.TaskList;
import Lucifer.UserInterface.Ui;

/**
 * Class ParseCommands which encapsulates the command.
 * @author Yu Meng
 */
public class ParseCommands {
    /** A checker to check if it is within Lucifer.Lucifer chatbot commands **/
    private static final String[] WORD_COMMANDS = {"todo", "deadline", "event", "list", "mark", "unmark", "delete", "find"};
    /** Lucifer.TaskList which stores the list of user tasks. **/
    private final TaskList task;

    /**
     * Constructor for Class ParseCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public ParseCommands(TaskList task) {
        this.task = task;
    }

    /**
     * Parses the command from input.
     * @return
     */
    @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:OperatorWrap"})
    public String parseCommand(String command, Ui ui) {
        DeleteCommands deleter = new DeleteCommands(this.task);
        MarkCommands marker = new MarkCommands(this.task);
        FindCommands finder = new FindCommands(this.task);
        AddCommands adder = new AddCommands(this.task);
        try {
            if (command.equals("list")) {
                return task.giveList();
            } else if (command.equals("!help")) {
                return ui.helpCommands();
            } else {
                String[] commands = command.split(" ");
                String theCommand = commands[0].toLowerCase();
                if (Arrays.stream(WORD_COMMANDS).anyMatch(command::contains)) {
                    if (commands.length == 1) {
                        throw new InvalidException();
                    } else if (theCommand.equals("mark") || theCommand.equals("unmark") || theCommand.equals("delete")) {
                        try {
                            int numList = Integer.parseInt(commands[1]);
                            if (numList <= 0 || numList > task.getTaskList().size()) {
                                throw new OutOfBoundsException();
                            }
                            if (theCommand.equals("mark")) {
                                return marker.mark(numList);
                            } else if (theCommand.equals("unmark")) {
                                return marker.unMark(numList);
                            } else if (theCommand.equals("delete")) {
                                return deleter.delete(numList);
                            }
                        } catch (NumberFormatException | OutOfBoundsException e) {
                            return e.getMessage();
                        }
                    } else if (theCommand.equals("find")) {
                        return finder.find(commands[1]);
                    } else if (theCommand.equals("todo")) {
                        String toDo = command.substring(command.indexOf(" ") + 1);
                        if (!toDo.isBlank()) {
                            return adder.addToDo(toDo);
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
                                return adder.addDeadline(description, old);
                            } catch (DateTimeException e) {
                                return "My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n"
                                        + "for help, type !help to see the list of commands available.";
                            }
                        }

                    } else if (theCommand.equals("event")) {
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
                                return adder.addEvent(description, old);
                            } catch (DateTimeException e) {
                                return "My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n"
                                            + "for help, type !help to see the list of commands available.";
                            }
                        }
                    }
                } else {
                    throw new InvalidException();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "My Love, please give me the correct format! type !help to see the list of commands available";
        } catch (WrongFormatException | InvalidException e) {
            return e.getMessage();
        }
        return command;
    }

}
