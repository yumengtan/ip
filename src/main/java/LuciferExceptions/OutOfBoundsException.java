//Author: Yu Meng
//A0218371H

package LuciferExceptions;

public class OutOfBoundsException extends Exception {

    public OutOfBoundsException() {
        super("My Dear, that's more than your current desires!\n" +
                "Please give me a valid number so I can grant your desires.\n" +
                "To check your current total tasks,type:\nlist");
    }
}