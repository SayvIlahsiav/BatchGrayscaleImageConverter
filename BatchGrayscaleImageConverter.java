import edu.duke.*;
import java.io.*;

/**
 * Write a description of BatchGrayscaleImageConverter here.
 * 
 * @author Vaishali Vys 
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
            gray.draw();
        }
    }
    
    public static void main(String[] args) {
        BatchGrayscaleImageConverter bgic = new BatchGrayscaleImageConverter();
        bgic.selectAndConvert();
    }
}
