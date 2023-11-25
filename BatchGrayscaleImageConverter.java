import edu.duke.*;
import java.io.*;

/**
 * This class provides functionality for 
 * converting color images to grayscale. 
 * It includes methods for processing individual images as well as 
 * batch processing multiple images from a selected directory, 
 * saving the grayscale versions with modified file names in a specified path.
 * 
 * @author Vaishali Vyas 
 * @version 2023, November 25
 */

public class BatchGrayscaleImageConverter {
    
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            String fileName = inImage.getFileName();
            String newName = "grayed-" + fileName;
            String directoryPath = "D:\\Study\\Coursera\\Java - Duke University\\Projects\\BatchGrayscaleImageConverter\\images\\";
            String fullPath = directoryPath + newName;
            gray.setFileName(fullPath);
            gray.draw();
            gray.save();
        }
    }
    
    public static void main(String[] args) {
        BatchGrayscaleImageConverter bgic = new BatchGrayscaleImageConverter();
        bgic.selectAndConvert();
    }
}
