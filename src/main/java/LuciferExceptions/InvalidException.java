package LuciferExceptions;

/**
 * Class InvalidException which inherits from LuciferException.
 * @author Yu Meng
 */
public class InvalidException extends LuciferExceptions {
    /**
     * Constructor for Class InvalidException.
     */
    public InvalidException() {
        super("My my, you've got to specify your desire love\n( • ∀•)–Ψ Let me help you with an example:\n" +
        "deadline return book /by 12-12-2022 1800\nPlease try again my dear!\nTo check on known commands, type:\n!help");
    }
}
