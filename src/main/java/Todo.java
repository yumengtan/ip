//Author: Yu Meng
//A0218371H

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveFormat() {
        return "T" + " |" + (this.isDone ? "1" : "0") + "| " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
