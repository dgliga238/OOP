import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Info {
    private final String folderPath;
    private final String filename;
    private final SimpleDateFormat dateFormat;

    public Info(String folderPath, String filename, SimpleDateFormat dateFormat) {
        this.folderPath = folderPath;
        this.filename = filename;
        this.dateFormat = dateFormat;
    }

    public void performInfo() {
        Path filePath = Paths.get(folderPath, filename);

        if (Files.exists(filePath)) {
            try {
                BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
                Date createdTime = new Date(attributes.creationTime().toMillis());
                Date updatedTime = new Date(attributes.lastModifiedTime().toMillis());

                System.out.println("File Name: " + filename);
                System.out.println("Created: " + dateFormat.format(createdTime));
                System.out.println("Last Modified: " + dateFormat.format(updatedTime));

                // Display specific information based on the file type
                if (Files.isRegularFile(filePath)) {
                    if (filename.toLowerCase().endsWith(".txt")) {
                        TextFile textFile = new TextFile(filename, createdTime, updatedTime, 0, 0, 0);
                        textFile.countTextFileInfo(filePath);
                        textFile.displayFileInfo();
                    } else if (filename.toLowerCase().endsWith(".png") || filename.toLowerCase().endsWith(".jpg")) {
                        ImageFile imageFile = new ImageFile(filename, createdTime, updatedTime, "N/A");
                        imageFile.displayFileInfo();
                    } else if (filename.toLowerCase().endsWith(".java")) {
                        ProgramFile programFile = new ProgramFile(filename, createdTime, updatedTime, 0, 0, 0);
                        programFile.countMethods(Files.readAllLines(filePath));
                        programFile.displayFileInfo();
                    } else if (filename.toLowerCase().endsWith(".py")) {
                        ProgramFile pythonFile = new ProgramFile(filename, createdTime, updatedTime, 0, 0, 0);
                        pythonFile.countMethods(Files.readAllLines(filePath));
                        pythonFile.displayFileInfo();
                    } else {
                        // Handle other file types
                        System.out.println("Unsupported file type");
                    }
                } else {
                    System.out.println("Not a regular file");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found: " + filename);
        }
    }
}
