import java.nio.file.Path;

import java.util.Date;

public class File {
    protected String name;
    protected Date createdTime;
    protected Date updatedTime;

    public File(String name, Date createdTime, Date updatedTime) {
        this.name = name;
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }


    public String getName() {
        return name;
    }

    void displayFileInfo() {
        System.out.println("File Name: " + getName());
        System.out.println("Created: " + ProgramLoop.dateFormat.format(createdTime));
        System.out.println("Last Modified: " + ProgramLoop.dateFormat.format(updatedTime));
    }
}

