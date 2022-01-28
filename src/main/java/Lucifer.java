//Author: Yu Meng
//A0218371H

import LuciferExceptions.*;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;



public class Lucifer {

    private static ArrayList<Task> task = new ArrayList<>();
    private static final String underscore = "____________________________________________________________";
    private static final String[] wordCommands = {"todo", "deadline", "event", "list", "mark", "unmark", "delete"};

    public static void main(String[] args) throws IOException {
        //greetings
        greeting();

        Storage storage = new Storage(System.getProperty("user.dir"));
        storage.loadList(task);

        //get input
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {

                String command = sc.nextLine();

                if (command.isBlank() || command == null) {
                    throw new EmptyInputException();
                }

                if (command.equals("list")) {
                    giveList();

                } else if (command.equals("bye")) {
                    //farewell message
                    storage.saveFileList(task);
                    farewell();
                    break;
                } else if (command.equals("!help")) {
                    helpCommands();
                } else {
                    String[] commands = command.split(" ");
                    String theCommand = commands[0].toLowerCase();
                    if (Arrays.stream(wordCommands).anyMatch(command::contains)) {
                        if (commands.length == 1) {
                            throw new InvalidException();
                        } else if (theCommand.equals("mark") || theCommand.equals("unmark") || theCommand.equals("delete")) {
                            try {
                                int numList = Integer.parseInt(commands[1]);
                                if (numList <= 0 || numList > task.size()) {
                                    throw new OutOfBoundsException();
                                }
                                if (theCommand.equals("mark")) {
                                    mark(numList);
                                } else if (theCommand.equals("unmark")) {
                                    unmark(numList);
                                } else if (theCommand.equals("delete")) {
                                    delete(numList);
                                }
                            } catch (NumberFormatException | OutOfBoundsException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else if (theCommand.equals("todo")) {
                        String toDo = command.substring(command.indexOf(" ") + 1);
                        if (!toDo.isBlank()) {
                            addToDo(toDo);
                        } else {
                            throw new WrongFormatException();
                        }
                        }  else if (theCommand.equals("deadline")) {
                            String dateTime = command.substring(command.indexOf("/"));

                            String[] dead = command.split("/by ");
                        if (commands[1].equals("")) {
                            throw new InvalidException();
                        }
                        if (dead[1].isBlank()) {
                                throw new InvalidException();
                            } else {
                                String deadline = dead[1];
                                String description = commands[1];
                                addDeadline(description, deadline);
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
                                String description = commands[1];
                                String time = eventTime[1];
                                addEvent(description, time);
                            }
                        }
                    } else {
                        throw new InvalidException();
                    }
                }
            } catch (EmptyInputException | WrongFormatException | InvalidException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();

    }

    public static void giveList() {
        System.out.println("Here are the desires in your list:");
        for (int i = 0; i < task.size(); i++) {
            int order = i + 1;
            System.out.println(" " + order + ". " + task.get(i));
        }
        System.out.println(underscore);
    }

    public static void farewell() {
        System.out.println(underscore);
        System.out.println("（￣ｗ￣）Ψ Oh no! I hate to see you go:(");
        System.out.println("Have I granted your desires?\nPlease come back again!");
        System.out.println(underscore);
    }

    public static void greeting() {
        System.out.println(underscore);
        System.out.println("↜(╰ •ω• )╯ψ Hello there! I'm Lucifer.\nWhat is it you desire today?");
        System.out.println(underscore);
    }

    public static void mark(int num) {
        System.out.println(underscore);
        System.out.println("Nice! I've marked this task as done: ");
        task.get(num - 1).markDone();
        System.out.println(task.get(num - 1).getStatusIcon());
        System.out.println(underscore);
    }

    public static void unmark(int num) {
        System.out.println(underscore);
        System.out.println("OK, I've marked this task as not done yet: ");
        task.get(num - 1).unmarkDone();
        System.out.println(task.get(num - 1).getStatusIcon());
        System.out.println(underscore);
    }

    public static void delete(int index) {
        System.out.println(underscore);
        System.out.println("Got it. I have removed this to your desires:");
        System.out.println(task.get(index - 1));
        task.remove(index - 1);
        System.out.println("Currently you have " + task.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    public static void addEvent(String desc, String at) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Event curr = new Event(desc, at);
        curr.convertDateTime();
        task.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + task.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    public static void addToDo(String adder) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Todo curr = new Todo(adder);
        task.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + task.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    public static void addDeadline(String desc, String by) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Deadline curr = new Deadline(desc, by);
        curr.convertDateTime();
        task.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + task.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    public static void helpCommands() {
        System.out.println(underscore);
        System.out.println("Alright love, here are the list of desires I can grant:");
        System.out.println("\tlist\t :I will show you what your current desires are.");
        System.out.println("\tdelete\t :I will remove this desires from your current list.");
        System.out.println("\ttodo (desire)\t :I will add this desire to your todo list.");
        System.out.println("\tevent (desire) /at 31-12-2022 1800\t :I will add this desire to your list with the date & time.");
        System.out.println("\tdeadline (desire) /by 31-12-2022 1800\t :I will add this desire to your list with its deadline.");
        System.out.println("\tmark (number)\t :I can mark this desire in your list as done.");
        System.out.println("\tmark (number)\t :I can unmark this desire in your list as not done.");
        System.out.println("\tbye\t: I will end our lovely little conversation for now");
        System.out.println("Now let's let back to what we are doing now shall we? ψ\uD83D\uDC7F\uD83D\uDD31⸸");
        System.out.println(underscore);
    }
    /*
    private static LocalDateTime convert(String dateTime) throws DateTimeException {
        LocalDateTime old = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            old = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeException e) {
            System.out.println("My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n" +
                    "for help, type !help to see the list of commands available.");
        }
        return old;
    }
    */

}
