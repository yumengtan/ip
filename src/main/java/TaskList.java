import java.util.ArrayList;

public class TaskList {
    private static final String underscore = "____________________________________________________________";
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void giveList() {
        System.out.println("Here are the desires in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            int order = i + 1;
            System.out.println(" " + order + ". " + this.list.get(i));
        }
        System.out.println(underscore);
    }

    public ArrayList<Task> getTaskList() {
        return this.list;
    }
}
