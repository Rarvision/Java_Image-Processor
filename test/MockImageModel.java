import images.ImageModel;
import java.awt.image.BufferedImage;

/**
 * Mock model of the image processor for testing.
 * @author ZHANG Mao
 *
 */
public class MockImageModel implements ImageModel {
  
  private StringBuilder log;
  
  /**
   * The constructor of the mock model.
   * @param log the log for recording activities in the mock model
   */
  public MockImageModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void loadImage(String fileName) throws IllegalArgumentException {
    this.log.append(fileName);
    this.log.append(System.lineSeparator());
    if ("fileUnexists".equals(fileName)) {
      throw new IllegalArgumentException();
    }
    

  }

  @Override
  public void saveImage(String fileName) throws IllegalArgumentException {
    this.log.append(fileName);
    this.log.append(System.lineSeparator());
    if (! fileName.matches("^.*\\.(jpg|JPG|gif|GIF|png|PNG|jpeg|JPEG)$")) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void applyBlur() {
    this.log.append("Blurring...");
    this.log.append(System.lineSeparator());
  }

  @Override
  public void applySharpen() {
    this.log.append("Sharpening...");
    this.log.append(System.lineSeparator());

  }

  @Override
  public void applyGrayscale() {
    this.log.append("Grayscale applying...");
    this.log.append(System.lineSeparator());

  }
  
  @Override
  public void applyEdgeDetection() {
    this.log.append("Edge Detection applying...");
    this.log.append(System.lineSeparator());
    
  }
  
  @Override
  public void applyHistogramEqualization() {
    this.log.append("Histogram equalization applying...");
    this.log.append(System.lineSeparator());
    
  }

  @Override
  public void applySepia() {
    this.log.append("Sepia applyting...");
    this.log.append(System.lineSeparator());

  }

  @Override
  public void applyDither() {
    this.log.append("Dithering applying...");
    this.log.append(System.lineSeparator());

  }

  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    this.log.append("Mosaic with ");
    this.log.append(Integer.toString(seeds));
    this.log.append(" seeds applying...");
    this.log.append(System.lineSeparator());
  }

  @Override
  public BufferedImage convertImage() throws IllegalArgumentException {
    this.log.append("image converted");
    this.log.append(System.lineSeparator());
    return null;
  }

  @Override
  public void resetRgbData() {
    this.log.append("reset completed");
    this.log.append(System.lineSeparator());
  }

}
