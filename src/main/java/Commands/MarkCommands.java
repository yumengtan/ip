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
    private static final String underscore = "____________________________________________________________";

    /**
     * Constructor for Class MarkCommands.
     *
     * @param list the list of current tasks
     */
    public MarkCommands(TaskList list) {
        super(list);
    }

    /**
     * Marks the task from the list.
     *
     * @param index the index of task to mark
     * @param list the list of current tasks
     */
    public static void mark(int index, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Nice! I've marked this task as done: ");
        list.get(index - 1).markDone();
        System.out.println(list.get(index - 1).getStatusIcon());
        System.out.println(underscore);
    }
    /**
     * Marks the task from the list.
     *
     * @param index the index of task to unmark
     * @param list the list of current tasks
     */
    public static void unMark(int index, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("OK, I've marked this task as not done yet: ");
        list.get(index - 1).unmarkDone();
        System.out.println(list.get(index - 1).getStatusIcon());
        System.out.println(underscore);
    }
}
