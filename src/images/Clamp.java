package images;

/**
 * This class contains static method which can detect and prevent 
 * overflow of the RGB color.
 * @author ZHANG Mao
 *
 */
public class Clamp {
  
  /**
   * Check if the RGB value of a pixel overflows and clamp it if so.
   * @param colorValue the RGB value of a pixel to be clamped.
   *     It has to be a value passed from an 3D array of RGB data.
   * @return the updated RGB value
   */
  public static int clamper(int colorValue) {
    if (colorValue < 0) {
      colorValue = 0;
    } else if (colorValue > 255) {
      colorValue = 255;
    }
    return colorValue;
  }
}
