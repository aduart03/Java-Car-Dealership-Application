import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.spi.LocaleServiceProvider;
import javax.lang.model.util.ElementScanner6;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.lang.Math;
import java.util.NoSuchElementException;

/**
 * This is the Junit test file which will test methods provided in the Junit test suite.
 */
public class AppTest {
    
    /**
     * We test the method "testCreateCarTypeRevenueHash" to ensure that the hashmap is being created correctly.
     */
    @Test
    public void testCreateCarTypeRevenueHash(){
        HashMap<String, Double> expectedHashMap = new HashMap<String, Double>();
        expectedHashMap.put("sedan", 0.00);
        expectedHashMap.put("suv", 0.00);
        expectedHashMap.put("hatchback", 0.00);
        expectedHashMap.put("pickup", 0.00);

        assertEquals(expectedHashMap, RunShop.createCarTypeRevenueHash());
    }

    /**
     * We test the method "testLogsData" to ensure that the logs are being written correctly and to test teh node
     * data structure to ensure its working properly.
     */
    @Test
    public void testLogsData() {
        // Prepare test data
        String activity =  "";

        // Invoke the logsData method
        RunShop.logsData(activity);

        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Format date and time
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = currentDate.format(formatterDate);
        String formattedTime = currentTime.format(formatterTime);

        // Expected log entry
        String expectedLogEntry = "Log Activity\n" + "-------------\n" + activity + " " + formattedDate + " " + formattedTime + " ";

        // Assert that logs contain the expected log entry
        assertEquals(expectedLogEntry, RunShop.logs.getData());
    }

    /**
     * We test "testCreateUserIndexHash" to ensure that the user index HashMap, which will be used and referred to throughout
     * the program, is being created correctly.
     */
    @Test
    public void testCreateUserIndexHash() {

        // Test HashMap
        HashMap<String, Integer> expectedHashMap = new HashMap<String, Integer>();

        RunShop.createUserIndexHash(RunShop.user_data);

        try {
            Scanner reader = new Scanner (RunShop.user_data);
            String line = reader.nextLine();
            String[] dataFields = line.split(",");
            for(int i = 0; i < dataFields.length; i++) {
                expectedHashMap.put(dataFields[i], i);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println ("File Not Found");
        }

        assertEquals(expectedHashMap, RunShop.createUserIndexHash(RunShop.user_data) );
    }

}
