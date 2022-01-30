package Commands;

import Task.Task;
import TaskList.TaskList;

import java.util.ArrayList;

/**
 * Class MarkCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class MarkCommands extends ParseCommands {
    /** line after each command for ui purposes **/
    private static final String UNDERSCORE = "____________________________________________________________";
    /** list that stores all the task **/
    private ArrayList<Task> list;

    /**
     * Constructor for Class MarkCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public MarkCommands(TaskList task) {
        super(task);
        this.list = task.getTaskList();
    }

    /**
     * Marks the task from the list.
     *
     * @param index the index of task to mark
     */
    public void mark(int index) {
        System.out.println(UNDERSCORE);
        System.out.println("Nice! I've marked this task as done: ");
        list.get(index - 1).markDone();
        System.out.println(list.get(index - 1).getStatusIcon());
        System.out.println(UNDERSCORE);
    }

    /**
     * Marks the task from the list.
     *
     * @param index the index of task to unmark
     */
    public void unMark(int index) {
        System.out.println(UNDERSCORE);
        System.out.println("OK, I've marked this task as not done yet: ");
        list.get(index - 1).unmarkDone();
        System.out.println(list.get(index - 1).getStatusIcon());
        System.out.println(UNDERSCORE);
    }
}
