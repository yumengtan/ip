package Lucifer.Commands;

import java.util.ArrayList;

import Lucifer.LuciferExceptions.InvalidException;
import Lucifer.LuciferExceptions.OutOfBoundsException;
import Lucifer.Task.Task;
import Lucifer.TaskList.TaskList;

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
     * @param input the user input
     */
    public String mark(String input) {
        try {
            String[] split = input.split(" ");
            if (split.length == 1) {
                throw new InvalidException();
            }
            int index = Integer.parseInt(split[1]);
            if (index <= 0 || index > list.size()) {
                throw new OutOfBoundsException();
            }
            String output = "Nice! I've marked this task as done: \n";
            list.get(index - 1).markDone();
            output += list.get(index - 1).getStatusIcon() + "\n";
            return output;
        } catch (NumberFormatException | OutOfBoundsException | InvalidException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks the task from the list.
     *
     * @param input the user input
     */
    public String unMark(String input) {
        try {
            String[] split = input.split(" ");
            if (split.length == 1) {
                throw new InvalidException();
            }
            int index = Integer.parseInt(split[1]);
            if (index <= 0 || index > list.size()) {
                throw new OutOfBoundsException();
            }
            String output = "OK, I've marked this task as not done yet: \n";
            list.get(index - 1).unmarkDone();
            output += list.get(index - 1).getStatusIcon() + "\n";
            return output;
        } catch (NumberFormatException | OutOfBoundsException | InvalidException e) {
            return e.getMessage();
        }
    }
}
