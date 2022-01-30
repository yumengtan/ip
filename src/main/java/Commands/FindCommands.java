package Commands;

import Task.Task;
import TaskList.TaskList;

import java.util.ArrayList;

/**
 * Class FindCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class FindCommands extends ParseCommands {
    /** line after each command for ui purposes **/
    private static final String UNDERSCORE = "____________________________________________________________";
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
    public void find(String description) {
        System.out.println("Here are the matching desires in your list my love:");
        int order = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).saveFormat().contains(description)) {
                System.out.println(" " + order + ". " + list.get(i));
                order ++;
            }
        }
        if (order == 1) {
            System.out.println("You've got nothing like that in your current desires love!");
        }
        System.out.println(UNDERSCORE);
    }
}
