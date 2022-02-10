package Lucifer.Commands;

import java.util.ArrayList;

import Lucifer.LuciferExceptions.InvalidException;
import Lucifer.LuciferExceptions.OutOfBoundsException;
import Lucifer.Task.Task;
import Lucifer.TaskList.TaskList;

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
     * @param input the user input
     */
    public String delete(String input) {
        try {
            String[] split = input.split(" ");
            if (split.length == 1) {
                throw new InvalidException();
            }
            int index = Integer.parseInt(split[1]);
            if (index <= 0 || index > list.size()) {
                throw new OutOfBoundsException();
            }
            String output = "Got it. I have removed this to your desires:\n";
            System.out.println(list.get(index - 1));
            list.remove(index - 1);
            output += "Currently you have " + list.size() + " things yet to be desired";
            return output;
        } catch (NumberFormatException | OutOfBoundsException | InvalidException e) {
            return e.getMessage();
        }
    }
}
