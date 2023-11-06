import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

public class TextFile extends File {
    private int lineCount;
    private int wordCount;
    private int charCount;

    public TextFile(String name, Date createdTime, Date updatedTime, int lineCount, int wordCount, int charCount) {
        super(name);
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.charCount = charCount;
    }

    public void countTextFileInfo(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            lineCount = lines.size();
            charCount = lines.stream().mapToInt(String::length).sum();
            wordCount = lines.stream()
                    .mapToInt(line -> line.split("\\s+").length)
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharCount() {
        return charCount;
    }
}
