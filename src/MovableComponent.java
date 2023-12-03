import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *@ClassName MovableComponent
 *@Description Movable component refer to the moving objects, namely cars/trains.
 * This class provide methods for moving a train by storing the nodes
 * it is going to pass. External classes should utilise setMoveList()
 * and useMoveList() methods to input nodes and run the train.
 *@Author Yang Baihan, Guan Zhou, Li Xiao, Chen Xinrui, He Zhicheng, Xu Yulun
 *@DATE 2023/06/01
 *@Version 2.0
 */

public class MovableComponent extends JLabel {
    /**
     * The Icon.
     */
    ImageIcon icon;
    private int posX, posY, speed=5;
    private double angle;
    private ArrayList<Point> moveList;

    /**
     * Use move list.
     */
    public void useMoveList() {
        for (int i = 0; i < moveList.size(); i++) {
            loadMove(i);
        }
    }

    /**
     * Load move.
     *
     * @param index the index
     */
    public void loadMove(int index) {
        Point target = moveList.get(index);
        double angle;
        angle = Math.abs(target.y - posY) <= 10 ? (target.x > posX ? 90 : 270) : (target.y > posY ? 180 : 0);
        if (Math.abs(target.x - posX) > 10) {
            angle = Math.toDegrees(Math.atan2((target.x - posX), -(target.y - posY)));
        }
        setAngle(angle);
        moveTo(target.x, target.y);
    }

    /**
     * Move to.
     *
     * @param x the x
     * @param y the y
     */
    public void moveTo(int x, int y) {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dx = x - posX, dy = y - posY;
                double dis = Math.sqrt(dx * dx + dy * dy);
                if (dis <= speed) {
                    ((Timer) e.getSource()).stop();
                } else {
                    double ratio = speed / dis;
                    double stepX = dx * ratio;
                    double stepY = dy * ratio;
                    posX += stepX;
                    posY += stepY;
                    repaint();
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = (ImageIcon) getIcon();
        Image image = icon.getImage();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(posX, posY);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawImage(image, -50, -50, this);
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-posX, -posY);
    }

    /**
     * Instantiates a new Movable component.
     *
     * @param image the image
     */
    public MovableComponent(Icon image) {
        super(image);
        posX = posY = 50;
        angle = 0;
    }

    /**
     * Sets pos.
     *
     * @param x the x
     * @param y the y
     */
    public void setPos(int x, int y) {
        posX = x;
        posY = y;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Sets angle.
     *
     * @param angle the angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Sets move list.
     *
     * @param moveList the move list
     */
    public void setMoveList(ArrayList<Point> moveList) {
        this.moveList = moveList;
    }
}
