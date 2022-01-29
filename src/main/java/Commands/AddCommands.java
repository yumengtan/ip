package Commands;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;
import TaskList.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class AddCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class AddCommands extends ParseCommands{
    /** line after each command for ui purposes **/
    private static final String underscore = "____________________________________________________________";

    /**
     * Constructor for Class AddCommands.
     *
     * @param list the list of current tasks
     */
    public AddCommands(TaskList list) {
        super(list);
    }

    /**
     * Adds the task to the list if it is of type Event.
     *
     * @param desc the description of task
     * @param old the user input format of the event date
     * @param list the list of current tasks
     */
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
    /**
     * Adds the task to the list if it is of type Deadline.
     *
     * @param desc the description of task
     * @param by the date and time of the task deadline
     * @param list the list of current tasks
     */
    public static void addDeadline(String desc, LocalDateTime by, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Deadline curr = new Deadline(desc, by);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }

    /**
     * Adds the task to the list if it is of type Todo.
     *
     * @param desc the description of task
     * @param list the list of current tasks
     */
    public static void addToDo(String desc, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have added this to your desires:");
        Todo curr = new Todo(desc);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }
}
