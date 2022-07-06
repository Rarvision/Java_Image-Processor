package images;

/**
 * This class is for converting an image using arbitrary kernels.
 * 
 * @author ZHANG Mao
 *
 */
public class ArbitraryFilter extends AbstractFilter {

  /**
   * Converting an image using arbitrary kernels.\
   * 
   * @param kernel customized kernel matrix
   */
  public ArbitraryFilter(double[][] kernel) {
    super(kernel);
  }

}
