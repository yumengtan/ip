package TaskList;

import Task.Task;

import java.util.ArrayList;

/**
 * Class TaskList that handles the list of tasks.
 * @author Yu Meng
 */
public class TaskList {
    /** line after each command for ui purposes **/
    private static final String underscore = "____________________________________________________________";
    /** ArrayList which stores the tasks **/
    private final ArrayList<Task> list;
    /**
     * Constructor for Class Ui if no saved list.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }
    /**
     * Constructor for Class Ui if there is a saved list.
     */
    public TaskList(ArrayList<Task> list) {

        this.list = list;
    }
    /**
     * outputs the list of current tasks user has.
     */
    public void giveList() {
        if (list.size() == 0) {
            System.out.println("Your list is currently empty!\n" +
                    "Please input some commands my love.");
        } else {
            System.out.println("Here are the desires in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                int order = i + 1;
                System.out.println(" " + order + ". " + this.list.get(i));
            }
            System.out.println(underscore);
        }
    }
    /**
     * Returns list of tasks for JUnit test.
     *
     * @return return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.list;
    }
}
