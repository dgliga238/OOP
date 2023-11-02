import java.nio.file.Path;

import java.util.Date;

public class File {
    private String name;
    private Date createdTime;
    private Date updatedTime;

    public File(String name, Date createdTime, Date updatedTime) {
        this.name = name;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }
}

