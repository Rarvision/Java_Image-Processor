package script;

import java.io.IOException;

/**
 * A console-based text view of this image processor.
 * @author ZHANG Mao
 *
 */
public class ImageTextView extends AbstractImageTextView {
  
  /**
   * The constructor of this console-based text view.
   * @param out the way how output displayed by the view 
   */
  public ImageTextView(Appendable out) {
    super(out);
  }

  /**
   * Show massage of doing a certain processing operation.
   * @param processName name of the processing operation
   */
  public void showStatus(String processName) {
    try {
      super.out.append("The processor is ");
      switch (processName) {
        case "blur":
          super.out.append("blurring...");
          break;
        case "sharpen":
          super.out.append("sharpening...");
          break;
        case "histogram-equalization":
          super.out.append("applying enhanced grayscale conversion with "
              + "histogram equaliztion...");
          break;
        case "edge-detection":
          super.out.append("applying effect of edge-detection");
          break;
        case "grayscale":
          super.out.append("applying grayscale conversion to the picture...");
          break;
        case "sepia":
          super.out.append("applying sepia conversion to the picture...");
          break;
        case "dither":
          super.out.append("applying dithering conversion to the picture...");
          break;
        default:
          if (processName.matches("^mosaic.*")) {
            super.out.append("applying ");
            super.out.append(processName);
            super.out.append(" conversion to the picture...");
          }
      }
      super.out.append(System.lineSeparator());
    } catch (IOException ex) {
      throw new IllegalStateException("Error occurred writing to the view.");
    } 
  }
}
