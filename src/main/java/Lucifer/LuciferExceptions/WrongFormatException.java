package Lucifer.LuciferExceptions;

/**
 * Class WrongFormatException which inherits from LuciferException.
 * @author Yu Meng
 */
public class WrongFormatException extends LuciferExceptions {
    /**
     * Constructor for Class WrongFormatException.
     */
    public WrongFormatException() {
        super("Oh Deary me! You have to tell me what you specifically desire.\n to check on known commands,"
                + "type !help");
    }
}
