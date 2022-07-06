package images;

/**
 * This abstract class implementing the function of transformer (sepia-toned and
 * gray scale).
 * 
 * @author ZHANG Mao
 *
 */
public abstract class AbstractTransformer {

  double[][] matrix;

  /**
   * The constructor of the AbstractTransformer class.
   * 
   * @param matrix the argument of transformation algorithm
   */
  public AbstractTransformer(double[][] matrix) {
    this.matrix = matrix;
  }

  /**
   * Convert an image into a image with different color tone. The original image
   * will be changed after conversion.
   * 
   * @param rgbData the RGB data of the image for conversion
   */
  public void applyTransform(int[][][] rgbData) {

    if (rgbData == null) {
      throw new IllegalStateException("The picture should be loaded first");
    }

    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[i].length; j++) {

        int redChannel = (int) (matrix[0][0] * rgbData[i][j][0] + matrix[0][1] * rgbData[i][j][1]
            + matrix[0][2] * rgbData[i][j][2]);
        int greenChannel = (int) (matrix[1][0] * rgbData[i][j][0] + matrix[1][1] * rgbData[i][j][1]
            + matrix[1][2] * rgbData[i][j][2]);
        int blueChannel = (int) (matrix[2][0] * rgbData[i][j][0] + matrix[2][1] * rgbData[i][j][1]
            + matrix[2][2] * rgbData[i][j][2]);

        rgbData[i][j][0] = redChannel;
        rgbData[i][j][1] = greenChannel;
        rgbData[i][j][2] = blueChannel;

        for (int channel = 0; channel < 3; channel++) {
          rgbData[i][j][channel] = Clamp.clamper(rgbData[i][j][channel]);
        }

      }
    }
  }

}
