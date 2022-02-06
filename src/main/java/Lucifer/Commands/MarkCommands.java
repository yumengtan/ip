package Lucifer.Commands;

import Lucifer.Task.Task;
import Lucifer.TaskList.TaskList;

import java.util.ArrayList;

/**
 * Class MarkCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class MarkCommands extends ParseCommands {
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
    public String mark(int index) {
        String output = "Nice! I've marked this task as done: \n";
        list.get(index - 1).markDone();
        output += list.get(index - 1).getStatusIcon() + "\n";
        return output;
    }

    /**
     * Marks the task from the list.
     *
     * @param index the index of task to unmark
     */
    public String unMark(int index) {
        String output = "OK, I've marked this task as not done yet: \n";
        list.get(index - 1).unmarkDone();
        output += list.get(index - 1).getStatusIcon() + "\n";
        return output;
    }
}
