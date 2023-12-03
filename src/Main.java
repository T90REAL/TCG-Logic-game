/**
 * T
 */
public class Main {
    /**
     * The Main frame.
     */
    static MainFrame mainFrame;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Logic.init();
        mainFrame = new MainFrame();
    }

    /**
     * Gets main frame.
     *
     * @return the main frame
     */
    public static MainFrame getMainFrame() {
        return mainFrame;
    }
}