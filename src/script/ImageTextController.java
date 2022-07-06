package script;

import images.ImageModel;
import java.util.Scanner;


/**
 * The controller of this image processor for console-based view.
 * 
 * @author ZHANG Mao
 *
 */
public class ImageTextController extends AbstractImageController {
  
  private ImageTextView tv;

  /**
   * The constructor of the controller for console-based view.
   * 
   * @param m model of this image processor
   * @param in the source of input to use
   */
  public ImageTextController(ImageModel m, Readable in) {
    super(m, in);
  }
  
  @Override
  public void setView(ImageView v) {
    if (!(v instanceof ImageTextView)) {
      throw new IllegalArgumentException("The view is not capatible " + "with the controller.");
    }
    
    this.tv = (ImageTextView) v;
  }

  @Override
  public void go() {
    if (this.tv == null) {
      throw new IllegalStateException("A valid view shoule be set first.");
    }

    Scanner input = new Scanner(super.in);

    String currentLine;
    String[] splitedCurrentLine;

    tv.showMessage("The image processer is running...");
    tv.showSeparator();

    do {
      currentLine = input.nextLine();
      splitedCurrentLine = currentLine.split("\\s+");
      try {
        switch (splitedCurrentLine[0]) {
          case "load":
            im.loadImage(splitedCurrentLine[1]);
            tv.showLoadStatus(splitedCurrentLine[1]);
            break;
          case "save":
            im.saveImage(splitedCurrentLine[1]);
            tv.showSaveStatus(splitedCurrentLine[1]);
            tv.showSeparator();
            break;
          case "blur":
            im.applyBlur();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          case "sharpen":
            im.applySharpen();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          case "sepia":
            im.applySepia();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          case "grayscale":
            im.applyGrayscale();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          case "edge-detection":
            im.applyEdgeDetection();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          case "histogram-equalization":
            im.applyHistogramEqualization();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          case "mosaic":
            im.applyMosaic(Integer.parseInt(splitedCurrentLine[1]));
            tv.showStatus(splitedCurrentLine[0] + " (seeds: " + splitedCurrentLine[1] + ")");
            break;
          case "dithering":
            im.applyDither();
            tv.showStatus(splitedCurrentLine[0]);
            break;
          default:
            tv.showSeparator();
            tv.showMessage("!!! CANNOT RECOGNIZE THE OPERATION");
            tv.showMessage("Please check the grammer then try again.");
            tv.showSeparator();
            input.close();
            return;
        }
      } catch (IllegalArgumentException ex) {
        tv.showSeparator();
        tv.showMessage("!!! OPERATION FAILED: " + splitedCurrentLine[0]);
        if (splitedCurrentLine[0].equals("mosaic")) {
          tv.showMessage("The amount of seeds must be an integer.");
        } else {
          tv.showMessage("Please check the path then try again.");
        }
        tv.showSeparator();
        input.close();
        return;
      } catch (IllegalStateException ex) {
        tv.showSeparator();
        tv.showMessage("!!! OPERATION FAILED: " + splitedCurrentLine[0]);
        tv.showMessage("The picture should be loaded before processing");
        tv.showSeparator();
        input.close();
        return;
      }
    } while (input.hasNextLine() == true);

    input.close();
    tv.showMessage("Image processing completed!");
    tv.showSeparator();
    return;

  }
}
