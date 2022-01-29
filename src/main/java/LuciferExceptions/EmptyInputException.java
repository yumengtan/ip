package LuciferExceptions;

/**
 * Class EmptyInputException which inherits from LuciferException.
 * @author Yu Meng
 */
public class EmptyInputException extends Exception {

    /**
     * Constructor for Class EmptyInputException.
     */
    public EmptyInputException() {
        super("Darling, I can't grant empty desires! Try again my dear.");
    }
}
