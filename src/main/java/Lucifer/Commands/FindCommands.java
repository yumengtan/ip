package Lucifer.Commands;

import Lucifer.Task.Task;
import Lucifer.TaskList.TaskList;

import java.util.ArrayList;

/**
 * Class FindCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class FindCommands extends ParseCommands {
    /** list that stores all the task **/
    private ArrayList<Task> list;
    
    /**
     * Constructor for Class MarkCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public FindCommands(TaskList task) {
        super(task);
        this.list = task.getTaskList();

    }

    /**
     * Marks the task from the list.
     *  @param description the index of task to mark
     *
     */
    public String find(String description) {
        String output = "Here are the matching desires in your list my love:\n";
        int order = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).saveFormat().contains(description)) {
                output += " " + order + ". " + list.get(i) + "\n";
                order++;
            }
        }
        if (order == 1) {
            return "You've got nothing like that in your current desires love!";
        }
        return output;
    }
}
