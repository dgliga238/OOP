import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    void displayFileInfo() {
        System.out.println("Method Count: " + getMethodCount());
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void countMethods(List<String> lines) {
        int pythonMethodCount = countPythonMethods(lines);
        int javaMethodCount = countJavaMethods(lines);

        methodCount = pythonMethodCount + javaMethodCount;
    }

    private int countPythonMethods(List<String> lines) {
        int pythonMethodCount = 0;

        Pattern pythonMethodPattern = Pattern.compile(".*\\bdef\\s+(\\w+)\\(.*\\):");

        for (String line : lines) {
            Matcher matcher = pythonMethodPattern.matcher(line);
            if (matcher.matches()) {
                pythonMethodCount++;
            }
        }

        return pythonMethodCount;
    }

    private int countJavaMethods(List<String> lines) {
        int javaMethodCount = 0;

        Pattern javaMethodPattern = Pattern.compile(".*(\\b(public|private|protected)\\b)?.*\\bvoid\\s+(\\w+)\\s*\\(.*");

        for (String line : lines) {
            Matcher matcher = javaMethodPattern.matcher(line);
            if (matcher.matches()) {
                javaMethodCount++;
            }
        }

        return javaMethodCount;
    }
}
