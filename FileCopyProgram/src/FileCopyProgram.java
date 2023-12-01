import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyProgram {
    public static void main(String[] args) {
        String sourceFilePath = args[0];
        String targetFilePath = args[1];

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);

        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist");
            return;
        }

        if (targetFile.exists()) {
            System.out.println("Target file already exists");
            return;
        }

        try (FileReader reader = new FileReader(sourceFile);
             FileWriter writer = new FileWriter(targetFile)) {

            char[] buffer = new char[1024];
            int charactersRead;

            while ((charactersRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charactersRead);
            }

            System.out.println("File copied successfully");
            System.out.println("Number of characters copied: " + sourceFile.length());

        } catch (IOException e) {
            System.out.println("An error occurred during file copy: " + e.getMessage());
        }
    }
}
