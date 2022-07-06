package imageview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;

/**
 * A GUI-based view of this image processor.
 * 
 * @author ZHANG Mao
 *
 */
public class ImageGuiView extends AbstractImageGuiView {

  private static final long serialVersionUID = 202296729L;
  
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private JPanel toolPanel;
  private JFileChooser fc;
  private JScrollPane scrollPane;
  private ImageLabel imgLabel;
  private JMenuBar bar;
  private JMenu menu;
  private JMenuItem loadBar;
  private JMenuItem scriptBar;
  private JMenuItem saveBar;
  private JMenuItem saveAsBar;
  private JButton blur;
  private JButton sharpen;
  private JButton sepia;
  private JButton grayscale;
  private JButton edgeDetection;
  private JButton histogramEqualization;
  private JButton dithering;
  private JButton mosaic;
  private JButton reset;
  private JSlider seed;
  private JLabel hintLabel;

  private JButton generate;
  
  private final GenerateDialog gtDia;

  /**
   * The constructor of this GUI-based view.
   */
  public ImageGuiView() {
    
    super(WIDTH, HEIGHT, JFrame.EXIT_ON_CLOSE, new BorderLayout());

    super.messageLabel = new JLabel(" Load an image to start!");
    add(messageLabel, BorderLayout.SOUTH);

    this.gtDia = new GenerateDialog();
    
    blur = new JButton("Blur");
    sharpen = new JButton("Sharpen");
    sepia = new JButton("Sepia");
    grayscale = new JButton("Grayscale");
    dithering = new JButton("Dithering");
    mosaic = new JButton("Mosaic");
    reset = new JButton("Reset");
    reset.setBackground(Color.GREEN);
    edgeDetection = new JButton("Edge Detection");
    histogramEqualization = new JButton("Histogram Equalization");
    
    generate = new JButton("Draw a Graph");
    generate.setBackground(Color.CYAN);
    
    seed = new JSlider(0, 20000);
    seed.setMajorTickSpacing(5000);
    seed.setPaintTicks(true);
    seed.setPaintLabels(true);
    hintLabel = new JLabel("amounts of mosaic seeds: ");

    fc = new JFileChooser();
    File workingDirectory = new File(System.getProperty("user.dir"));
    fc.setCurrentDirectory(workingDirectory);
    ;

    toolPanel = new JPanel();
    toolPanel.setLayout(new FlowLayout());
    toolPanel.add(reset);
    toolPanel.add(generate);
    toolPanel.add(blur);
    toolPanel.add(sharpen);
    toolPanel.add(sepia);
    toolPanel.add(grayscale);
    toolPanel.add(edgeDetection);
    toolPanel.add(histogramEqualization);
    toolPanel.add(dithering);
    toolPanel.add(mosaic);
    toolPanel.add(hintLabel);
    toolPanel.add(seed);
    

    add(toolPanel, BorderLayout.NORTH);

    imgLabel = new ImageLabel();
    scrollPane = new JScrollPane(imgLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setViewportView(imgLabel);
    scrollPane.setPreferredSize(new Dimension(600, 400));

    add(scrollPane, BorderLayout.CENTER);

    menu = new JMenu("File");
    loadBar = new JMenuItem("Load");
    scriptBar = new JMenuItem("Process with Script File");
    saveBar = new JMenuItem("Save");
    saveAsBar = new JMenuItem("Save As");

    menu.add(loadBar);
    menu.add(scriptBar);
    menu.add(saveBar);
    menu.add(saveAsBar);

    bar = new JMenuBar();
    bar.add(menu);

    setJMenuBar(bar);

    pack();
    setVisible(true);
  }

  @Override
  public void showLoadStatus(String fileName) {
    super.showMessage(fileName + " is successfully loaded!");

  }

  @Override
  public void showSaveStatus(String fileName) {
    super.showMessage("Processed file is saved as " + fileName);

  }

  /**
   * Get the path of the file chosen by users from the JFilechooser.
   * @param isSave true if the result comes from a "Save" operation
   * @return path of the file chosen by users
   */
  public String getUserSelectFile(boolean isSave) {

    if (isSave) {
      int returnVal = fc.showSaveDialog(fc);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        return fc.getSelectedFile().getPath();
      }
    } else {
      int returnVal = fc.showOpenDialog(fc);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        return fc.getSelectedFile().getPath();
      }
    }
    return null;
  }
  
  /**
   * Open an option panel for user's confirmation.
   * @return user's choice
   */
  public int getUsersChoiceFromOptionPanel() {
    int choice = JOptionPane.showConfirmDialog(this, "The original file will be overwritten. "
        + "Click OK to continue.", "Warning", JOptionPane.OK_CANCEL_OPTION);
    return choice;
  }
  
  /**
   * Open an dialog for user to draw simple graphs.
   * @return user's setting and inputs
   */
  public GraphicGenerator getUsersChoiceFromGraphicGenerator() {
    
    return gtDia.showDiaglog();
    
  }

  /**
   * Get the amount of mosaic seeds from the JSlide bar.
   * 
   * @return amount of mosaic seeds
   */
  public int getMosaicSeedInput() {
    return this.seed.getValue();
  }

  /**
   * Set and reset the image displayed in the frame.
   * 
   * @param bufferImg image to be displayed
   */
  public void setImageDisplay(BufferedImage bufferImg) {

    imgLabel.displayImg(bufferImg);

  }

  /**
   * Simulate an event of button click according to the given order.
   * 
   * @param buttonName the order of which button to be clicked
   */
  public void virtualClick(String buttonName) {
    switch (buttonName) {
      case "Blur":
        blur.doClick();
        break;
      case "Sharpen":
        sharpen.doClick();
        break;
      case "Sepia":
        sepia.doClick();
        break;
      case "Dithering":
        dithering.doClick();
        break;
      case "Grayscale":
        grayscale.doClick();
        break;
      case "Edge Detection":
        edgeDetection.doClick();
        break;
      case "Histogram Equalization":
        histogramEqualization.doClick();
        break;
      case "Mosaic":
        mosaic.doClick();
        break;
      case "Load":
        loadBar.doClick();
        break;
      case "Reset":
        reset.doClick();
        break;
      case "Draw a Graph":
        generate.doClick();
        break;
      case "Script":
        scriptBar.doClick();
        break;
      case "Save":
        saveBar.doClick();
        break;
      case "Save As":
        saveAsBar.doClick();
        break;
      default:
    }
  }

  /**
   * Binding an action listener to buttons in the main frame.
   * 
   * @param clicks action listener to be bound to
   */
  public void setPanelActionListeners(ActionListener clicks) {
    this.blur.addActionListener(clicks);
    this.sharpen.addActionListener(clicks);
    this.sepia.addActionListener(clicks);
    this.grayscale.addActionListener(clicks);
    this.edgeDetection.addActionListener(clicks);
    this.dithering.addActionListener(clicks);
    this.histogramEqualization.addActionListener(clicks);
    this.mosaic.addActionListener(clicks);
    this.reset.addActionListener(clicks);
    
    this.generate.addActionListener(clicks);
  }

  /**
   * Binding an action listener to items in the menu.
   * 
   * @param clicks action listener to be bound to
   */
  public void setMenuActionListeners(ActionListener clicks) {
    this.loadBar.addActionListener(clicks);
    this.saveBar.addActionListener(clicks);
    this.saveAsBar.addActionListener(clicks);
    this.scriptBar.addActionListener(clicks);
  }

}
