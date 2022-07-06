package images;

import java.awt.image.BufferedImage;

/**
 * This class is the implementation of the model of the image processor.
 * 
 * @author ZHANG Mao
 *
 */
public class ConcreteImageModel implements ImageModel {

  private int[][][] rgbData;
  private int[][][] duplicatedRgbData;

  @Override
  public void loadImage(String fileName) throws IllegalArgumentException {

    this.rgbData = ImageUtilities.readImage(fileName);
    this.duplicatedRgbData = ImageUtilities.readImage(fileName);

  }

  @Override
  public void saveImage(String fileName) throws IllegalArgumentException {

    if (!fileName.matches("^.*\\.(jpg|JPG|gif|GIF|png|PNG|jpeg|JPEG)$")) {
      throw new IllegalArgumentException();
    }

    ImageUtilities.writeImage(this.rgbData, fileName);

  }

  @Override
  public void applyBlur() {
    Blur blurProcessor = new Blur();
    blurProcessor.applyFilter(this.rgbData);
  }

  @Override
  public void applySharpen() {
    Sharpen sharpenProcessor = new Sharpen();
    sharpenProcessor.applyFilter(this.rgbData);
  }

  @Override
  public void applyGrayscale() {
    Grayscale grayscaleProcessor = new Grayscale();
    grayscaleProcessor.applyTransform(this.rgbData);

  }

  @Override
  public void applySepia() {
    Sepia sepiaProcessor = new Sepia();
    sepiaProcessor.applyTransform(this.rgbData);

  }

  @Override
  public void applyDither() {
    Dithering ditheringProcessor = new Dithering();
    ditheringProcessor.applyDithering(this.rgbData);

  }

  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    Mosaic mosaicProcessor = new Mosaic(seeds);
    mosaicProcessor.applyMosaic(this.rgbData);

  }

  @Override
  public BufferedImage convertImage() throws IllegalArgumentException {
    return ImageUtilities.convertImage(this.rgbData);
  }

  @Override
  public void resetRgbData() {
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        for (int k = 0; k < rgbData[0][0].length; k++) {
          this.rgbData[i][j][k] = this.duplicatedRgbData[i][j][k];
        }
      }
    }
  }

  @Override
  public void applyHistogramEqualization() {
    HistogramEqualization heProcessor = new HistogramEqualization();
    heProcessor.applyHistogramEqualization(this.rgbData);

  }

  @Override
  public void applyEdgeDetection() {
    EdgeDetection edProcessor = new EdgeDetection();
    edProcessor.applyEdgeDetection(this.rgbData);

  }

}
