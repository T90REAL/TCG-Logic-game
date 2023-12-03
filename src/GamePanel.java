import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @ClassName GamePanel
 * @Description GamePanel is the panel for our main game program.
 * It integrates chessboard, components(railways) and trains,
 * and their corresponding methods and listeners.
 * @Author Li Xiao, Guan Zhou, Yang Baihan, Chen Xinrui, He Zhicheng, Xu Yulun
 * @DATE 2023/06/01
 * @Version 2.0
 */
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, Cloneable {
    private JLabel level1Label;
    private JLabel emplevelLabel;
    private JLabel level1ssLabel;
    private JLabel level1sssLabel;
    private JLabel bananaLabel;
    private JLabel crossLabel;
    private JLabel railLabel;
    private JLabel startPointLabel;
    private JLabel endPointLabel;
    private JLabel shovelLabel;
    private JLabel Edge_left;
    private JLabel Edge_right;
    private JLabel ReturnLabel;
    private JLabel AboutUsLabel;
    private JLabel HelpUsLabel;
    private JLabel SelectLabel;
    private JLabel BackLabel;
    private JLabel level1;
    private JLabel level2;
    private JLabel level3;
    private JLabel level4;
    private int levelNumber=0;
    private DraggableComponent Level;
    private DraggableComponent gameWin;
    private DraggableComponent Banana;
    private DraggableComponent Cross;
    private DraggableComponent Double;
    private DraggableComponent Start;
    private DraggableComponent End;
    private DraggableComponent Pick;
    private DraggableComponent Shovel;
    private static MovableComponent Car;

    /**
     * The Select icon selects corresponding levels.
     */
    ImageIcon SelectIcon;
    /**
     * The Return icon return to the start panel.
     */
    ImageIcon ReturnIcon;
    /**
     * The Right icon moves to the right panel.
     */
    ImageIcon RightIcon;
    /**
     * The Left icon moves to the left panel.
     */
    ImageIcon LeftIcon;
    /**
     * The Pick icon stands for the pickaxe.
     */
    ImageIcon PickIcon;
    /**
     * The Backboard icon is the ground page.
     */
    ImageIcon BackboardIcon;
    /**
     * The Builder icon decide whether the road is available.
     */
    ImageIcon BuilderIcon;
    /**
     * The Flower icon is an ornament.
     */
    ImageIcon FlowerIcon;
    /**
     * The Rock icon is an ornament.
     */
    ImageIcon RockIcon;
    /**
     * The About us icon performs the information of the team.
     */
    ImageIcon AboutUsIcon;
    /**
     * The Corner 1 icon.
     */
    ImageIcon Corner1Icon;
    /**
     * The Corner 2 icon.
     */
    ImageIcon Corner2Icon;
    /**
     * The Corner 3 icon.
     */
    ImageIcon Corner3Icon;
    /**
     * The Corner 4 icon.
     */
    ImageIcon Corner4Icon;
    /**
     * The Edge 1 icon.
     */
    ImageIcon Edge1Icon;
    /**
     * The Edge 2 icon.
     */
    ImageIcon Edge2Icon;
    /**
     * The Edge 3 icon.
     */
    ImageIcon Edge3Icon;
    /**
     * The Edge 4 icon.
     */
    ImageIcon Edge4Icon;
    /**
     * The Level 1 s icon.
     */
    ImageIcon level1Icon;
    ImageIcon emptylevelIcon;
    /**
     * The Level 1 ss icon.
     */
    ImageIcon level1ssIcon;
    /**
     * The Level 1 sss icon.
     */
    ImageIcon level1sssIcon;
    ImageIcon level2Icon;
    ImageIcon level2sssIcon;
    ImageIcon level3Icon;
    ImageIcon level3sssIcon;
    ImageIcon level4Icon;
    /**
     * The Banana icon, which turns the road in 45 degrees.
     */
    ImageIcon bananaIcon;
    /**
     * The Cross icon, which turns the road in 90 degrees.
     */
    ImageIcon crossIcon;
    /**
     * The Rail icon, which turns the road in 45 degrees.
     */
    ImageIcon railIcon;
    /**
     * The Shovel icon, which removes the block.
     */
    ImageIcon shovelIcon;
    /**
     * The Base icon, which is the basement of the block.
     */
    ImageIcon BaseIcon;
    /**
     * The Start point icon.
     */
    ImageIcon startPointIcon;
    /**
     * The End point icon.
     */
    ImageIcon endPointIcon;
    /**
     * The Help icon.
     */
    ImageIcon HelpIcon;
    /**
     * The Scaled icon return.
     */
    ImageIcon scaledIcon_Return;
    /**
     * The Scaled icon left.
     */
    ImageIcon scaledIcon_left;
    /**
     * The Scaled icon right.
     */
    ImageIcon scaledIcon_right;

    /**
     * The Current page.
     */
    int Current_Page = 0;

    private Image boardImage;
    /**
     * The Image icon width.
     */
    static final int imageIconWidth = 80;
    /**
     * The Image icon height.
     */
    static final int imageIconHeight = 80;
    private JLabel[][] chess = new JLabel[6][6];
    private JLabel[] RockSide_Ver = new JLabel[10];
    private JLabel[] RockSide_Hor = new JLabel[18];
    private JLabel[][] Edges = new JLabel[4][8];
    private DraggableComponent[] Above_item = new DraggableComponent[38];
    private DraggableComponent[][] Item = new DraggableComponent[2][10];
    private JLabel Base[] = new JLabel[10];

    /**
     * Gets chess with its coordinates.
     *
     * @param x the x
     * @param y the y
     * @return the chess
     */
    public JLabel getChess(int x, int y) {
        return chess[x][y];
    }

    /**
     * Spawn draggable component according to a specific procedure.
     *
     * @param originalIcon the original icon
     * @param x            the x
     * @param y            the y
     * @param labelSize    the label size
     * @param type         the type
     * @param l1           the l 1
     * @param l2           the l 2
     * @param panel        the panel
     * @return the draggable component
     */
    public DraggableComponent spawnComponent(ImageIcon originalIcon, int x, int y, int labelSize, int type, MouseListener l1, MouseMotionListener l2, GamePanel panel) {
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(labelSize, labelSize, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        DraggableComponent component = new DraggableComponent(scaledIcon);
        component.setBounds(x, y, labelSize, labelSize);
        component.addMouseListener(l1);
        component.addMouseMotionListener(l2);
        component.setType(type);

        component.setOriginalPos(component.getLocation());
        component.setCurrentPos(component.getLocation());
        panel.add(component);
        return component;
    }

    public ImageIcon getEmptylevelIcon() {
        return emptylevelIcon;
    }

    public ImageIcon getLevel1sssIcon() {
        return level1sssIcon;
    }

    public ImageIcon getLevel2sssIcon() {
        return level2sssIcon;
    }

    public ImageIcon getLevel3sssIcon() {
        return level3sssIcon;
    }

    /**
     * Initializes all the ImageIcon and all the JLabel variable.
     * Adds the ChangeIcon, the Return Label, all the type of the cards, the cards base, the background, the grass edge, the Rock-sides, the 4 Edges.
     * Saves all the variable into array.
     * Adds the Select Panel, the AboutUs Panel, the Help Panel.
     */
    public GamePanel() {
        playCustomSound("src/source/Background Music.wav");
        ImageIcon CarIcon = new ImageIcon("src/source/minecart.png");
        Image originalImage = CarIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        Car = new MovableComponent(scaledIcon);
        Car.setBounds(300, 300, 100, 100);

        this.setLocation(0, 0);
        this.setLayout(null);
        this.setSize(1750, 1059);
        this.setBackground(Color.white);
        boardImage = new ImageIcon("src/source/background.png").getImage();

        {
            SelectIcon = new ImageIcon("src/source/Newlevel_list.png");
            ReturnIcon = new ImageIcon("src/source/LeftIcon.png");
            LeftIcon = new ImageIcon("src/source/RightIcon.png");
            RightIcon = new ImageIcon("src/source/LeftIcon.png");
            PickIcon = new ImageIcon("src/source/Pickaxe.png");
            BackboardIcon = new ImageIcon("src/source/board.png");
            BuilderIcon = new ImageIcon("src/source/Button.png");
            HelpIcon = new ImageIcon("src/source/guide.png");
            FlowerIcon = new ImageIcon("src/source/flower.gif");
            RockIcon = new ImageIcon("src/source/Rock.png");
            AboutUsIcon = new ImageIcon("src/source/aboutUs.png");
            level1Icon = new ImageIcon("src/source/level1.png");
            emptylevelIcon = new ImageIcon("src/source/emptyLevel.png");
            level1ssIcon = new ImageIcon("src/source/level1ss.png");
            level1sssIcon = new ImageIcon("src/source/level1sss.png");
            level2Icon = new ImageIcon("src/source/level2.png");
            level2sssIcon = new ImageIcon("src/source/level2sss.png");
            level3Icon = new ImageIcon("src/source/level3.png");
            level3sssIcon = new ImageIcon("src/source/level3sss.png");
            level4Icon = new ImageIcon("src/source/level4.png");
            bananaIcon = new ImageIcon("src/source/banana.png");
            crossIcon = new ImageIcon("src/source/cross.png");
            railIcon = new ImageIcon("src/source/rail.png");
            endPointIcon = new ImageIcon("src/source/end.png");
            startPointIcon = new ImageIcon("src/source/start.png");
            shovelIcon = new ImageIcon("src/source/shovel.png");
            Corner1Icon = new ImageIcon("src/source/11.png");
            Corner2Icon = new ImageIcon("src/source/22.png");
            Corner3Icon = new ImageIcon("src/source/33.png");
            Corner4Icon = new ImageIcon("src/source/44.png");
            Edge1Icon = new ImageIcon("src/source/grass.png");
            Edge2Icon = new ImageIcon("src/source/grass2.png");
            Edge3Icon = new ImageIcon("src/source/grass3.png");
            Edge4Icon = new ImageIcon("src/source/grass4.png");
            BaseIcon = new ImageIcon("src/source/base.png");

            level1Label = new JLabel(null, level1Icon, SwingConstants.LEADING);
            emplevelLabel = new JLabel(null, emptylevelIcon, SwingConstants.LEADING);
            level1ssLabel = new JLabel(null, level1ssIcon, SwingConstants.LEADING);
            level1sssLabel = new JLabel(null, level1sssIcon, SwingConstants.LEADING);
            bananaLabel = new JLabel(null, bananaIcon, SwingConstants.LEADING);
            crossLabel = new JLabel(null, crossIcon, SwingConstants.LEADING);
            railLabel = new JLabel(null, railIcon, SwingConstants.LEADING);
            startPointLabel = new JLabel(null, startPointIcon, SwingConstants.LEADING);
            endPointLabel = new JLabel(null, endPointIcon, SwingConstants.LEADING);
            shovelLabel = new JLabel(null, shovelIcon, SwingConstants.LEADING);


            level1Label.setSize(80, 80);
            level1Label.setVisible(true);

            bananaLabel.setSize(80, 80);
            bananaLabel.setVisible(true);
            crossLabel.setSize(80, 80);
            crossLabel.setVisible(true);
            railLabel.setSize(80, 80);
            railLabel.setVisible(true);
            startPointLabel.setSize(80, 80);
            startPointLabel.setVisible(true);
            endPointLabel.setSize(80, 80);
            endPointLabel.setVisible(true);
            shovelLabel.setSize(80, 80);
            shovelLabel.setVisible(true);

            // item
            {
                // add the Return Label
                ImageIcon originalIcon = ReturnIcon;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                scaledIcon_Return = new ImageIcon(scaledImage);
                ReturnLabel = new JLabel(scaledIcon_Return);
                ReturnLabel.setBounds(0, 0, 100, 100);
                repaint();
                this.add(ReturnLabel);
                ReturnLabel.addMouseListener(this);

                // base
                int x = 0, y = 0;
                int labelSize = 100;
                int cur = 0;

                int ind = 0;
                // cards
                for (int i = 0; i < 6; i++) {
                    x = 0;
                    y = 0;
                    Level = spawnComponent(emptylevelIcon, x += 100, y, labelSize, -1, this, this, this);
                    Banana = spawnComponent(bananaIcon, x += 100, y, labelSize, Logic.TYPE_45, this, this, this);
                    Cross = spawnComponent(crossIcon, x += 100, y, labelSize, Logic.TYPE_180, this, this, this);
                    Double = spawnComponent(railIcon, x += 100, y, labelSize, Logic.TYPE_90, this, this, this);
                    End = spawnComponent(endPointIcon, x += 100, y, labelSize, Logic.TYPE_END, this, this, this);
                    Start = spawnComponent(startPointIcon, x += 100, y, labelSize, Logic.TYPE_START, this, this, this);
                    Above_item[ind++] = Start;
                    Above_item[ind++] = End;
                    Above_item[ind++] = Double;
                    Above_item[ind++] = Cross;
                    Above_item[ind++] = Banana;
                    Above_item[ind++] = Level;
                    if (i == 0) {
                        Pick = spawnComponent(PickIcon, x += 100, y + 10, 100, Logic.TYPE_BUILDER, this, this, this);
                        Shovel = spawnComponent(shovelIcon, x += 100, y, labelSize, Logic.TYPE_SHOVEL, this, this, this);
                        Above_item[ind++] = Pick;
                        Above_item[ind++] = Shovel;

                    } else {
                        x += 100;
                    }
                }
                for (int i = 0; i < 8; i++) {
                    originalIcon = new ImageIcon("src/source/Base.png");
                    originalImage = originalIcon.getImage();
                    scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    scaledIcon = new ImageIcon(scaledImage);
                    Base[i] = new JLabel(scaledIcon);
                    Base[i].setBounds((i + 1) * labelSize, y, labelSize, labelSize);
                    this.add(Base[i]);
                }
            }
        }
        // add the background
        {
            ImageIcon originalIcon = new ImageIcon("src/source/kakou.png");
            originalImage = originalIcon.getImage();
            scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);

            int labelSize = 100;
            int gap = 0;
            for (int i = 2; i <= 7; i++) {
                for (int j = 2; j <= 7; j++) {
                    int x = j * (labelSize + gap);
                    int y = i * (labelSize + gap);

                    chess[i - 2][j - 2] = new JLabel(scaledIcon);
                    chess[i - 2][j - 2].setBounds(x, y, labelSize, labelSize);
                    this.add(chess[i - 2][j - 2]);
                }
            }
        }

        // add the edge
        {
            int labelSize = 100;
            ImageIcon originalIcon = Edge1Icon;
            originalImage = originalIcon.getImage();
            scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);

            // c1
            originalIcon = Corner1Icon;
            originalImage = originalIcon.getImage();
            scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);
            JLabel Edge = new JLabel(scaledIcon);

            // Rock-side
            {
                for (int i = 0; i < 10; i++) {
                    originalIcon = RockIcon;
                    originalImage = originalIcon.getImage();
                    scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    scaledIcon = new ImageIcon(scaledImage);
                    Edge = new JLabel(scaledIcon);
                    Edge.setBounds(0, 1 + i * 100, 100, 100);
                    RockSide_Ver[i] = Edge;
                }

                for (int i = 0; i < 10; i++) {
                    this.add(RockSide_Ver[i]);
                }

                for (int i = 0; i < 18; i++) {
                    originalIcon = RockIcon;
                    originalImage = originalIcon.getImage();
                    scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    scaledIcon = new ImageIcon(scaledImage);
                    Edge = new JLabel(scaledIcon);
                    Edge.setBounds(i * 100, 900, 100, 100);
                    RockSide_Hor[i] = Edge;
                }

                for (int i = 0; i < 18; i++) {
                    this.add(RockSide_Hor[i]);
                }
            }

            // ChangeIcon
            {
                originalIcon = RightIcon;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                scaledIcon_right = new ImageIcon(scaledImage);
                Edge_right = new JLabel(scaledIcon_right);
                Edge_right.setBounds(100 * 12, 800, 100, 100);
                repaint();
                this.add(Edge_right);

                originalIcon = LeftIcon;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                scaledIcon_left = new ImageIcon(scaledImage);
                Edge_left = new JLabel(scaledIcon_left);
                Edge_left.setBounds(100 * 13, 800, 100, 100);
                repaint();
                this.add(Edge_left);

                Edge_right.addMouseListener(this);
                Edge_left.addMouseListener(this);
                ReturnLabel.addMouseListener(this);
            }

            // Select
            {
                originalIcon = SelectIcon;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                SelectLabel = new JLabel(scaledIcon);
                SelectLabel.setBounds(100 * 8, -50, 1000, 1000);
                this.add(SelectLabel);
                repaint();

                JLabel backgroundLabel = SelectLabel;
                ImageIcon backgroundImage = SelectIcon;
                backgroundLabel.setIcon(backgroundImage);

                ImageIcon image1 = new ImageIcon("src/source/ship1.png");
                originalIcon = image1;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                level1 = new JLabel(scaledIcon);
                level1.setBounds(230, 300, 100, 100);
                level1.addMouseListener(this);
                backgroundLabel.add(level1);

                ImageIcon image2 = new ImageIcon("src/source/ship2.png");
                originalIcon = image2;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                level2 = new JLabel(scaledIcon);
                level2.setBounds(230, 600, 100, 100);
                level2.addMouseListener(this);
                backgroundLabel.add(level2);

                ImageIcon image3 = new ImageIcon("src/source/ship3.png");
                originalIcon = image3;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                level3 = new JLabel(scaledIcon);
                level3.setBounds(500, 600, 100, 100);
                level3.addMouseListener(this);
                backgroundLabel.add(level3);

                ImageIcon image4 = new ImageIcon("src/source/ship.png");
                originalIcon = image4;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                level4 = new JLabel(scaledIcon);
                level4.setBounds(690, 300, 100, 100);
                level4.addMouseListener(this);
                backgroundLabel.add(level4);
                level4.setOpaque(false);
                level4.setBorder(BorderFactory.createEmptyBorder());
            }

            // AboutUs
            {
                originalIcon = AboutUsIcon;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(800, 900, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                AboutUsLabel = new JLabel(scaledIcon);
                AboutUsLabel.setBounds(100 * 8 + 50, -50, 900, 1000);
                this.add(AboutUsLabel);
                repaint();
            }
            // Help
            {
                originalIcon = HelpIcon;
                originalImage = originalIcon.getImage();
                scaledImage = originalImage.getScaledInstance(1200, 900, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                HelpUsLabel = new JLabel(scaledIcon);
                HelpUsLabel.setBounds(100 * 8 + 50, -50, 900, 1000);
                repaint();
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 2; j < 8; j++) {
                    if (i == 0) {
                        originalIcon = Edge1Icon;
                        originalImage = originalIcon.getImage();
                        scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        scaledIcon = new ImageIcon(scaledImage);
                        Edge = new JLabel(scaledIcon);
                        Edge.setBounds(j * labelSize, 100, labelSize, labelSize);
                    } else if (i == 1) {
                        originalIcon = Edge2Icon;
                        originalImage = originalIcon.getImage();
                        scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        scaledIcon = new ImageIcon(scaledImage);
                        Edge = new JLabel(scaledIcon);
                        Edge.setBounds(100 * 8, j * labelSize, labelSize, labelSize);
                    } else if (i == 2) {
                        originalIcon = Edge3Icon;
                        originalImage = originalIcon.getImage();
                        scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        scaledIcon = new ImageIcon(scaledImage);
                        Edge = new JLabel(scaledIcon);
                        Edge.setBounds(j * labelSize, 100 * 8, labelSize, labelSize);
                    } else {
                        originalIcon = Edge4Icon;
                        originalImage = originalIcon.getImage();
                        scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        scaledIcon = new ImageIcon(scaledImage);
                        Edge = new JLabel(scaledIcon);
                        Edge.setBounds(100, j * labelSize, labelSize, labelSize);
                    }
                    Edges[i][j - 2] = Edge;
                    this.add(Edges[i][j - 2]);
                }
            }
            // Backboard
            originalIcon = BackboardIcon;
            originalImage = originalIcon.getImage();
            scaledImage = originalImage.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);
            BackLabel = new JLabel(scaledIcon);
            BackLabel.setBounds(50, 0, 900, 1000);
            this.add(BackLabel);
        }

        this.setVisible(true);

        this.setFocusable(true);
    }

    /**
     * Paints the components.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Sets icon into frames.
     *
     * @param label
     */
    private void setIconToFrame(JLabel label) {
        ImageIcon icon = (ImageIcon) label.getIcon();
        icon.setImage(icon.getImage().getScaledInstance(imageIconWidth, imageIconHeight, Image.SCALE_DEFAULT));
        label.setIcon(icon);
        label.setFont(new Font(null, Font.BOLD, 22));
        label.setEnabled(false);
        label.addMouseListener(this);
        this.add(label);
    }

    private static DraggableComponent endReference;
    private static DraggableComponent startReference;
    /**
     * Clicks the mouse to trigger some events.
     *
     * @param e
     */
    private DraggableComponent Levels[] = new DraggableComponent[3];
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof DraggableComponent) {
            DraggableComponent component = (DraggableComponent) e.getSource();
            if (component.getType() == Logic.TYPE_BUILDER) {
                try {
                    Logic.checkPass();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (SwingUtilities.isRightMouseButton(e)) {
                if (component.getType() >= 0) {
                    playCustomSound("src/source/Rotate.wav");
                    component.rotate(false);
                    component.twist();
                }

            }
        }

        if (e.getSource().equals(level1)) {
            playCustomSound("src/source/click.wav");
            MovableComponent start = new MovableComponent(startPointIcon);
            DraggableComponent end = new DraggableComponent(endPointIcon);
            levelNumber = 1;
            start.setLocation(200, 200);
            end.setLocation(500, 500);

            Clear_chessboard();
            Car.setLocation(200, 200);
            Car.setSize(100, 100);
            this.add(Car);
            Init_Chessboard();
            placeComponent(3, 3, endReference);
            placeComponent(0,0,startReference);
        }
        if (e.getSource().equals(level2)) {
            playCustomSound("src/source/click.wav");
            MovableComponent start = new MovableComponent(startPointIcon);
            MovableComponent end = new MovableComponent(endPointIcon);
            levelNumber = 2;
            start.setLocation(200, 200);
            end.setLocation(600, 600);

            Clear_chessboard();
            Car.setLocation(200, 200);
            Car.setSize(100, 100);
            this.add(Car);
            Init_Chessboard();
            placeComponent(4, 4, endReference);
            placeComponent(0,0,startReference);
        }
        if (e.getSource().equals(level3)) {
            playCustomSound("src/source/click.wav");
            MovableComponent start = new MovableComponent(startPointIcon);
            MovableComponent end = new MovableComponent(startPointIcon);
            levelNumber = 3;
            start.setLocation(200, 200);
            end.setLocation(700, 700);

            Clear_chessboard();
            Car.setLocation(200, 200);
            Car.setSize(100, 100);
            this.add(Car);
            Init_Chessboard();
            placeComponent(5, 5, endReference);
            placeComponent(0,0,startReference);
        }
        if (e.getSource().equals(level4)) {
            playCustomSound("src/source/click.wav");
            MovableComponent start = new MovableComponent(startPointIcon);
            MovableComponent end = new MovableComponent(startPointIcon);
            levelNumber = 4;

            Clear_chessboard();
            Car.setLocation(300, 300);
            Car.setSize(100, 100);
            this.add(Car);
            Init_Chessboard();
            placeComponent(5, 5, endReference);
            placeComponent(1,1,startReference);
        }

        if (e.getSource().equals(Edge_left) || e.getSource().equals(Edge_right) || e.getSource().equals(ReturnLabel)) {
            playCustomSound("src/source/click.wav");

            if (e.getSource().equals(ReturnLabel)) {
                Clear_chessboard();
                Init_Chessboard();
                Main.getMainFrame().setContentPane(Main.getMainFrame().getStartPanel());
            }

            if (e.getSource().equals(Edge_left)) {
                if (Current_Page != 0) {
                    Current_Page--;
                } else {
                    Current_Page = 2;
                }
            }
            if (e.getSource().equals(Edge_right)) {
                if (Current_Page == 2) {
                    Current_Page = 0;
                } else {
                    Current_Page++;
                }
            }

            if (Current_Page == 0) {
                this.remove(Edge_left);
                this.remove(Edge_right);
                this.remove(HelpUsLabel);
                this.remove(AboutUsLabel);
                repaint();
                this.add(Edge_left);
                this.add(Edge_right);
                this.add(SelectLabel);
            } else if (Current_Page == 1) {
                this.remove(Edge_left);
                this.remove(Edge_right);
                this.remove(AboutUsLabel);
                this.remove(SelectLabel);
                repaint();
                this.add(Edge_left);
                this.add(Edge_right);
                this.add(HelpUsLabel);
                repaint();
            } else {
                this.remove(Edge_left);
                this.remove(Edge_right);
                this.remove(HelpUsLabel);
                this.remove(SelectLabel);
                repaint();
                this.add(Edge_left);
                this.add(Edge_right);
                this.add(AboutUsLabel);
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void placeComponent(int i, int j, DraggableComponent component) {
        component.setLocation(getChess(i, j).getLocation());
        getChess(i, j).setVisible(true);
        playCustomSound("src/source/Put.wav");
        if (component.getLocX() > 0) {
            Logic.resetModule(i, j, component.getLocX(), component.getLocY());
        } else {
            Logic.setModule(i, j, component.getType(), component);
        }
        component.setLoc(i, j);
        component.setCurrentPos(component.getLocation());
    }

    /**
     * Releases the mouse to trigger some events.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof DraggableComponent && ((DraggableComponent) e.getSource()).getType() != -1) {
            DraggableComponent component = (DraggableComponent) e.getSource();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    Rectangle bounds = getChess(i, j).getBounds();
                    bounds.setLocation(getChess(i, j).getLocationOnScreen());
                    if (bounds.contains(e.getLocationOnScreen())) {
                        if (component.getType() >= 0 && Logic.getModule(i, j).getType() == Logic.TYPE_EMPTY) {
                            placeComponent(i, j, component);
                        } else {
                            if (component.getType() == Logic.TYPE_SHOVEL && Logic.getModule(i, j).getType() != Logic.TYPE_EMPTY) {
                                Logic.getModule(i, j).getComponent().setLoc(-1, -1);
                                Logic.getModule(i, j).getComponent().rotate(true);
                                Logic.getModule(i, j).getComponent().setLocation(Logic.getModule(i, j).getComponent().getOriginalPos());
                                Logic.clearModule(i, j);
                                playCustomSound("src/source/Shovel.wav");

                            }
                            component.setLocation(component.getCurrentPos());
                        }
                        return;
                    }
                }
            }
            component.setLocation(component.getCurrentPos());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public static MovableComponent get_Car() {
        return Car;
    }

    /**
     * Gets label data flavor.
     */
    private DataFlavor getLabelDataFlavor() {
        try {
            return new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=javax.swing.JLabel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds mouse listener.
     *
     * @param sourceLabel the source label
     * @param targetLabel the target label
     */
    public void AddMouseListener(JLabel sourceLabel, JLabel targetLabel) {
        sourceLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                sourceLabel.getTransferHandler().exportAsDrag(sourceLabel, e, TransferHandler.COPY);
            }
        });

        targetLabel.setTransferHandler(new TransferHandler() {
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(getLabelDataFlavor());
            }

            public boolean importData(TransferSupport support) {
                try {
                    JLabel source = (JLabel) support.getTransferable().getTransferData(getLabelDataFlavor());
                    targetLabel.setIcon(source.getIcon());
                    targetLabel.setText("");
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        sourceLabel.setTransferHandler(new TransferHandler("icon") {
            public int getSourceActions(JComponent c) {
                return COPY;
            }

            protected Transferable createTransferable(JComponent c) {
                // Create a custom drag image based on the source label's appearance
                BufferedImage dragImage = new BufferedImage(sourceLabel.getWidth(), sourceLabel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = dragImage.createGraphics();
                sourceLabel.paint(g);
                g.dispose();

                setDragImage(dragImage); // Set the custom drag image
                return new Transferable() {
                    public Object getTransferData(DataFlavor flavor) {
                        return sourceLabel;
                    }

                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[]{getLabelDataFlavor()};
                    }

                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor.equals(getLabelDataFlavor());
                    }
                };
            }
        });

        this.add(sourceLabel);
        this.add(targetLabel);

        setVisible(true);
    }

    /**
     * Determines if the label is clickable.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
        }
    }

    /**
     * Uses the mouse to drag components.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() instanceof DraggableComponent) {
            DraggableComponent component = (DraggableComponent) e.getSource();
            if (component.getType() != -1) {
                component.setLocation(component.getLocation().x + e.getX() - 50, component.getLocation().y + e.getY() - 50);
            }
        }
    }

    /**
     * Plays music.
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
    public DraggableComponent getLevel() {
        return Level;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Instantiates a new chessboard.
     */
    private void Init_Chessboard() {
        // Enable the background music
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Logic.setModule(i, j, Logic.TYPE_EMPTY, new DraggableComponent());
            }
        }

        int labelSize = 100;
        int x = 0, y = 0;
        int pos = 0;
        for (int i = 0; i < 6; i++) {
            x = 0;
            y = 0;
            switch (levelNumber) {
                case 0:
                    Level = spawnComponent(emptylevelIcon, x += 100, y, labelSize, -1, this, this, this);
                    break;
                case 1:
                    Level = spawnComponent(level1Icon, x += 100, y, labelSize, -1, this, this, this);
                    break;
                case 2:
                    Level = spawnComponent(level2Icon, x += 100, y, labelSize, -1, this, this, this);
                    break;
                case 3:
                    Level = spawnComponent(level3Icon, x += 100, y, labelSize, -1, this, this, this);
                    break;
                case 4:
                    Level = spawnComponent(level4Icon, x += 100, y, labelSize, -1, this, this, this);
                    break;
            }
            Banana = spawnComponent(bananaIcon, x += 100, y, labelSize, Logic.TYPE_45, this, this, this);
            Cross = spawnComponent(crossIcon, x += 100, y, labelSize, Logic.TYPE_180, this, this, this);
            Double = spawnComponent(railIcon, x += 100, y, labelSize, Logic.TYPE_90, this, this, this);
            End = spawnComponent(endPointIcon, x += 100, y, labelSize, Logic.TYPE_END, this, this, this);
            Start = spawnComponent(startPointIcon, x += 100, y, labelSize, Logic.TYPE_START, this, this, this);
            Above_item[pos++] = Start;
            Above_item[pos++] = End;
            endReference = End;
            startReference = Start;
            Above_item[pos++] = Double;
            Above_item[pos++] = Cross;
            Above_item[pos++] = Banana;
            Above_item[pos++] = Level;
            if (i == 0) {
                Pick = spawnComponent(PickIcon, x += 100, y, 100, Logic.TYPE_BUILDER, this, this, this);
                Shovel = spawnComponent(shovelIcon, x += 100, y, labelSize, Logic.TYPE_SHOVEL, this, this, this);
                Above_item[pos++] = Pick;
                Above_item[pos++] = Shovel;
            } else {
                x += 100;
            }
        }
        for (int i = 0; i < 8; i++) {
            this.add(Base[i]);
            this.repaint();
        }

        for (int i = 2; i <= 7; i++) {
            for (int j = 2; j <= 7; j++) {
                this.add(chess[i - 2][j - 2]);
                this.repaint();
            }
        }

        this.add(BackLabel);
        this.repaint();
    }

    /**
     * Clears the chessboard.
     */
    private void Clear_chessboard() {
        for (int i = 0; i < 38; i++) {
            this.remove(Above_item[i]);
            this.repaint();
        }
        for (int i = 0; i < 8; i++) {
            this.remove(Base[i]);
            this.repaint();
        }

        for (int i = 2; i <= 7; i++) {
            for (int j = 2; j <= 7; j++) {
                this.remove(chess[i - 2][j - 2]);
                this.repaint();
            }
        }
        this.remove(BackLabel);
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
