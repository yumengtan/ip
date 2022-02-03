import Commands.ParseCommands;
import Task.Deadline;
import Task.Event;
import Task.Todo;
import TaskList.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandsTest {
    private TaskList tasks = new TaskList();
    private ParseCommands parser = new ParseCommands(tasks);

    @Test
    public void addDeadLineTest(){
        parser.parseCommand("deadline do homework /by 12-12-2022 1800");
        assertTrue(tasks.getTaskList().get(0) instanceof Deadline);
    }

    @Test
    public void addEventTest(){
        parser.parseCommand("event do homework /at 12-12-2022 1800");
        assertTrue(tasks.getTaskList().get(0) instanceof Event);
    }
    @Test
    public void addToDo(){
        parser.parseCommand("todo do homework");
        assertTrue(tasks.getTaskList().get(0) instanceof Todo);
    }

}

