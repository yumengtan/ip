package Lucifer.Commands;

import Lucifer.Task.Task;
import Lucifer.TaskList.TaskList;

import java.util.ArrayList;

/**
 * Class DeleteCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class DeleteCommands extends ParseCommands {
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
    public String delete(int index) {
        String output = "Got it. I have removed this to your desires:\n";
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        output += "Currently you have " + list.size() + " things yet to be desired";
        return output;
    }
}
