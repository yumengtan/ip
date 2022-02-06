package Lucifer.Commands;

import Lucifer.Task.Deadline;
import Lucifer.Task.Event;
import Lucifer.Task.Task;
import Lucifer.Task.Todo;
import Lucifer.TaskList.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class AddCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class AddCommands extends ParseCommands {
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
    public String addEvent(String desc, LocalDateTime old) {
        String output = "Got it. I have added this to your desires:\n";
        Event curr = new Event(desc, old);
        list.add(curr);
        output += curr;
        output += "Currently you have " + list.size() + " things yet to be desired";
        return output;
    }
    /**
     * Adds the task to the list if it is of type Deadline.
     *
     * @param description the description of task
     * @param by the date and time of the task deadline
     */
    public String addDeadline(String description, LocalDateTime by) {
        String output = "Got it. I have added this to your desires:\n";
        Deadline curr = new Deadline(description, by);
        list.add(curr);
        output += curr;
        output += "\nCurrently you have " + list.size() + " things yet to be desired";
        return output;
    }

    /**
     * Adds the task to the list if it is of type Todo.
     *
     * @param description the description of task
     */
    public String addToDo(String description) {
        String output = "Got it. I have added this to your desires:\n";
        Todo curr = new Todo(description);
        list.add(curr);
        output += curr;
        output += "\nCurrently you have " + list.size() + " things yet to be desired";
        return output;
    }
}
