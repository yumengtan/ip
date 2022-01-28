import java.util.ArrayList;

public class MarkCommands extends ParseCommands {

    private static final String underscore = "____________________________________________________________";

    public MarkCommands(Ui ui, TaskList list) {
        super(ui, list);
    }

    public static void mark(int num, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Nice! I've marked this task as done: ");
        list.get(num - 1).markDone();
        System.out.println(list.get(num - 1).getStatusIcon());
        System.out.println(underscore);
    }

    public static void unMark(int num, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("OK, I've marked this task as not done yet: ");
        list.get(num - 1).unmarkDone();
        System.out.println(list.get(num - 1).getStatusIcon());
        System.out.println(underscore);
    }
}
