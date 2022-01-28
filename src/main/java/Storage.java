//Author: Yu Meng
//A0218371H

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveFileList(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath + "/Lucifer.txt");
        String tasks = "";
        for(int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                tasks += list.get(i).saveFormat();
            } else {
                tasks += list.get(i).saveFormat() + "\n";
            }
        }
        fw.write(tasks);
        System.out.println("Your desires have been etched into my memories my love.");
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
                if (eventType.equals('E')) {
                    Character markOrNot = curr.charAt(3);
                    String theTask = curr.substring(6);
                    String[] getTask = theTask.split("\\|");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime old = LocalDateTime.parse(getTask[1].trim(), formatter);
                    Event currTask = new Event(getTask[0].trim(), old);
                    if (markOrNot.equals("1")) {
                        currTask.markDone();
                    } else {
                        currTask.unmarkDone();
                    }
                    list.add(currTask);

                } else if (eventType.equals('D')) {
                    Character markOrNot = curr.charAt(3);
                    String theTask = curr.substring(6);
                    String[] getTask = theTask.split("\\|");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime old = LocalDateTime.parse(getTask[1].trim(), formatter);
                    Deadline currTask = new Deadline(getTask[0].trim(),old);
                    if (markOrNot.equals("1")) {
                        currTask.markDone();
                    } else {
                        currTask.unmarkDone();
                    }
                    list.add(currTask);

                } else if (eventType.equals('T')) {
                    Character markOrNot = curr.charAt(3);
                    int i = curr.lastIndexOf("|") + 2;
                    String theTask = curr.substring(i);
                    Todo currTask = new Todo(theTask);
                    if (markOrNot.equals("1")) {
                        currTask.markDone();
                    } else {
                        currTask.unmarkDone();
                    }
                    list.add(currTask);
                }
            }
        }
    }
}
