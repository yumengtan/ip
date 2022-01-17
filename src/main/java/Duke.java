//Author: Yu Meng
//A0218371H

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String underscore = "____________________________________________________________";
        //greetings
        System.out.println(underscore);

        System.out.println("(◍´◕ᴥ◕) Hello there! I'm Lucifer.\nWhat is it you desire today?");
        System.out.println(underscore);

        //get input
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String repeat = sc.next();
            System.out.println(underscore);
            System.out.println(repeat);
            System.out.println(underscore);

            if (repeat.equals("bye")) {
                //farewell message
                System.out.println(underscore);
                System.out.println("ʕ•́ᴥ•̀ʔっ Oh no! I hate to see you go:(");
                System.out.println("Have I granted your desires?\nPlease come back again!");
                System.out.println(underscore);
                break;
            }
        }

        sc.close();

    }
}
