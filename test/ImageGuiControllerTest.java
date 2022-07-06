import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import script.ImageController;
import script.ImageGuiController;

/**
 * Test class for the controller for GUI-based view.
 * 
 * @author ZHANG Mao
 *
 */
public class ImageGuiControllerTest {
  
  private StringBuilder modelLog;
  private StringBuilder viewLog;
  private MockImageModel mm;
  private MockImageGuiView mgv;
  private ImageController ic;
  
  /**
   * Set up the test process.
   */
  @Before
  public void setUp() {
    this.modelLog = new StringBuilder();
    this.viewLog = new StringBuilder();
    this.mm = new MockImageModel(modelLog);
    this.mgv = new MockImageGuiView(viewLog);
    this.ic = new ImageGuiController(mm);
    
  }
  
  @Test
  public void testSetView() {
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("PanelActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("MenuActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    
    this.ic.setView(mgv);
    assertEquals(expectedViewLog.toString(), viewLog.toString());
  }
  
  @Test
  public void testClickButton() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("Blurring...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("image converted");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("PanelActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("MenuActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image Displayed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Blur is virtual clicked");
    expectedViewLog.append(System.lineSeparator());
    
    this.ic.setView(mgv);
    mgv.virtualClick("Blur");
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
    
  }
  
  @Test
  public void testClickMenuItem() {
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("getUserSelectFile() Return");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("image converted");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("PanelActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("MenuActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("non Save Operation");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image Displayed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("getUserSelectFile() Return is successfully loaded!");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Load is virtual clicked");
    expectedViewLog.append(System.lineSeparator());
    
    this.ic.setView(mgv);
    mgv.virtualClick("Load");
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testClickMosaicButton() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("Mosaic with 8888 seeds applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("image converted");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("PanelActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("MenuActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Get Seed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image Displayed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Mosaic is virtual clicked");
    expectedViewLog.append(System.lineSeparator());
    
    this.ic.setView(mgv);
    mgv.virtualClick("Mosaic");
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
    
  }
  
  @Test
  public void testMultipleProcess() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("Blurring...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("image converted");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Mosaic with 8888 seeds applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("image converted");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("PanelActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("MenuActionListeners added");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image Displayed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Blur is virtual clicked");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Get Seed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image Displayed");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Focus Reset");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Mosaic is virtual clicked");
    expectedViewLog.append(System.lineSeparator());
    
    this.ic.setView(mgv);
    mgv.virtualClick("Blur");
    mgv.virtualClick("Mosaic");
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
    
  }

}
