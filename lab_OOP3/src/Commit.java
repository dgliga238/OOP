import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Commit {
    private final String folderPath;
    private final long lastSnapshotTime;
    private final SimpleDateFormat dateFormat;

    public Commit(String folderPath, long lastSnapshotTime, SimpleDateFormat dateFormat) {
        this.folderPath = folderPath;
        this.lastSnapshotTime = lastSnapshotTime;
        this.dateFormat = dateFormat;
    }

    public void performCommit() {
        long snapshotTime = System.currentTimeMillis();
        saveLastSnapshotTime(snapshotTime);
        System.out.println("Snapshot created at: " + dateFormat.format(new Date(snapshotTime)));
    }

    private void saveLastSnapshotTime(long lastSnapshotTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("text.txt"))) {
            writer.write(Long.toString(lastSnapshotTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getLastSnapshotTime() {
        return lastSnapshotTime;
    }
}

