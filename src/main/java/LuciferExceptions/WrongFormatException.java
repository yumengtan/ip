//Author: Yu Meng
//A0218371H

package LuciferExceptions;

public class WrongFormatException extends Exception {

    public WrongFormatException() {
        super("Oh Deary me! You have to tell me what you specifically desire.\n to check on known commands," +
                "type !help");
    }
}
