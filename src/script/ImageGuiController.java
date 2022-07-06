package script;

import images.ImageModel;
import imageview.ButtonShortcutListener;
import imageview.GraphicGenerator;
import imageview.ImageGuiView;
import imageview.MenuActionListener;
import imageview.MenuShortcutListener;
import imageview.PanelActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The controller of this image processor for console-based view.
 * 
 * @author ZHANG Mao
 *
 */
public class ImageGuiController extends AbstractImageController {

  private ImageGuiView gv;
  private String currentFile;

  /**
   * The constructor of the controller for view with GUI.
   * 
   * @param m model of this image processor
   */
  public ImageGuiController(ImageModel m) {
    super(m, null);
  }

  @Override
  public void setView(ImageView v) throws IllegalArgumentException {
    if (!(v instanceof ImageGuiView)) {
      throw new IllegalArgumentException("The view is not capatible " + "with the controller.");
    }

    this.gv = (ImageGuiView) v;
    configurePanelActionListener();
    configureMenuActionListener();
    configureButtonShortcutListener();
    configureMenuShortcutListener();

    this.gv.resetFocus();
  }

  /**
   * Configure action listeners for buttons in the main frame.
   */
  private void configurePanelActionListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    buttonClickedMap.put("Blur", () -> {
      try {
        im.applyBlur();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Sharpen", () -> {
      try {
        im.applySharpen();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Sepia", () -> {
      try {
        im.applySepia();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Dithering", () -> {
      try {
        im.applyDither();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Grayscale", () -> {
      try {
        im.applyGrayscale();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Edge Detection", () -> {
      try {
        im.applyEdgeDetection();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Histogram Equalization", () -> {
      try {
        im.applyHistogramEqualization();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Mosaic", () -> {
      try {
        im.applyMosaic(gv.getMosaicSeedInput());
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Reset", () -> {
      try {
        im.resetRgbData();
        gv.setImageDisplay(im.convertImage());
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      } catch (NullPointerException ex) {
        gv.resetFocus();
      }
    });

    buttonClickedMap.put("Draw a Graph", () -> {
      try {
        GraphicGenerator painter = gv.getUsersChoiceFromGraphicGenerator();
        if (painter != null) {
          gv.setImageDisplay(painter.draw());
          gv.resetFocus();
        }
        gv.resetFocus();
      } catch (IllegalStateException ex) {
        gv.resetFocus();
      }
    });

    PanelActionListener buttonListener = new PanelActionListener(buttonClickedMap);
    gv.setPanelActionListeners(buttonListener);
  }

  /**
   * Configure action listeners for buttons in the menu.
   */
  public void configureMenuActionListener() {
    Map<String, Runnable> menuItemClickedMap = new HashMap<>();
    menuItemClickedMap.put("Load", () -> {

      String tempFilePath = gv.getUserSelectFile(false);
      if (tempFilePath != null) {
        currentFile = tempFilePath;
        im.loadImage(currentFile);
        gv.setImageDisplay(im.convertImage());
        gv.showLoadStatus(currentFile);
        gv.resetFocus();
      } else {
        gv.resetFocus();
      }

    });

    menuItemClickedMap.put("Save As", () -> {
      String saveFilePath = gv.getUserSelectFile(true);
      if (saveFilePath != null) {
        im.saveImage(saveFilePath);
        gv.showSaveStatus(saveFilePath);
        gv.resetFocus();
      } else {
        gv.resetFocus();
      }

    });

    menuItemClickedMap.put("Save", () -> {

      if (currentFile != null) {
        if (gv.getUsersChoiceFromOptionPanel() == 0) {
          im.saveImage(currentFile);
          gv.showSaveStatus(currentFile);
          gv.resetFocus();
        } else {
          gv.resetFocus();
        }
      } else {
        gv.resetFocus();
      }
    });

    menuItemClickedMap.put("Process with Script File", () -> {
      gv.showMessage("The image processer is running by script, please wait...");
      String scriptFilePath = gv.getUserSelectFile(false);
      if (!scriptFilePath.matches("^.*\\.(txt|TXT)$")) {
        throw new IllegalArgumentException("Script file shou be a TXT file");
      }

      if (scriptFilePath != null) {

        try {
          Scanner input = new Scanner(new FileReader(scriptFilePath));

          String currentLine;
          String[] splitedCurrentLine;

          do {
            currentLine = input.nextLine();
            splitedCurrentLine = currentLine.split("\\s+");
            try {
              switch (splitedCurrentLine[0]) {
                case "load":
                  im.loadImage(splitedCurrentLine[1]);
                  break;
                case "save":
                  im.saveImage(splitedCurrentLine[1]);
                  break;
                case "blur":
                  im.applyBlur();
                  break;
                case "sharpen":
                  im.applySharpen();
                  break;
                case "sepia":
                  im.applySepia();
                  break;
                case "grayscale":
                  im.applyGrayscale();
                  break;
                case "edge-detection":
                  im.applyEdgeDetection();
                  break;
                case "histogram-equalization":
                  im.applyHistogramEqualization();
                  break;
                case "mosaic":
                  im.applyMosaic(Integer.parseInt(splitedCurrentLine[1]));
                  break;
                case "dithering":
                  im.applyDither();
                  break;
                default:
                  gv.showMessage("!!! CANNOT RECOGNIZE THE OPERATION");
                  gv.showMessage("Please check the grammer then try again.");
                  input.close();
              }
            } catch (IllegalArgumentException ex) {
              gv.showMessage("!!! OPERATION FAILED: " + splitedCurrentLine[0]);

              input.close();
            } catch (IllegalStateException ex) {
              gv.showMessage("!!! OPERATION FAILED: " + splitedCurrentLine[0]);
              gv.showMessage(
                  "The picture should be loaded before processing. Please check your script file.");
              input.close();

            }
          } while (input.hasNextLine() == true);

          input.close();
        } catch (FileNotFoundException e) {
          gv.resetFocus();
        }

        gv.showMessage("Image processing completed!");
        gv.resetFocus();
      }
    });

    MenuActionListener menuListener = new MenuActionListener(menuItemClickedMap);
    gv.setMenuActionListeners(menuListener);
  }

  /**
   * Configure key event listeners for buttons in the main frame.
   */
  public void configureButtonShortcutListener() {
    Map<Integer, Runnable> buttonShortcutMap = new HashMap<>();
    buttonShortcutMap.put(KeyEvent.VK_B, () -> {
      gv.virtualClick("Blur");
    });

    buttonShortcutMap.put(KeyEvent.VK_P, () -> {
      gv.virtualClick("Sharpen");
    });

    buttonShortcutMap.put(KeyEvent.VK_S, () -> {
      gv.virtualClick("Sepia");
    });

    buttonShortcutMap.put(KeyEvent.VK_G, () -> {
      gv.virtualClick("Grayscale");
    });

    buttonShortcutMap.put(KeyEvent.VK_D, () -> {
      gv.virtualClick("Dithering");
    });

    buttonShortcutMap.put(KeyEvent.VK_E, () -> {
      gv.virtualClick("Edge Detection");
    });

    buttonShortcutMap.put(KeyEvent.VK_H, () -> {
      gv.virtualClick("Histogram Equalization");
    });

    buttonShortcutMap.put(KeyEvent.VK_M, () -> {
      gv.virtualClick("Mosaic");
    });

    buttonShortcutMap.put(KeyEvent.VK_R, () -> {
      gv.virtualClick("Reset");

    });

    buttonShortcutMap.put(KeyEvent.VK_W, () -> {
      gv.virtualClick("Draw a Graph");

    });

    ButtonShortcutListener buttonShortcutListener = new ButtonShortcutListener(buttonShortcutMap);

    gv.addKeyListener(buttonShortcutListener);
  }

  /**
   * Configure key event listeners for buttons in the menu.
   */
  public void configureMenuShortcutListener() {
    Map<Integer, Runnable> menuShortcutMap = new HashMap<>();
    menuShortcutMap.put(KeyEvent.VK_L, () -> {
      gv.virtualClick("Load");
    });

    menuShortcutMap.put(KeyEvent.VK_S, () -> {
      gv.virtualClick("Save");
    });

    menuShortcutMap.put(KeyEvent.VK_A, () -> {
      gv.virtualClick("Save As");
    });

    menuShortcutMap.put(KeyEvent.VK_T, () -> {
      gv.virtualClick("Script");
    });

    MenuShortcutListener menuShortcutListener = new MenuShortcutListener(menuShortcutMap);

    gv.addKeyListener(menuShortcutListener);
  }
}
