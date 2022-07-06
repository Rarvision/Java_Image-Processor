package images;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is for applying mosaic-like style to an image.
 * @author ZHANG Mao
 *
 */
public class Mosaic {
  
  int seeds;
  Random rand;
  
  /**
   * The constructor of the Mosaic class. The amount of mosaic seeds should be
   * given by users.
   * @param seeds the amount of mosaic seeds
   */
  public Mosaic(int seeds) {
    this.seeds = seeds;
    this.rand = new Random();
  }
  
  /**
   * Applying a mosaic-like style to an image with the given amount of seeds. 
   * The original image will be changed after conversion.
   * @param rgbData the RGB data of the image for conversion
   */
  public void applyMosaic(int[][][] rgbData) {
    
    if (rgbData == null) {
      throw new IllegalStateException("The picture should be loaded first");
    }

    int[][] groupingRgbData;
    
    
    groupingRgbData = new int[rgbData.length][rgbData[0].length];
    
    
    ArrayList<int[]> seedPoints = new ArrayList<int[]>();
    for (int n = 0; n < this.seeds; n++) {
      int i = this.rand.nextInt(rgbData.length);
      int j = this.rand.nextInt(rgbData[0].length);
      seedPoints.add(new int[] {i, j});
    }
    
    for (int i = 0; i < groupingRgbData.length; i++) {
      for (int j = 0; j < groupingRgbData[0].length; j++) {
        
        double minDistanceSquare = Math.pow(groupingRgbData.length, 2) 
            + Math.pow(groupingRgbData[0].length, 2);
        int groupNumber = 0;
        
        for (int n = 1; n <= this.seeds; n++) {
          if (Math.pow((i - seedPoints.get(n - 1)[0]), 2) 
              + Math.pow((j - seedPoints.get(n - 1)[1]), 2) 
              < minDistanceSquare) {
            minDistanceSquare = Math.pow((i - seedPoints.get(n - 1)[0]), 2) 
                + Math.pow((j - seedPoints.get(n - 1)[1]), 2);
            groupNumber = n;
          }
        }
        groupingRgbData[i][j] = groupNumber;
      }
    }
    
    int[] groupRedColor = new int[this.seeds];
    int[] groupGreenColor = new int[this.seeds];
    int[] groupBlueColor = new int[this.seeds];
    int[] numberOfEachGroup = new int[this.seeds];
    
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        groupRedColor[groupingRgbData[i][j] - 1] += rgbData[i][j][0];
        groupGreenColor[groupingRgbData[i][j] - 1] += rgbData[i][j][1];
        groupBlueColor[groupingRgbData[i][j] - 1] += rgbData[i][j][2];
        numberOfEachGroup[groupingRgbData[i][j] - 1] += 1;
      }
    }
    
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[0].length; j++) {
        rgbData[i][j][0] = groupRedColor[groupingRgbData[i][j] - 1] 
            / numberOfEachGroup[groupingRgbData[i][j] - 1];
        rgbData[i][j][1] = groupGreenColor[groupingRgbData[i][j] - 1] 
            / numberOfEachGroup[groupingRgbData[i][j] - 1];
        rgbData[i][j][2] = groupBlueColor[groupingRgbData[i][j] - 1] 
            / numberOfEachGroup[groupingRgbData[i][j] - 1];
      }
    }
  }
}
