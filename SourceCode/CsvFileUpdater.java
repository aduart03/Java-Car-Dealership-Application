import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which contains the method to update a csv file
 */
public class CsvFileUpdater {

    /**
     * Default constructor.
     */
    CsvFileUpdater() {

    }

    /**
     * Updates a specific cell in a csv file.
     * @param fileName The pathname of the file.
     * @param row The row of the cell to be updated.
     * @param col The column of the cell to be updated.
     * @param replace The new string that will replace the current string in the specific cell.
     */
    public void updateCSV(String fileName, int row, int col, String replace) {
        String updatedFileName = fileName;
        File updatedFile = new File(updatedFileName);

        try {
            File inputFile = new File(fileName);

            // Read existing file and add all the contents into an array list
            Scanner fileReader = new Scanner(inputFile);
            ArrayList<String[]> csvBody = new ArrayList<String[]>();
            String line = "";
            while(fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                String[] csvLineArray = line.split(",");
                csvBody.add(csvLineArray);
            }
            // replacing cell at specified row and column with given string
            csvBody.get(row)[col] = replace;
            fileReader.close();
            
            // Write to CSV file which is open
            FileWriter writer = new FileWriter(inputFile);
            String newLine = "";
            for(int i = 0; i < csvBody.size(); i++) {
                newLine = String.join(",", csvBody.get(i));
                writer.write(newLine + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error" + e);
        }
    }

}