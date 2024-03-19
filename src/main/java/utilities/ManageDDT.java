package utilities;

import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class ManageDDT extends CommonOps {

    // Method Name : getDataObject
    // Method Description: DataProvider method that retrieves test data from a CSV file.
    // Returns a 2D array containing test data.
    @DataProvider(name = "data-provider")
    public Object[][] getDataObject() {

        // Calls a method to retrieve data from a CSV file and return it as a 2D array
        return getDataFromCSV(getData("DDTFile"));
    }


    // Method Name : readCSV
    // Method Description: Reads the contents of a CSV file and returns them as a list of strings.
    // Method Parameters : String csvFile - The path to the CSV file.
    // Returns : A list of strings containing the lines from the CSV file.
    public static List<String> readCSV(String csvFile) {
        List<String> lines = null; // Initialize a list of strings to store the lines from the CSV file
        File file = new File(csvFile); // Create a File object representing the CSV file
        try {
            // Read all lines from the CSV file using UTF-8 encoding
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            // If an IOException occurs during file reading, print the stack trace
            e.printStackTrace();
        }
        return lines; // Return the list of strings (lines from the CSV file)
    }

    // Method Name : getDataFromCSV
    // Method Description: Reads data from a CSV file and formats it into a 2D array.
    // Method Parameters : String filePath - The path to the CSV file.
    // Returns : A 2D array containing the formatted data.
    public static Object[][] getDataFromCSV(String filePath) {
        List<String> csvData = readCSV(filePath); // Read data from CSV file and store in a list

        int numRows = csvData.size();
        int numCols = csvData.get(0).split(",").length;

        // Initialize a 2D array to hold the data dynamically
        Object[][] data = new Object[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            // Split each line of CSV data using the delimiter ",,," and assign to respective elements in the 2D array
            String[] rowData = csvData.get(i).split(",");
            for (int j = 0; j < numCols; j++) {
                data[i][j] = rowData[j];
            }

        }

        return data; // Return the formatted 2D array
    }
}


