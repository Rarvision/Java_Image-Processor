package images;

/**
 * This class is for applying dithering effect to an image.
 * 
 * @author ZHANG Mao
 *
 */
public class Dithering {

  private double[][] ditheringMatrix;

  private int matrixLength;
  private int matrixHeight;
  private int radius;

  private Grayscale grayscaleProcessor;

  /**
   * The constructor of the dithering class.
   */
  public Dithering() {

    ditheringMatrix = new double[][] { { 0, 0, (7.00 / 16.00) },
        { (3.00 / 16.00), (5.00 / 16.00), (1.00 / 16.00) } };

    matrixLength = 3;
    matrixHeight = 2;
    radius = 1;

    grayscaleProcessor = new Grayscale();
  }

  /**
   * Check whether an RGB value is closer to 0 or 255.
   * 
   * @param colorValue the RGB value to be checked
   * @return true if the RGB value is closer to 0 than 255
   */
  private boolean isCloserToZero(int colorValue) {
    if (Math.abs(colorValue) < Math.abs(colorValue - 255)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Apply dithering effect to an image. The original image will be changed.
   * 
   * @param rgbData the RGB data of the image for filter applying
   */
  public void applyDithering(int[][][] rgbData) {

    if (rgbData == null) {
      throw new IllegalStateException("The picture should be loaded first");
    }

    grayscaleProcessor.applyTransform(rgbData);

    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        for (int channel = 0; channel < 3; channel++) {
          int error = isCloserToZero(rgbData[i][j][channel]) ? rgbData[i][j][channel]
              : rgbData[i][j][channel] - 255;

          rgbData[i][j][channel] = isCloserToZero(rgbData[i][j][channel]) ? 0 : 255;

          for (int x = 0; x < matrixHeight; x++) {
            for (int y = 0; y < matrixLength; y++) {

              if ((i + x >= 0) && (j - radius + y >= 0) && (i + x <= rgbData.length - 1)
                  && (j - radius + y <= rgbData[0].length - 1)) {
                rgbData[i + x][j - radius + y][channel] += (ditheringMatrix[x][y] * error);
              }
            }
          }
        }
      }
    }

    for (int i = 0; i < rgbData.length; i = i + 1) {
      for (int j = 0; j < rgbData[i].length; j = j + 1) {
        for (int channel = 0; channel < 3; channel++) {
          rgbData[i][j][channel] = Clamp.clamper(rgbData[i][j][channel]);
        }
      }
    }
  }
}
