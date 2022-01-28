package Commands;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;
import TaskList.TaskList;
import UserInterface.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddCommands extends ParseCommands{

    private static final String underscore = "____________________________________________________________";

    public AddCommands(Ui ui, TaskList list) {
        super(ui, list);
    }
    public static void addEvent(String desc, LocalDateTime old, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Event curr = new Event(desc, old);
        //curr.convertDateTime();
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    public static void addDeadline(String desc, LocalDateTime by, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Deadline curr = new Deadline(desc, by);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    public static void addToDo(String adder, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Todo curr = new Todo(adder);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }
}
