import java.util.ArrayList;

public class DeleteCommands extends ParseCommands {

    private static final String underscore = "____________________________________________________________";

    public DeleteCommands(Ui ui, TaskList list) {
        super(ui, list);
    }
    public static void delete(int index, ArrayList<Task> list) {
        System.out.println(underscore);
        System.out.println("Got it. I have removed this to your desires:");
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        System.out.println("Currently you have " + list.size() + " things yet to be desired");
        System.out.println(underscore);
    }
}
