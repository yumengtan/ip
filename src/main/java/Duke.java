//Author: Yu Meng
//A0218371H

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> li = new ArrayList<>();
    private static final String underscore = "____________________________________________________________";

    public static void main(String[] args) {
        //greetings
        greeting();

        //get input
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String repeat = sc.nextLine();
            addList(repeat);

            if (repeat.equals("list")) {
                giveList();
            }
            if (repeat.equals("bye")) {
                //farewell message
                farewell();
                break;
            }
        }

        sc.close();

    }

    
    public static void addList(String adder) {
        li.add(adder);
        System.out.println(underscore);
        System.out.println("added " + adder);
        System.out.println(underscore);
    }

    public static void giveList() {
        for (int i = 1; i < li.size(); i++) {
            System.out.println(" " + i + ". " + li.get(i));
        }
        System.out.println(underscore);
    }

    public static void farewell() {
        System.out.println(underscore);
        System.out.println("ʕ•́ᴥ•̀ʔっ Oh no! I hate to see you go:(");
        System.out.println("Have I granted your desires?\nPlease come back again!");
        System.out.println(underscore);
    }

    public static void greeting() {
        System.out.println(underscore);
        System.out.println("(◍´◕ᴥ◕) Hello there! I'm Lucifer.\nWhat is it you desire today?");
        System.out.println(underscore);
    }
}
