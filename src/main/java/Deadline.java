//Author: Yu Meng
//A0218371H

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime dateTime;
    protected String by;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        this.by = formater.format(this.dateTime);
    }

    @Override
    public String saveFormat() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String dateInString = this.dateTime.format(formatter1);
        return "D" + " |" + (this.isDone ? "1" : "0") + "| " + this.description + " | " + dateInString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

