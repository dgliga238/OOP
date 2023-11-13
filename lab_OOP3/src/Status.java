
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class Status {
    private final String folderPath;
    private final long lastSnapshotTime;
    private final SimpleDateFormat dateFormat;

    public Status(String folderPath, long lastSnapshotTime, SimpleDateFormat dateFormat) {
        this.folderPath = folderPath;
        this.lastSnapshotTime = lastSnapshotTime;
        this.dateFormat = dateFormat;
    }

    public void performStatus() {
        System.out.println("Last Snapshot Time: " + dateFormat.format(new Date(lastSnapshotTime)));

        boolean filesChanged = false;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    long fileLastModifiedTime = Files.getLastModifiedTime(file).toMillis();
                    if (fileLastModifiedTime > lastSnapshotTime) {
                        String relativePath = Paths.get(folderPath).relativize(file).toString();
                        System.out.println(relativePath + " - Changed ");
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
}
