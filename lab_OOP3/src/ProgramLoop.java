import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramLoop {
    private String folderPath;
    private List<File> files;

    public ProgramLoop(String folderPath) {
        this.folderPath = folderPath;
        this.files = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void commit() {
        // Update the snapshot time to the current time
        Date lastCommitTime = new Date();
        System.out.println("Snapshot time updated to: " + lastCommitTime);
        System.out.println("Committing changes...");
    }

    public void info(String fileName) {
        File foundFile = null;
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                foundFile = file;
                break;
            }
        }
        if (foundFile != null) {
            System.out.println("File Name: " + foundFile.getName());
            System.out.println("Created Time: " + foundFile.getCreatedTime());
            System.out.println("Updated Time: " + foundFile.getUpdatedTime());

            if (foundFile instanceof TextFile) {
                TextFile textFile = (TextFile) foundFile;
                System.out.println("Line Count: " + textFile.getLineCount());
                System.out.println("Word Count: " + textFile.getWordCount());
                System.out.println("Char Count: " + textFile.getCharCount());
            } else if (foundFile instanceof ImageFile) {
                ImageFile imageFile = (ImageFile) foundFile;
                System.out.println("Image Size: " + imageFile.getImageSize());
            } else if (foundFile instanceof ProgramFile) {
                ProgramFile programFile = (ProgramFile) foundFile;
                System.out.println("Line Count: " + programFile.getLineCount());
                System.out.println("Class Count: " + programFile.getClassCount());
                System.out.println("Method Count: " + programFile.getMethodCount());
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    public void status() {
        System.out.println("Folder Path: " + folderPath);
        System.out.println("Number of Files: " + files.size());
        // Print information about each file in the files list
        for (File file : files) {
            System.out.println("File Name: " + file.getName());
            System.out.println("Created Time: " + file.getCreatedTime());
            System.out.println("Updated Time: " + file.getUpdatedTime());

            if (file instanceof TextFile) {
                TextFile textFile = (TextFile) file;
                System.out.println("Type: Text File");
                System.out.println("Line Count: " + textFile.getLineCount());
                System.out.println("Word Count: " + textFile.getWordCount());
                System.out.println("Char Count: " + textFile.getCharCount());
            } else if (file instanceof ImageFile) {
                ImageFile imageFile = (ImageFile) file;
                System.out.println("Type: Image File");
                System.out.println("Image Size: " + imageFile.getImageSize());
            } else if (file instanceof ProgramFile) {
                ProgramFile programFile = (ProgramFile) file;
                System.out.println("Type: Program File");
                System.out.println("Line Count: " + programFile.getLineCount());
                System.out.println("Class Count: " + programFile.getClassCount());
                System.out.println("Method Count: " + programFile.getMethodCount());
            }

            System.out.println("---------------");
        }
    }
}
