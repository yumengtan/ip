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
    private static final String UNDERSCORE = "____________________________________________________________";
    /** list that stores all the task **/
    private ArrayList<Task> list;

    /**
     * Constructor for Class DeleteCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public DeleteCommands(TaskList task) {
        super(task);
        this.list = task.getTaskList();
    }

    /**
     * Deletes the task from the list.
     *
     * @param index the index of task to delete in the list
     */
    public void delete(int index) {
        System.out.println(UNDERSCORE);
        System.out.println("Got it. I have removed this to your desires:");
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(UNDERSCORE);
    }
}
