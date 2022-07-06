package images;

import java.awt.image.BufferedImage;

/**
 * This class is the interface of the model of the image processor.
 * @author ZHANG Mao
 *
 */
public interface ImageModel {

  /**
   * Load an image into the image model.
   * 
   * @param filename the name of the file containing the image.
   * @throws IllegalArgumentException if the filename is invalid or if something
   *                                  goes wrong loading the image
   */
  public void loadImage(String filename) throws IllegalArgumentException;

  /**
   * Save the data in the image model to a file.
   * 
   * @param filename the name of the file to save to
   * @throws IllegalArgumentException if the filename is invalid or if something
   *                                  goes wrong saving the file
   */
  public void saveImage(String filename) throws IllegalArgumentException;

  /**
   * Apply the blur filter to the data in the image model.
   */
  public void applyBlur();

  /**
   * Apply the sharpen filter to the data in the image model.
   */
  public void applySharpen();

  /**
   * Apply the grayscale color transformation to the data in the image model.
   */
  public void applyGrayscale();

  /**
   * Apply the sepia color transformation to the data in the image model.
   */
  public void applySepia();

  /**
   * Apply the dithering effect to the data in the image model.
   */
  public void applyDither();

  /**
   * Apply the mosaic effect to the data in the image model.
   * 
   * @param seeds the number of seeds to use in the mosaic
   * @throws IllegalArgumentException if the number of seeds is not positive
   */
  public void applyMosaic(int seeds) throws IllegalArgumentException;
  
  /**
   * Return the stored image with BufferedImage form.
   * 
   * @return image with BufferedImage form
   * @throws IllegalArgumentException if there is no image stored
   */
  public BufferedImage convertImage() throws IllegalArgumentException;
  
  /**
   * Reset the RGB data of the image to origin.
   */
  public void resetRgbData();
  
  /**
   * Apply enhancing gray-scale effect to the data in the image model.

   */
  public void applyHistogramEqualization();
  
  /**
   * Apply gray-scale effect with edge detection to the data in the image model.

   */
  public void applyEdgeDetection();

}
