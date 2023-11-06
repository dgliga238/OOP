import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ProgramLoop {
    private final String folderPath;
    private long lastSnapshotTime= loadLastSnapshotTime();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ProgramLoop(String folderPath) {
        this.folderPath = folderPath;
        this.lastSnapshotTime = loadLastSnapshotTime();
    }

    public void commit() {
        long snapshotTime = System.currentTimeMillis();
        saveLastSnapshotTime(snapshotTime);
        lastSnapshotTime = snapshotTime; // Update lastSnapshotTime when a new snapshot is created
        System.out.println("Snapshot created at: " + dateFormat.format(new Date(snapshotTime)));
    }


    public void info(String filename) {
        Path filePath = Paths.get(folderPath, filename);
        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            try {
                BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
                System.out.println("File Name: " + filePath.getFileName());
                System.out.println("Created: " + dateFormat.format(new Date(attributes.creationTime().toMillis())));
                System.out.println("Last Modified: " + dateFormat.format(new Date(attributes.lastModifiedTime().toMillis())));

                if (filename.endsWith(".txt")) {
                    TextFile textFile = new TextFile(filename, new Date(attributes.creationTime().toMillis()),
                            new Date(attributes.lastModifiedTime().toMillis()), 0, 0, 0);
                    textFile.countTextFileInfo(filePath);
                    System.out.println("Line Count: " + textFile.getLineCount());
                    System.out.println("Character Count: " + textFile.getCharCount());
                    System.out.println("Word Count: " + textFile.getWordCount());
                } else if (filename.endsWith(".png") || filename.endsWith(".jpg")) {
                    String imageSize = getImageSize(filePath);
                    ImageFile imageFile = new ImageFile(filename, new Date(attributes.creationTime().toMillis()),
                            new Date(attributes.lastModifiedTime().toMillis()), imageSize);
                    System.out.println("Image Size: " + imageFile.getImageSize());
                } else if (filename.endsWith(".java") || filename.endsWith(".py")) {
                    List<String> lines = Files.readAllLines(filePath);
                    ProgramFile programFile = new ProgramFile(filename, new Date(attributes.creationTime().toMillis()),
                            new Date(attributes.lastModifiedTime().toMillis()), lines.size(), 0, countMethods(lines));
                    System.out.println("Method Count: " + programFile.getMethodCount());
                } else {
                    System.out.println("File type not supported for additional information.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found: " + filename);
        }
    }

    public void status() {
        System.out.println("Last Snapshot Time: " + dateFormat.format(new Date(lastSnapshotTime)));

        boolean filesChanged = false;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    long fileLastModifiedTime = Files.getLastModifiedTime(file).toMillis();
                    if (fileLastModifiedTime > lastSnapshotTime) {
                        String relativePath = Paths.get(folderPath).relativize(file).toString();
                        System.out.println(relativePath + " - Changed (on " +
                                dateFormat.format(new Date(fileLastModifiedTime)) + ")");
                        filesChanged = true;
                    } else {
                        String relativePath = Paths.get(folderPath).relativize(file).toString();
                        System.out.println(relativePath + " - No Change");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!filesChanged) {
            System.out.println("No files were changed since the last snapshot.");
        }
    }


    private void countTextFileInfo(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            int lineCount = lines.size();
            int charCount = lines.stream().mapToInt(String::length).sum();
            int wordCount = lines.stream().mapToInt(line -> line.split("\\s+").length).sum();

            System.out.println("Line Count: " + lineCount);
            System.out.println("Character Count: " + charCount);
            System.out.println("Word Count: " + wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getImageSize(Path filePath) {
        try {
            BufferedImage image = ImageIO.read(filePath.toFile());
            int width = image.getWidth();
            int height = image.getHeight();
            return width + "x" + height;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    private int countMethods(List<String> lines) {
        int methodCount = 0;
        Pattern methodPattern = Pattern.compile(".*(\\b(public|private|protected)\\b)?.*\\bvoid\\s+(\\w+)\\s*\\(.*");

        for (String line : lines) {
            Matcher matcher = methodPattern.matcher(line);
            if (matcher.matches()) {
                methodCount++;
            }
        }

        return methodCount;
    }

    private long loadLastSnapshotTime() {
        File file = new File("text.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(file)))) {
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    return Long.parseLong(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Return the current time if no valid snapshot time is found in the file
        return System.currentTimeMillis();
    }

    private void saveLastSnapshotTime(long lastSnapshotTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("text.txt"))) {
            writer.write(Long.toString(lastSnapshotTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

