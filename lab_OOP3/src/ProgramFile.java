import java.util.Date;

public class ProgramFile extends File {
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String name, Date createdTime, Date updatedTime, int lineCount, int classCount, int methodCount) {
        super(name, createdTime, updatedTime);
        this.lineCount = lineCount;
        this.classCount = classCount;
        this.methodCount = methodCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getClassCount() {
        return classCount;
    }

    public int getMethodCount() {
        return methodCount;
    }
}