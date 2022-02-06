package Lucifer.Task;

/**
 * Class Todo which inherits from Lucifer.Task.
 * @author Yu Meng
 */
public class Todo extends Task {

    /**
     * Constructor for Lucifer.Task Objects.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     {@inheritdoc}
     */
    @Override
    public String saveFormat() {
        return "T" + " |" + (this.isDone ? "1" : "0") + "| " + this.description;
    }

    /**
     {@inheritdoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
