package Task;//Author: Yu Meng
//A0218371H

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime dateTime;
    protected String at;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        this.at = formatter.format(this.dateTime);
    }


    @Override
    public String saveFormat() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String dateInString = this.dateTime.format(formatter1);
        return "E" + " |" + (this.isDone ? "1" : "0") + "| " + this.description + " | " + dateInString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
