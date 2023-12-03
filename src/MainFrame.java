import javax.swing.*;
import java.awt.*;


/**
 * The type Main frame.
 */
public class MainFrame extends JFrame {
    private StartPanel startPanel;
    private GamePanel gamePanel;

    /**
     * Instantiates a new Main frame.
     */
    public MainFrame() {
        this.setSize(1750, 1059);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setTitle("Logic Road Builder Game by Group 7");
        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        this.add(startPanel);
        this.add(gamePanel);
        this.setContentPane(startPanel);
        this.setVisible(true);

    }

    /**
     * Gets start panel.
     *
     * @return the start panel
     */

    public StartPanel getStartPanel() {
        return startPanel;
    }

    /**
     * Gets game panel.
     *
     * @return the game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}