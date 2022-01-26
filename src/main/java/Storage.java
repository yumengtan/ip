//Author: Yu Meng
//A0218371H

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveFileList(ArrayList<Task> list) throws IOException {
        System.out.println(".");
        FileWriter fw = new FileWriter(filePath + "/Lucifer.txt");
        String tasks = "";
        for(int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                tasks += list.get(i);
            } else {
                tasks += list.get(i) + "\n";
            }
        }
        fw.write(tasks);
        System.out.println("Your wish is my command. Your desires have been updated my love");
        fw.close();
    }

    public void loadList(ArrayList<Task> list) throws IOException {
        String directory = filePath + "/Lucifer.txt";
        File file = new File(directory);
        try {
            Files.createFile(Paths.get(directory));
        } catch (FileAlreadyExistsException e) {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String curr = sc.nextLine();
                Character eventType = curr.charAt(0);
                if (eventType.equals("E")) {
                    int i = curr.lastIndexOf("]") + 1;
                    String theTask = curr.substring(i);
                    String[] splitter = curr.split("at");
                    list.add(new Event(splitter[0],splitter[1]));
                } else if (eventType.equals("D")) {
                    int i = curr.lastIndexOf("]") + 1;
                    String theTask = curr.substring(i);
                    String[] splitter = theTask.split("by");
                    list.add(new Deadline(splitter[0],splitter[1]));
                } else if (eventType.equals("T")) {
                    int i = curr.lastIndexOf("]") + 1;
                    String theTask = curr.substring(i);
                    list.add(new Todo(theTask));
                } else {
                    int i = curr.lastIndexOf("]") + 1;
                    String theTask = curr.substring(i);
                    list.add(new Task(theTask));
                }
            }
        }
    }
}
