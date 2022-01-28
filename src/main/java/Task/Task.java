package Task;//Author: Yu Meng
//A0218371H

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ((isDone ? "[X] " : "[ ] ") + this.description); // mark done task with X
    }

    public abstract String saveFormat();

    @Override
    public String toString() {
        return getStatusIcon();
    }

}
