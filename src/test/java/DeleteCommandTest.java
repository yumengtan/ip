import Commands.DeleteCommands;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;
import TaskList.TaskList;
import UserInterface.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {
    
    @Test
    public void DeleteTask() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse("12-12-2022 1800", formatter);
        ArrayList<Task> list = tasks.getTaskList();
        list.add(new Todo("drink water"));
        list.add(new Deadline("eat", dateTime));
        list.add(new Event("sleep", dateTime));
        DeleteCommands.delete(1, list);
        DeleteCommands.delete(1, list);
        DeleteCommands.delete(1, list);
        assertTrue(list.isEmpty());
    }
}
