package images;

/**
 * This class is for converting an image into a gray scale image with edge
 * detection.
 * 
 * @author ZHANG Mao
 *
 */
public class EdgeDetection {

  private ArbitraryFilter kxTr;
  private ArbitraryFilter kyTr;

  /**
   * The constructor of the EdgeDetection class.
   */
  public EdgeDetection() {
    kxTr = new ArbitraryFilter(new double[][] { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } });
    kyTr = new ArbitraryFilter(new double[][] { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } });
  }

  /**
   * converting an image into a gray scale image with edge.
   * 
   * @param rgbData the RGB data of the image for conversion
   */
  public void applyEdgeDetection(int[][][] rgbData) {
    if (rgbData == null) {
      throw new IllegalStateException("The picture should be loaded first");
    }
    int[][][] kxRgbData = new int[rgbData.length][rgbData[0].length][rgbData[0][0].length];
    int[][][] kyRgbData = new int[rgbData.length][rgbData[0].length][rgbData[0][0].length];

    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        for (int k = 0; k < rgbData[0][0].length; k++) {
          kxRgbData[i][j][k] = rgbData[i][j][k];
          kyRgbData[i][j][k] = rgbData[i][j][k];
        }
      }
    }

    kxTr.applyFilter(kxRgbData);
    kyTr.applyFilter(kyRgbData);

    int min = 400;
    int max = 0;

    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        for (int k = 0; k < rgbData[0][0].length; k++) {
          rgbData[i][j][k] = (int) Math
              .sqrt(Math.pow(kxRgbData[i][j][k], 2) + Math.pow(kyRgbData[i][j][k], 2));
          min = Math.min(min, rgbData[i][j][k]);
          max = Math.max(max, rgbData[i][j][k]);
        }
      }
    }

    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        for (int k = 0; k < rgbData[0][0].length; k++) {
          rgbData[i][j][k] = (255 * (rgbData[i][j][k] - min)) / (max - min);
        }
      }
    }
    
    Grayscale grayscaleProcessor = new Grayscale();
    grayscaleProcessor.applyTransform(rgbData);

  }
}
