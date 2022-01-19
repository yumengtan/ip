//Author: Yu Meng
//A0218371H

import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static ArrayList<Task> task = new ArrayList<>();
    private static final String underscore = "____________________________________________________________";
    private static final String[] wordCommands = {"todo", "deadline", "event", "list", "mark", "unmark", "delete"};

    public static void main(String[] args) {
        //greetings
        greeting();

        //get input
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {

                String command = sc.nextLine();

                if (command.equals("")) {
                    throw new EmptyInputException();
                }

                if (command.equals("list")) {
                    giveList();

                } else if (command.equals("bye")) {
                    //farewell message
                    farewell();
                    break;

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
                            }
                        }  else if (theCommand.equals("todo") || theCommand.equals("deadline") || theCommand.equals("event")) {
                            String toDo = command.substring(command.indexOf(" ") + 1);
                            if (commands[1].equals("")) {
                                throw new InvalidException();
                            }
                            if (theCommand.equals("todo")) {
                            if (toDo.length() == 0) {
                                throw new WrongFormatException();
                            } else {
                                addToDo(toDo);
                            }
                        }   else if (theCommand.equals("deadline")) {
                            String[] dead = command.split("/by");
                            if (dead[1].isBlank()) {
                                throw new InvalidException();
                            } else {
                                String deadline = dead[1];
                                String description = commands[1];
                                addDeadline(description, deadline);
                            }
                        } else if (theCommand.equals("event")) {
                            String[] eventTime = command.split("/at");
                            if (eventTime[1].isBlank()) {
                                throw new InvalidException();
                            } else {
                                String description = commands[1];
                                String time = eventTime[1];
                                addEvent(description, time);
                            }
                        }
                    } else {
                        addList(command);
                    }
                }
            } catch (EmptyInputException | WrongFormatException | InvalidException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();

    }

    public static void addList(String adder) {
        Task curr = new Task(adder);
        task.add(curr);
        System.out.println(underscore);
        System.out.println("added " + curr.description);
        System.out.println(underscore);
    }

    public static void giveList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            int order = i + 1;
            System.out.println(" " + order + ". " + task.get(i));
        }
        System.out.println(underscore);
    }

    public static void farewell() {
        System.out.println(underscore);
        System.out.println("ʕ•́ᴥ•̀ʔっ Oh no! I hate to see you go:(");
        System.out.println("Have I granted your desires?\nPlease come back again!");
        System.out.println(underscore);
    }

    public static void greeting() {
        System.out.println(underscore);
        System.out.println("(◍´◕ᴥ◕) Hello there! I'm Lucifer.\nWhat is it you desire today?");
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
        task.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + task.size() + " things yet to be desired");
        System.out.println(underscore);
    }



}
