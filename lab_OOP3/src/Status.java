import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Status {

    private final String folderPath;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private long lastSnapshotTime;

    public Status(String folderPath) {
        this.folderPath = folderPath;
        this.lastSnapshotTime = loadLastSnapshotTime("text.txt");
    }

    public void check() {
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

    private long loadLastSnapshotTime(String fileName) {
        Path path = FileSystems.getDefault().getPath(fileName);

        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                if (!lines.isEmpty()) {
                    return Long.parseLong(lines.get(0).trim());
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
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