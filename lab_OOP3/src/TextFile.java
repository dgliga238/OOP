import java.util.Date;

public class TextFile extends File {
    private int lineCount;
    private int wordCount;
    private int charCount;

    public TextFile(String name, Date createdTime, Date updatedTime, int lineCount, int wordCount, int charCount) {
        super(name, createdTime, updatedTime);
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.charCount = charCount;
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
