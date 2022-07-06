import imageview.ImageGuiView;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * This is a mock GUI-based view for testing.
 * @author ZHANG Mao
 *
 */
public class MockImageGuiView extends ImageGuiView {

  private static final long serialVersionUID = 777L;
  private StringBuilder log;
  private JButton fakePanelButton;
  private JButton fakePanelMosaicButton;
  private JMenuItem fakeMenuItem;
  
  /**
   * The constructor of the mock view for testing GUI-based view.
   * 
   * @param log the log for recording activities in the mock views
   */
  public MockImageGuiView(StringBuilder log) {
    this.log = log;
    fakePanelButton = new JButton("Blur");
    fakePanelMosaicButton = new JButton("Mosaic");
    fakeMenuItem = new JMenuItem("Load");
  }
  
  @Override
  public void showLoadStatus(String fileName) {
    this.log.append(fileName + " is successfully loaded!");
    this.log.append(System.lineSeparator());

  }

  @Override
  public void showSaveStatus(String fileName) {
    this.log.append("Processed file is saved as " + fileName);
    this.log.append(System.lineSeparator());

  }

  @Override
  public String getUserSelectFile(boolean isSave) {

    if (isSave) {
      this.log.append("Save Operation");
      this.log.append(System.lineSeparator());
    } else {
      this.log.append("non Save Operation");
      this.log.append(System.lineSeparator());
    }
    return "getUserSelectFile() Return";
  }

  @Override
  public int getMosaicSeedInput() {
    this.log.append("Get Seed");
    this.log.append(System.lineSeparator());
    return 8888;
  }

  @Override
  public void setImageDisplay(BufferedImage bufferImg) {

    this.log.append("Image Displayed");
    this.log.append(System.lineSeparator());

  }

  @Override
  public void virtualClick(String buttonName) {
    switch (buttonName) {
      case "Blur":
        this.fakePanelButton.doClick();
        break;
      case "Load":
        this.fakeMenuItem.doClick();
        break;
      case "Mosaic":
        this.fakePanelMosaicButton.doClick();
        break;
      default:
    }
    this.log.append(buttonName + " is virtual clicked");
    this.log.append(System.lineSeparator());
  }

  @Override
  public void setPanelActionListeners(ActionListener clicks) {
    
    this.fakePanelButton.addActionListener(clicks);
    this.fakePanelMosaicButton.addActionListener(clicks);
    
    this.log.append("PanelActionListeners added");
    this.log.append(System.lineSeparator());
  }

  @Override
  public void setMenuActionListeners(ActionListener clicks) {
    
    this.fakeMenuItem.addActionListener(clicks);
    
    this.log.append("MenuActionListeners added");
    this.log.append(System.lineSeparator());
  }
  
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
    this.log.append("Focus Reset");
    this.log.append(System.lineSeparator());
  }

}
