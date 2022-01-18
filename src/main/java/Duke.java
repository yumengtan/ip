//Author: Yu Meng
//A0218371H

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> task = new ArrayList<>();
    private static final String underscore = "____________________________________________________________";

    public static void main(String[] args) {
        //greetings
        greeting();

        //get input
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();

            if (command.equals("list")) {
                giveList();
            } else if (command.equals("bye")) {
                //farewell message
                farewell();
                break;
            } else {
                String[] commands = command.split(" ");
                if (commands[0].equals("mark")) {
                    mark(Integer.parseInt(commands[1]));
                } else if (commands[0].equals("unmark")) {
                    unmark(Integer.parseInt(commands[1]));
                } else if (commands[0].equals("todo")) {
                    String toDo = command.substring(command.indexOf(" ") + 1);
                    addToDo(toDo);
                } else if (commands[0].equals("deadline")) {
                    String[] dead = command.split("/by");
                    String deadline = dead[1];
                    String description = commands[1];
                    addDeadline(description, deadline);
                } else if (commands[0].equals("event")) {
                    String[] eventTime = command.split("/at");
                    String description = commands[1];
                    String time = eventTime[1];
                    addEvent(description, time);
                } else {
                    addList(command);
                }
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
