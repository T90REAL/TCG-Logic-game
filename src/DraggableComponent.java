import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *@ClassName DraggableComponent
 *@Description GamePanel is the panel for our main game program.
 * It integrates chessboard, components(railways) and trains,
 * and their corresponding methods and listeners.
 *@Author Yang Baihan, Guan Zhou, Li Xiao, Chen Xinrui, He Zhicheng, Xu Yulun
 *@DATE 2023/06/01
 *@Version 2.0
 */
public class DraggableComponent extends JLabel {
    private int locX, locY, type = -1, degree = 0;

    private Point originalPos;

    private Point currentPos;

    /**
     * Gets component's type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets component's type.
     *
     * @param type the type
     * @return void
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets location's x coordinate.
     *
     * @return the location's x coordinate
     */
    public int getLocX() {
        return locX;
    }

    /**
     * Gets location's y coordinate.
     *
     * @return the location's y coordinate
     */
    public int getLocY() {
        return locY;
    }

    /**
     * Sets the component's location.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setLoc(int x, int y) {
        locX = x;
        locY = y;
    }

    /**
     * Gets original position of the component.
     *
     * @return the original position of the component
     */
    public Point getOriginalPos() {
        return originalPos;
    }

    /**
     * Sets original position of the component.
     *
     * @param originalPos the original position of the component
     */
    public void setOriginalPos(Point originalPos) {
        this.originalPos = originalPos;
    }

    /**
     * Gets current position of the component.
     *
     * @return the current position of the component
     */
    public Point getCurrentPos() {
        return currentPos;
    }

    /**
     * Sets current position of the component.
     *
     * @param currentPos the current position of the component
     */
    public void setCurrentPos(Point currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * Change the corresponding module in the Logic class when the component is rotated.
     */
    public void twist() {
        try {
            Logic.getModule(locX, locY).twist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotate the image of the component.
     *
     * @param reset true: set it back to original look  false: rotate the image by 45 degree
     */
    public void rotate(boolean reset) {
        Image originalImage = ((ImageIcon) getIcon()).getImage();

        BufferedImage rotatedImage = new BufferedImage(
                originalImage.getWidth(null),
                originalImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = rotatedImage.createGraphics();
        double angle = reset ? Math.toRadians(-degree) : Math.toRadians(45);
        degree = reset ? 0 : degree + 45;

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        g2d.setTransform(transform);

        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        setIcon(new ImageIcon(rotatedImage));
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified
     * text, image, and horizontal alignment.
     * The label is centered vertically in its display area.
     * The text is on the trailing edge of the image.
     *
     * @param text                The text to be displayed by the label.
     * @param icon                The image to be displayed by the label.
     * @param horizontalAlignment One of the following constants                            defined in <code>SwingConstants</code>:                            <code>LEFT</code>,                            <code>CENTER</code>,                            <code>RIGHT</code>,                            <code>LEADING</code> or                            <code>TRAILING</code>.
     */
    public DraggableComponent(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified
     * text and horizontal alignment.
     * The label is centered vertically in its display area.
     *
     * @param text                The text to be displayed by the label.
     * @param horizontalAlignment One of the following constants                            defined in <code>SwingConstants</code>:                            <code>LEFT</code>,                            <code>CENTER</code>,                            <code>RIGHT</code>,                            <code>LEADING</code> or                            <code>TRAILING</code>.
     */
    public DraggableComponent(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified text.
     * The label is aligned against the leading edge of its display area,
     * and centered vertically.
     *
     * @param text The text to be displayed by the label.
     */
    public DraggableComponent(String text) {
        super(text);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified
     * image and horizontal alignment.
     * The label is centered vertically in its display area.
     *
     * @param image               The image to be displayed by the label.
     * @param horizontalAlignment One of the following constants                            defined in <code>SwingConstants</code>:                            <code>LEFT</code>,                            <code>CENTER</code>,                            <code>RIGHT</code>,                            <code>LEADING</code> or                            <code>TRAILING</code>.
     */
    public DraggableComponent(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified image.
     * The label is centered vertically and horizontally
     * in its display area.
     *
     * @param image The image to be displayed by the label.
     */
    public DraggableComponent(Icon image) {
        super(image);
        setLoc(-1, -1);
    }

    /**
     * Creates a <code>JLabel</code> instance with
     * no image and with an empty string for the title.
     * The label is centered vertically
     * in its display area.
     * The label's contents, once set, will be displayed on the leading edge
     * of the label's display area.
     */
    public DraggableComponent() {
    }
}
