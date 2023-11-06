import java.nio.file.Path;

import java.util.Date;

public class File {
    private String name;
    private Date createdTime;
    private Date updatedTime;

    public File(String name) {
        this.name = name;
        this.createdTime = new Date();
        this.updatedTime = new Date();
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


    public boolean exists() {
        return false;
    }
}

