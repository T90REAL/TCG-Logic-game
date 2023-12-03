import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 *@ClassName StartPanel
 *@Description This is the start panel which will be executed first when running the program.
 *@Author Li Xiao, Guan Zhou, Yang Baihan, Chen Xinrui, He Zhicheng, Xu Yulun
 *@DATE 2023/05/28
 *@Version 2.0
 */
public class StartPanel extends JPanel implements MouseListener {
    private JLabel startLabel;
    private JLabel optionLabel;
    private JLabel aboutUsLabel;
    private JLabel backgroundLabel;
    private JLabel blankLabel;
    private JLayeredPane layeredPane;

    /**
     * Sets the Background icon.
     */
    ImageIcon backgroundIcon;
    /**
     * Sets the Start icon.
     */
    ImageIcon startIcon;
    /**
     * Sets the Option icon.
     */
    ImageIcon optionIcon;
    /**
     * Sets the About us icon.
     */
    ImageIcon aboutUsIcon;
    /**
     * Sets the Blank icon.
     */
    ImageIcon blankIcon;
    /**
     * Sets the height of imageIcon.
     */
    final static int imageIconHeight = 80;
    /**
     * Sets the width of imageIcon.
     */
    final static int imageIconWidth = 80;

    /**
     * Instantiates a new Start panel.
     * Imports the picture of Icon, sets the text and position of Label.
     */
    public StartPanel(){
        layeredPane = new JLayeredPane();
        layeredPane.setSize(1750, 1059);
        startIcon = new ImageIcon("src/source/startImage.png");
        optionIcon = new ImageIcon("src/source/optionImage.png");
        aboutUsIcon = new ImageIcon("src/source/aboutUsImage.png");
        backgroundIcon = new ImageIcon("src/source/tutu.gif");
        blankIcon = new ImageIcon("src/source/rip.png");

        startLabel = new JLabel("Start", startIcon, SwingConstants.LEADING);
        optionLabel = new JLabel("Option", optionIcon, SwingConstants.LEADING);
        aboutUsLabel = new JLabel("About Us", aboutUsIcon, SwingConstants.LEADING);
        backgroundLabel = new JLabel(null,backgroundIcon,SwingConstants.LEADING);
        blankLabel = new JLabel(null,blankIcon,SwingConstants.LEADING);
        backgroundLabel.setSize(1750,1059);
        backgroundLabel.setOpaque(true);

        setIconToLayeredPane(startLabel, JLayeredPane.PALETTE_LAYER);
        setIconToLayeredPane(optionLabel, JLayeredPane.PALETTE_LAYER);
        setIconToLayeredPane(aboutUsLabel, JLayeredPane.PALETTE_LAYER);
        setIconToLayeredPane(blankLabel,JLayeredPane.PALETTE_LAYER);

        this.setLayout(null);
        startLabel.setBounds((int) (0.45 * 1920), (int) (0.5 * 960), 200, 150);
        optionLabel.setBounds((int) (0.45 * 1920), (int) (0.65 * 960), 200, 150);
        aboutUsLabel.setBounds((int) (0.45 * 1920), (int) (0.8 * 960), 200, 150);
        blankLabel.setBounds((int) (0.45 * 1920), (int) (1 * 960), 200, 150);

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(startLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(optionLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(aboutUsLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(blankLabel,JLayeredPane.PALETTE_LAYER);
        layeredPane.setVisible(true);

        this.add(layeredPane);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
    }

    /**
     * Sets icon to layered pane.
     *
     * @param label The label
     * @param layer The layer
     */
    public void setIconToLayeredPane(JLabel label, int layer) {
        ImageIcon icon = (ImageIcon) label.getIcon();
        icon.setImage(icon.getImage().getScaledInstance(imageIconWidth, imageIconHeight, Image.SCALE_DEFAULT));
        label.setIcon(icon);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        label.setEnabled(true);
        label.addMouseListener(this);
        layeredPane.add(label, layer);
    }


    /**
     * To monitor the mouse events.
     *
     * @param e type of mouse events
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(startLabel)) {
            startLabel.setEnabled(true);
            Main.getMainFrame().repaint();
            Main.getMainFrame().setContentPane(Main.getMainFrame().getGamePanel());
            Main.getMainFrame().getGamePanel().setFocusable(true);
            playCustomSound("src/source/click.wav");
            revalidate();
        }
        else if (e.getSource().equals(optionLabel)){
            playCustomSound("src/source/click.wav");
        }
        else if (e.getSource().equals(aboutUsLabel)){
            playCustomSound("src/source/click.wav");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    /**
     * Plays background music.
     *
     * @param soundFilePath
     */
    private static void playCustomSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
