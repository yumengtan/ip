package LuciferExceptions;

/**
 * Class OutOfBoundsException which inherits from LuciferException.
 * @author Yu Meng
 */
public class OutOfBoundsException extends LuciferExceptions {
    /**
     * Constructor for Class OutOfBoundsException.
     */
    public OutOfBoundsException() {
        super("My Dear, that's more than your current desires!\n" +
                "Please give me a valid number so I can grant your desires.\n" +
                "To check your current total tasks,type:\nlist");
    }
}