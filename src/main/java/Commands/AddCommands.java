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
    private static final String UNDERSCORE = "____________________________________________________________";
    /** list that stores all the task **/
    private ArrayList<Task> list;

    /**
     * Constructor for Class AddCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public AddCommands(TaskList task) {
        super(task);
        this.list = task.getTaskList();
    }

    /**
     * Adds the task to the list if it is of type Event.
     *
     * @param desc the description of task
     * @param old the user input format of the event date
     */
    public void addEvent(String desc, LocalDateTime old) {
        System.out.println(UNDERSCORE);
        System.out.println("Got it. I have added this to your desires:");
        Event curr = new Event(desc, old);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(UNDERSCORE);
    }
    /**
     * Adds the task to the list if it is of type Deadline.
     *
     * @param description the description of task
     * @param by the date and time of the task deadline
     */
    public void addDeadline(String description, LocalDateTime by) {
        System.out.println(UNDERSCORE);
        System.out.println("Got it. I have added this to your desires:");
        Deadline curr = new Deadline(description, by);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(UNDERSCORE);
    }

    /**
     * Adds the task to the list if it is of type Todo.
     *
     * @param description the description of task
     */
    public void addToDo(String description) {
        System.out.println(UNDERSCORE);
        System.out.println("Got it. I have added this to your desires:");
        Todo curr = new Todo(description);
        list.add(curr);
        System.out.println(curr);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(UNDERSCORE);
    }
}
