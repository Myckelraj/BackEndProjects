import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageCropper {

    public static void cropImage(String sourcePath, String outputPath, int x, int y, int width, int height) throws IOException {
        // Load the original image
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            throw new IOException("Source file does not exist: " + sourcePath);
        }

        BufferedImage originalImage = ImageIO.read(sourceFile);
        if (originalImage == null) {
            throw new IOException("Failed to load image from: " + sourcePath);
        }

        // Validate cropping dimensions
        int imgWidth = originalImage.getWidth();
        int imgHeight = originalImage.getHeight();

        if (x < 0 || y < 0 || x + width > imgWidth || y + height > imgHeight) {
            throw new IllegalArgumentException("Crop rectangle is out of bounds. Image dimensions: "
                    + imgWidth + "x" + imgHeight + ", crop rectangle: "
                    + x + "," + y + "," + width + "," + height);
        }

        // Define the cropping rectangle
        Rectangle cropRect = new Rectangle(x, y, width, height);

        // Crop the image
        BufferedImage croppedImage = originalImage.getSubimage(cropRect.x, cropRect.y, cropRect.width, cropRect.height);

        // Save the cropped image
        File outputFile = new File(outputPath);
        if (outputFile.exists()) {
            System.out.println("Warning: Output file already exists and will be overwritten.");
        }

        ImageIO.write(croppedImage, "jpg", outputFile);
        System.out.println("Cropped image saved to: " + outputPath);
    }

    public static void main(String[] args) {
        String sourcePath = "src/main/java/example.jpg";
        String outputPath = "src/main/java/cropped_example.jpg";
        int x = 50; // X-coordinate of the top-left corner of the crop area
        int y = 50; // Y-coordinate of the top-left corner of the crop area
        int width = 300; // Width of the crop area
        int height = 500; // Height of the crop area

        try {
            cropImage(sourcePath, outputPath, x, y, width, height);
            System.out.println("Image cropped successfully.");
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException: " + e.getMessage());
        }
    }
}
