package imageview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

/**
 * A dialog in which user can set and generate a graph.
 * 
 * @author ZHANG Mao
 *
 */
public class GenerateDialog extends JDialog {

  private static final long serialVersionUID = 8897L;

  private JPanel rbPane;
  private JPanel ckPane;
  private JPanel flgPane;
  private JTabbedPane gtTabbedPane;
  private GraphicGenerator painter;

  private Color color1;
  private Color color2;

  /**
   * Constructor of GenerateDialog class.
   */
  public GenerateDialog() {
    super();
    setModal(true);
    this.setTitle("Generate an Image");
    rbPane = createRainbowPanel();
    ckPane = createCheckerBoardPanel();
    flgPane = createFlagPanel();
    gtTabbedPane = new JTabbedPane();
    gtTabbedPane.addTab("Generate a Rainbow", rbPane);
    gtTabbedPane.addTab("Generate a Checker Board", ckPane);
    gtTabbedPane.addTab("Generate a National Flag", flgPane);
    add(gtTabbedPane);

    color1 = Color.BLACK;
    color2 = Color.RED;

    pack();
  }

  /**
   * Create a panel for generating rainbow pattern.
   * @return panel for generating rainbow pattern
   */
  private JPanel createRainbowPanel() {

    final JPanel rainbowPanel = new JPanel(new BorderLayout());

    final JLabel hintLabel1 = new JLabel("Width(10-2000): ");
    final JLabel hintLabel2 = new JLabel("Height(10-2000): ");
    SpinnerNumberModel spModel1 = new SpinnerNumberModel(400, 10, 2000, 1);
    SpinnerNumberModel spModel2 = new SpinnerNumberModel(400, 10, 2000, 1);
    final JSpinner spinner1 = new JSpinner(spModel1);
    final JSpinner spinner2 = new JSpinner(spModel2);

    final JLabel hintLabel3 = new JLabel("Direction: ");
    JRadioButton rb1 = new JRadioButton("Horizontal", true);
    rb1.setActionCommand("H");
    JRadioButton rb2 = new JRadioButton("Vertical");
    rb2.setActionCommand("V");
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(rb1);
    buttonGroup.add(rb2);

    final JButton generateButton = new JButton("Generate");
    final JButton cancelButton = new JButton("Cancel");

    JPanel heightPane = new JPanel();
    heightPane.add(hintLabel1);
    heightPane.add(spinner1);
    heightPane.add(hintLabel2);
    heightPane.add(spinner2);
    heightPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

    JPanel directionPane = new JPanel();
    directionPane.add(hintLabel3);
    directionPane.add(rb1);
    directionPane.add(rb2);
    directionPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new GridLayout(1, 0, 10, 10));
    buttonPane.add(generateButton);
    buttonPane.add(cancelButton);
    buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    rainbowPanel.add(heightPane, BorderLayout.NORTH);
    rainbowPanel.add(directionPane, BorderLayout.CENTER);
    rainbowPanel.add(buttonPane, BorderLayout.SOUTH);

    generateButton.addActionListener(e -> {
      boolean direction;
      if ("H".equals(buttonGroup.getSelection().getActionCommand())) {
        direction = false;
      } else {
        direction = true;
      }
      this.painter = new RainbowGenerator((int) spinner1.getValue(), (int) spinner2.getValue(),
          direction);
      setVisible(false);
      dispose();
    });

    cancelButton.addActionListener(e -> {
      this.painter = null;
      setVisible(false);
      dispose();
    });

    return rainbowPanel;

  }

  /**
   * Create a panel for generating checker board pattern.
   * @return panel for generating checker board pattern
   */
  private JPanel createCheckerBoardPanel() {
    final JPanel checkerPanel = new JPanel(new BorderLayout());

    JLabel hintLabel1 = new JLabel("Height(10-2000): ");
    JLabel hintLabel2 = new JLabel("Number of Squares in Each Side(must < height): ");
    SpinnerNumberModel spModel1 = new SpinnerNumberModel(400, 10, 2000, 1);
    SpinnerNumberModel spModel2 = new SpinnerNumberModel(8, 2, 200, 1);
    JSpinner spinner1 = new JSpinner(spModel1);
    JSpinner spinner2 = new JSpinner(spModel2);

    final JButton jb1 = new JButton("Select First Color");
    final JButton jb2 = new JButton("Select Secons Color");

    final JButton generateButton = new JButton("Generate");
    final JButton cancelButton = new JButton("Cancel");

    JPanel heightPane = new JPanel();
    heightPane.add(hintLabel1);
    heightPane.add(spinner1);
    heightPane.add(hintLabel2);
    heightPane.add(spinner2);
    heightPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

    JPanel colorPane = new JPanel();
    colorPane.add(jb1);
    colorPane.add(jb2);
    colorPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new GridLayout(1, 0, 10, 10));
    buttonPane.add(generateButton);
    buttonPane.add(cancelButton);
    buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    checkerPanel.add(heightPane, BorderLayout.NORTH);
    checkerPanel.add(colorPane, BorderLayout.CENTER);
    checkerPanel.add(buttonPane, BorderLayout.SOUTH);

    jb1.addActionListener(e -> setColor1());
    jb2.addActionListener(e -> setColor2());

    generateButton.addActionListener(e -> {
      try {
        this.painter = new CheckerBoardGenerator((int) spinner1.getValue(),
            (int) spinner2.getValue(), this.color1, this.color2);
      } catch (IllegalArgumentException ex) {
        this.painter = null;
      }
      setVisible(false);
      dispose();
    });

    cancelButton.addActionListener(e -> {
      this.painter = null;
      setVisible(false);
      dispose();
    });

    return checkerPanel;
  }

  /**
   * Create a panel for generating national flags.
   * @return panel for generating national flags
   */
  private JPanel createFlagPanel() {

    final JPanel flagPanel = new JPanel(new BorderLayout());

    JLabel hintLabel1 = new JLabel("Height(10-2000): ");
    SpinnerNumberModel spModel1 = new SpinnerNumberModel(400, 10, 2000, 1);
    JSpinner spinner1 = new JSpinner(spModel1);

    final JLabel hintLabel2 = new JLabel("Select a Nation: ");
    final JComboBox<String> nationList = new JComboBox<String>(
        new String[] { "Norway", "Switzerland", "Greece" });

    final JButton generateButton = new JButton("Generate");
    final JButton cancelButton = new JButton("Cancel");

    JPanel heightPane = new JPanel();
    heightPane.add(hintLabel1);
    heightPane.add(spinner1);
    heightPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

    JPanel selectPane = new JPanel();
    selectPane.add(hintLabel2);
    selectPane.add(nationList);
    selectPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new GridLayout(1, 0, 10, 10));
    buttonPane.add(generateButton);
    buttonPane.add(cancelButton);
    buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    flagPanel.add(heightPane, BorderLayout.NORTH);
    flagPanel.add(selectPane, BorderLayout.CENTER);
    flagPanel.add(buttonPane, BorderLayout.SOUTH);

    generateButton.addActionListener(e -> {
      this.painter = new FlagGenerator((int) spinner1.getValue(),
          (String) nationList.getSelectedItem());
      setVisible(false);
      dispose();
    });

    cancelButton.addActionListener(e -> {
      this.painter = null;
      setVisible(false);
      dispose();
    });

    return flagPanel;

  }

  /**
   * Helper method of createCheckerBoardPanel().
   */
  private void setColor1() {
    this.color1 = JColorChooser.showDialog(this, "Choose the First Color", Color.BLACK);
  }

  /**
   * Helper method of createCheckerBoardPanel().
   */
  private void setColor2() {
    this.color2 = JColorChooser.showDialog(this, "Choose the Second Color", Color.RED);
  }

  /**
   * Open this dialog and getting the input.
   * @return the user's setting and input
   */
  public GraphicGenerator showDiaglog() {
    setVisible(true);
    return painter;
  }
}