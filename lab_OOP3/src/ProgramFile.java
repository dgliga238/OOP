class ProgramFile extends File {
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String name, String extension, long createdTime, long updatedTime, String content) {
        super(name, extension, createdTime, updatedTime);

        String[] lines = content.split("\n");
        this.lineCount = lines.length;
        this.classCount = 0;
        this.methodCount = 0;

        if (extension.equals(".py")) {
            for (String line : lines) {
                if (line.trim().startsWith("class ")) {
                    this.classCount++;
                } else if (line.trim().startsWith("def ")) {
                    this.methodCount++;
                }
            }
        } else if (extension.equals(".java")) {
            for (String line : lines) {
                if (line.trim().startsWith("class ") || line.trim().startsWith("interface ")) {
                    this.classCount++;
                } else if (line.trim().startsWith("public ") || line.trim().startsWith("private ")) {
                    this.methodCount++;
                }
            }
        }
    }
}