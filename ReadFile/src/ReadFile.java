import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String csvFilePath = "path/to/your/file.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] countryData = line.split(",");

                int id = Integer.parseInt(countryData[0]);
                String code = countryData[1];
                String name = countryData[2];

                System.out.println("ID: " + id);
                System.out.println("Code: " + code);
                System.out.println("Name: " + name);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file: " + e.getMessage());
        }
    }
}
