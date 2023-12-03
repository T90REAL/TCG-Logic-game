

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


/**
 *@ClassName Logic
 *@Description the type of class that determines the logic of the game
 *and their corresponding methods, utilizing methods
 *init(), checkPass(), passTrain(), setModule(), resetModule(), clearModule(),
 *getModule(), Module(), twist(), getRoute(), getRoutes(), getType(), getComponent()
 *@Author Yang Baihan, Guan Zhou, Li Xiao, Chen Xinrui, He Zhicheng, Xu Yulun
 *@DATE 2023/06/01
 *@Version 2.0
 */
public class Logic {
    /**
     * The constant NORTH.
     */
    public static final int NORTH = 0;
    /**
     * The constant SOUTH.
     */
    public static final int SOUTH = 4;
    /**
     * The constant WEST.
     */
    public static final int WEST = 6;
    /**
     * The constant EAST.
     */
    public static final int EAST = 2;
    /**
     * The constant NORTHWEST.
     */
    public static final int NORTHWEST = 7;
    /**
     * The constant NORTHEAST.
     */
    public static final int NORTHEAST = 1;
    /**
     * The constant SOUTHWEST.
     */
    public static final int SOUTHWEST = 5;
    /**
     * The constant SOUTHEAST.
     */
    public static final int SOUTHEAST = 3;

    /**
     * The constant TYPE_START.
     */
    public static final int TYPE_START = 0;
    /**
     * The constant TYPE_END.
     */
    public static final int TYPE_END = 1;
    /**
     * The constant TYPE_45.
     */
    public static final int TYPE_45 = 2;
    /**
     * The constant TYPE_90.
     */
    public static final int TYPE_90 = 3;
    /**
     * The constant TYPE_180.
     */
    public static final int TYPE_180 = 4;
    /**
     * The constant TYPE_EMPTY.
     */
    public static final int TYPE_EMPTY = -1;
    /**
     * The constant TYPE_BUILDER.
     */
    public static final int TYPE_BUILDER = -2;
    /**
     * The constant TYPE_SHOVEL.
     */
    public static final int TYPE_SHOVEL = -10;
    /**
     * The constant DX.
     */
    public static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    /**
     * The constant DY.
     */
    public static final int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};
    /**
     * The constant flag.
     */
    private static int flag=0;
    private static Module[][] modules = new Module[6][6];

    /**
     * Initiate module's set information.
     */
    public static void init() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                modules[i][j] = new Module(TYPE_EMPTY);
            }
        }
    }

    /**
     * Check if the path is passable.
     *
     * @throws InterruptedException the interrupted exception
     */
    public static void checkPass() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (modules[i][j].getType() == TYPE_START) {
                    for (int k = 0; k < 8; k++) {
                        if (modules[i][j].getRoute(k) != -1) {
                            if (passTrain(i, j, k)) {
                                JDialog endDialog = new JDialog();
                                endDialog.setTitle("Win");
                                endDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                endDialog.setLocationRelativeTo(null);
                                ImageIcon icon = new ImageIcon("src/source/Win.gif");
                                setFlag(1);
                                JLabel label = new JLabel(icon);
                                endDialog.getContentPane().add(label);
                                endDialog.setSize(500, 300);
                                endDialog.setVisible(true);
                            }
                            else {
                                if (Main.getMainFrame().getGamePanel().getLevelNumber()!=3){
                                    JDialog endDialog = new JDialog();
                                    endDialog.setTitle("Lose");
                                    endDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                    endDialog.setLocationRelativeTo(null);
                                    ImageIcon icon = new ImageIcon("src/source/newflower.gif");
                                    setFlag(0);
                                    JLabel label = new JLabel(icon);
                                    endDialog.getContentPane().add(label);
                                    endDialog.setSize(500, 300);
                                    label.setSize(500,500);
                                    endDialog.setVisible(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Deque<Point> path = new LinkedList<>();
    static MovableComponent car;

    /**
     * Check if the path is passable.
     *
     * @param x    the x
     * @param y    the y
     * @param from the from
     * @return the boolean
     * @throws InterruptedException the interrupted exception
     */
    public static boolean passTrain(int x, int y, int from) throws InterruptedException {

        if (x < 0 || y < 0 || x >= 6 || y >= 6) {
            return false;
        }
        car = GamePanel.get_Car();
        path.add(new Point(x, y));

        if (modules[x][y].getType() == TYPE_END) {
            animation();
            return true;
        }
        int to = modules[x][y].getRoute(from);
        return to != -1 && passTrain(x + DX[to], y + DY[to], (to + 4) % 8);
    }

    public static void animation() {
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!path.isEmpty()) {
                    Point next = path.getFirst();
                    path.removeFirst();
                    car.setLocation(modules[next.x][next.y].component.getLocation());
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    /**
     * Sets module.
     *
     * @param i         the
     * @param j         the j
     * @param type      the type
     * @param component the component
     */
    public static void setModule(int i, int j, int type, DraggableComponent component) {
        modules[i][j] = new Module(type, component);
    }

    /**
     * Reset module.
     *
     * @param i the
     * @param j the j
     * @param x the x
     * @param y the y
     */
    public static void resetModule(int i, int j, int x, int y) {
        modules[i][j] = new Module(getModule(x, y).getType(), getModule(x, y).getRoutes(), getModule(x, y).getComponent());
        clearModule(x, y);
    }

    /**
     * Clear module.
     *
     * @param i the
     * @param j the j
     */
    public static void clearModule(int i, int j) {
        modules[i][j] = new Module(TYPE_EMPTY);
    }

    /**
     * Gets module.
     *
     * @param i the
     * @param j the j
     * @return the module
     */
    public static Module getModule(int i, int j) {
        return modules[i][j];
    }

    public static void main(String[] args) {
        init();
        modules[1][1] = new Module(TYPE_START);
        modules[2][1] = new Module(TYPE_180);
        modules[3][1] = new Module(TYPE_90);
        modules[3][2] = new Module(TYPE_180);
        modules[3][3] = new Module(TYPE_45);
        for (int i = 1; i <= 6; i++) {
            modules[3][3].twist();
        }
        modules[4][4] = new Module(TYPE_END);
        try {
            checkPass();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void display() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(modules[i][j].getType() + " ");
            }
            System.out.println("");
        }
    }

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        Logic.flag = flag;
    }

    /**
     * The type Module.
     */
    static class Module {
        /**
         * The Type and TwistCount.
         */
        int type, twistCnt = 0;
        /**
         * The Routes.
         */
        int[] routes = new int[8];
        /**
         * The Component.
         */
        DraggableComponent component = null;

        /**
         * Instantiates a new Module.
         *
         * @param type the type
         */
        public Module(int type) {
            this.type = type;
            Arrays.fill(routes, -1);
            switch (type) {
                case TYPE_START:
                    routes[NORTH] = SOUTH;
                    break;
                case TYPE_45:
                    routes[SOUTH] = NORTHEAST;
                    routes[NORTHEAST] = SOUTH;
                    break;
                case TYPE_90:
                    routes[NORTH] = WEST;
                    routes[WEST] = NORTH;
                    routes[SOUTH] = EAST;
                    routes[EAST] = SOUTH;
                    break;
                case TYPE_180:
                    routes[NORTH] = SOUTH;
                    routes[SOUTH] = NORTH;
                    routes[WEST] = EAST;
                    routes[EAST] = WEST;
                    break;
            }
            component = null;
        }
        /**
         * Instantiates a new Module.
         *
         * @param type      the type
         * @param component the component
         */
        public Module(int type, DraggableComponent component) {
            this(type);
            this.component = component;
        }
        /**
         * Instantiates a new Module.
         *
         * @param type      the type
         * @param route     the route
         * @param component the component
         */
        public Module(int type, int[] route, DraggableComponent component) {
            this.type = type;
            this.routes = route;
            this.component = component;
        }
        /**
         * Twist the routes.
         */
        public void twist() {
            int[] modifiedRoute = new int[8];
            for (int i = 0; i < 8; i++) {
                modifiedRoute[i] = routes[(i + 7) % 8] >= 0 ? (routes[(i + 7) % 8] + 1) % 8 : -1;
            }
            routes = modifiedRoute;
            twistCnt++;
        }
        /**
         * Gets route.
         *
         * @param direction the direction
         * @return the route
         */
        public int getRoute(int direction) {
            return routes[direction];
        }
        /**
         * Get routes int [ ].
         *
         * @return the int [ ]
         */
        public int[] getRoutes() {
            return routes;
        }
        /**
         * Sets type.
         *
         * @param type the type
         */
        public void setType(int type) {
            this.type = type;
        }
        /**
         * Gets type.
         *
         * @return the type
         */
        public int getType() {
            return type;
        }
        /**
         * Gets component.
         *
         * @return the component
         */
        public DraggableComponent getComponent() {
            return component;
        }
        /**
         * Sets component.
         *
         * @param component the component
         */
        public void setComponent(DraggableComponent component) {
            this.component = component;
        }
    }
}
