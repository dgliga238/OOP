import java.util.Date;

public class ImageFile extends File {
    private String imageSize;

    public ImageFile(String name, Date createdTime, Date updatedTime, String imageSize) {
        super(name, createdTime, updatedTime);
        this.imageSize = imageSize;
    }

    public String getImageSize() {
        return imageSize;
    }
}

