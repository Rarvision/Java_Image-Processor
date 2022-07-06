package images;

/**
 * This abstract class implementing the function of filter (blur and sharpen).
 * @author ZHANG Mao
 *
 */
public abstract class AbstractFilter {
  
  protected double[][] kernel;
  
  protected final int radius;
  protected final int sideLength;
  protected int[][][] filteredRgbData;
  
  /**
   * The constructor of the AbstractFilter class.
   */
  protected AbstractFilter(double[][] kernel) {
    
    this.kernel = kernel;
    this.radius = kernel.length / 2;
    this.sideLength = kernel.length;
  }
  
  
  
  /**
   * Apply filter to an image. The original image will be changed.
   * @param rgbData the RGB data of the image for filter applying
   */
  protected void applyFilter(int[][][] rgbData) {
    
    if (rgbData == null) {
      throw new IllegalStateException("The picture should be loaded first");
    }
    
    this.filteredRgbData = new int[rgbData.length][rgbData[0].length][3];
    
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[i].length; j++) {
        
        this.filteredRgbData[i][j][0] = 0;
        this.filteredRgbData[i][j][1] = 0;
        this.filteredRgbData[i][j][2] = 0;
        
        for (int x = 0; x < sideLength; x++) {
          for (int y = 0; y < sideLength; y++) {
            
            if ((i - radius + x >= 0) 
                && (j - radius + y >= 0) 
                && (i - radius + x <= rgbData.length - 1) 
                && (j - radius + y <= rgbData[i].length - 1)) {
              this.filteredRgbData[i][j][0] += 
                  rgbData[i - radius + x][j - radius + y][0] 
                      * this.kernel[x][y];          
              this.filteredRgbData[i][j][1] += 
                  rgbData[i - radius + x][j - radius + y][1] 
                      * this.kernel[x][y];
              this.filteredRgbData[i][j][2] += 
                  rgbData[i - radius + x][j - radius + y][2] 
                      * this.kernel[x][y];
            }
            
          }
        }
        
        for (int channel = 0; channel < 3; channel++) {
          this.filteredRgbData[i][j][channel] 
              = Clamp.clamper(this.filteredRgbData[i][j][channel]);
        }
        
      }
    }
    
    for (int i = 0; i < rgbData.length; i++) {
      for (int j = 0; j < rgbData[i].length; j++) {
        
        rgbData[i][j][0] = this.filteredRgbData[i][j][0];
        rgbData[i][j][1] = this.filteredRgbData[i][j][1];
        rgbData[i][j][2] = this.filteredRgbData[i][j][2];
      
      }
    }
  }

}
