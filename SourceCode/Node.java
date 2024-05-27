import java.io.IOException;
import java.io.*;

/**
 * This file represents the linked list that contains the
 * nodes and these nodes represent the log data of the user.
 * 
 */
class Node {

    /**
     * The elements of the linked list will be assigned to data.
     */
    private String data;

    /**
     * Reference to the next node in the login.
     */
    Node next;

    /**
     * Node default constructor.
     */
    Node() {
    }

    /**
     * Node that will update data through parameters passed.
     * @param i Represents the strings being stored into data.
     */
    Node (String i) {
        data = i;
    }

    /**
     * Sets the data to string passed in.
     * @param s String that will be assigned to data.
     */
    public void setData(String s) {
        data = s;
    }

    /**
     * Gets the current value of data.
     * @return value of data.
     */
    public String getData() {
        return data;
    }

    /**
     * Adds the date, time to the node at the time of the activity.
     * @param date is the date of when the activity took place using java localdate.
     * @param time is the time of when the activity took place using java localtime.
     * @param activity is the activity that will be logged using this method.
     */
    public void addLog( String date, String time, String activity) {
        //  Log string that will be displayed.
        String logString = "Log Activity\n" + "-------------\n" + activity + " " + date + " " + time + " ";
        
        //  Checks if there is any data.
        if (this.data == null) {
            this.data = logString;
        } else {
            //  Sets node to current node.
            Node current = this;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(logString);
        }

        //  Writes logs to a text file called 'logs.txt'.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt", true))) {
            writer.write(logString);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    /**
     * Displays all log entries.
     */
    public void displayLogs() {
        //  Reads the 'logs.txt' file and displays the data to the user/admin.
        try (BufferedReader reader = new BufferedReader(new FileReader("logs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading logs file: " + e.getMessage());
        }

    }
}