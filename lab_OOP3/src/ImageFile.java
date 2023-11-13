import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.awt.image.BufferedImage;



public class ImageFile extends File {
    private String imageSize;


    public ImageFile(String name, Date createdTime, Date updatedTime, String imageSize) {
        super(name, createdTime, updatedTime);
        this.imageSize = imageSize;
    }

    public String getImageSize() {
        return imageSize;
    }
    @Override
    void displayFileInfo() {
        System.out.println("Image Size: " + getImageSize());
    }
    private String getImageSize(Path filePath) {
        try {
            BufferedImage image = ImageIO.read(filePath.toFile());
            int width = image.getWidth();
            int height = image.getHeight();
            return width + "x" + height;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "N/A";
    }

}


