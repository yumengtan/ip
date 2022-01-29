package Commands;

import Task.Task;
import TaskList.TaskList;

import java.util.ArrayList;

/**
 * Class DeleteCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class DeleteCommands extends ParseCommands {
    /** line after each command for ui purposes **/
    private static final String underscore = "____________________________________________________________";

    /**
     * Constructor for Class DeleteCommands.
     *
     * @param list the list of current tasks
     */
    public DeleteCommands(TaskList list) {
        super(list);
    }

    /**
     * Deletes the task from the list.
     *
     * @param index the index of task to delete in the list
     * @param list the list of current tasks
     */
    public static void delete(int index, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have removed this to your desires:");
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }
}
