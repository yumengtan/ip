package Lucifer.UserInterface;

/**
 * Class Ui that represents the interaction messages that user will have with Lucifer.Lucifer chatbot.
 * @author Yu Meng
 */
public class Ui {
    /** line after each command for ui purposes **/
    public static final String UNDERSCORE = "____________________________________________________________";

    /**
     * Constructor for Class Ui.
     */
    public Ui() {

    }
    /**
     * Prints the farewell message when user inputs "bye".
     */
    public String farewell() {
         return UNDERSCORE + "\n（￣ｗ￣）Ψ Oh no! I hate to see you go:(\n" +
                "Have I granted your desires?\nPlease come back again!\n" + UNDERSCORE;
    }

    /**
     * Prints the opening message when chatbot runs.
     */
    @SuppressWarnings("checkstyle:Regexp")
    public String greeting() {
        return UNDERSCORE + "\n↜(╰ •ω• )╯ψ Hello there! I'm Lucifer.Lucifer.\nWhat is it you desire today?"
                + UNDERSCORE;
    }

    /**
     * Prints the list of desires Lucifer.Lucifer chatbot can receive as input should user provide invalid input.
     */
    public String helpCommands() {
        return UNDERSCORE + "Alright love, here are the list of desires I can grant:\n"
                + "\tlist\t :I will show you what your current desires are."
                + "\tdelete (number)\t :I will remove this desires from your current list."
                + "\ttodo (desire)\t :I will add this desire to your todo list."
                + "\tevent (desire) /at 31-12-2022 1800\t :I will add this desire to your list with the date & time."
                + "\tdeadline (desire) /by 31-12-2022 1800\t :I will add this desire to your list with its deadline."
                + "\tmark (number)\t :I can mark this desire in your list as done."
                + "\tunmark (number)\t :I can unmark this desire in your list as not done."
                + "\tbye\t: I will end our lovely little conversation for now"
                + "Now let's let back to what we are doing now shall we? ψ\uD83D\uDC7F\uD83D\uDD31⸸"
                + UNDERSCORE;
    }
}
