package images;

/**
 * This class implementing the function of applying enhanced gray-scale effect
 * to an image using histogram equalization.
 * 
 * @author ZHANG Mao
 *
 */
public class HistogramEqualization {
  
  private int[] cdf;
  private Grayscale grayscaleProcessor;
  
  /**
   * The constructor of the HistogramEqualization class.
   */
  public HistogramEqualization() {
    this.cdf = new int[255];
    this.grayscaleProcessor = new Grayscale();
  }
  
  /**
   * Apply enhanced gray-scale effect to an image using histogram equalization.
   * @param rgbData the RGB data of the image for filter applying
   */
  public void applyHistogramEqualization(int[][][] rgbData) {
    
    if (rgbData == null) {
      throw new IllegalStateException("The picture should be loaded first");
    }
    
    grayscaleProcessor.applyTransform(rgbData);
    int pixelNumber = rgbData.length * rgbData[0].length;
    
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        cdf[rgbData[i][j][0]]++;
      }
    }
    
    int tempSum = 0;
    int cdfMin = pixelNumber;
    for (int i = 0; i < cdf.length; i++) {
      if (cdf[i] != 0) {
        cdf[i] += tempSum;
        if (cdf[i] < cdfMin) {
          cdfMin = cdf[i];
        }
        tempSum = cdf[i];
      }
    }
    
    for (int i = 0; i < cdf.length; i++) {
      cdf[i] = ((cdf[i] - cdfMin) * 255) / (pixelNumber - cdfMin);
    }
    
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        rgbData[i][j][0] = cdf[rgbData[i][j][0]];
        rgbData[i][j][1] = rgbData[i][j][0];
        rgbData[i][j][2] = rgbData[i][j][0];
      }
    }
    
  }

}
