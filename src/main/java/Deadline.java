//Author: Yu Meng
//A0218371H

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime dateTime;
    protected String by;
    protected String newFormat;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime old = LocalDateTime.parse(by, formatter);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String tmp = old.format(formatter1);
        //this.dateTime = LocalDateTime.parse(tmp, formatter1);
        this.newFormat = tmp;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String saveFormat() {
        return "D" + " |" + (this.isDone ? "1" : "0") + "| " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.newFormat + ")";
    }
}

