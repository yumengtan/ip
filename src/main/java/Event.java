//Author: Yu Meng
//A0218371H

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime dateTime;
    protected String at;
    protected String newFormat;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime old = LocalDateTime.parse(at, formatter);
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String tmp = old.format(formater);
        //this.dateTime = LocalDateTime.parse(tmp, formater);
        this.newFormat = tmp;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String saveFormat() {
        return "E" + " |" + (this.isDone ? "1" : "0") + "| " + this.description + " |" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + newFormat + ")";
    }
}
