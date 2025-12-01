package Main;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Entity.Entity;
import Entity.Player;
import HUD.HUDManager;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // -------------------- TILE SETTINGS --------------------
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    // -------------------- SCREEN SETTINGS --------------------
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth = screen.width;
    public int screenHeight = screen.height;

    public final int maxScreenCol = screenWidth / tileSize;
    public final int maxScreenRow = screenHeight / tileSize;

    // -------------------- WORLD SETTINGS --------------------
    public final int maxWorldCol = 65;
    public final int maxWorldRow = 77;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // -------------------- GAME LOOP --------------------
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public collisionChecker Checker = new collisionChecker(this);
    public AssetSetter set = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[100];
    public Entity npc[] = new Entity[32];
    public HUDManager hudUI = new HUDManager(this);
    public EventHandler eHandler = new EventHandler(this);

    // -------------------- POPUP --------------------
    public Popup popup;

    // -------------------- GAME STATES --------------------
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public int gameState = playState;
    public int mouseX, mouseY;
    public boolean mouseClicked = false;
    public int currentNPC = -1;

    // -------------------- CONSTRUCTOR --------------------
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Initial popup on startup
        popup = new Popup(screenWidth, screenHeight, "/MurderRoomMaps/Intro1.png");

        // -------------------- FIXED MOUSE LISTENER --------------------
        MouseInputAdapter mouseHandler = new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {

                // Save click
                mouseX = e.getX();
                mouseY = e.getY();
                mouseClicked = true;

                // Popup handle
                if (popup != null) {
                    popup.handleClick(mouseX, mouseY);
                }

                // Click detection for items in "items" package
                for (int i = 0; i < obj.length; i++) {
                    SuperObject o = obj[i];

                    if (o != null) {
                        Package p = o.getClass().getPackage();
                        if (p != null && "items".equals(p.getName())) {

                            int objScreenX = o.worldX - player.worldX + player.screenX;
                            int objScreenY = o.worldY - player.worldY + player.screenY;

                            if (mouseX >= objScreenX && mouseX <= objScreenX + o.width &&
                                mouseY >= objScreenY && mouseY <= objScreenY + o.height) {

                                obj[i] = null;  // pick up item
                                repaint();
                                break;
                            }
                        }
                    }
                }
            }
        };

        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    // -------------------- GAME SETUP --------------------
    public void SetUpGame() {
        set.setObjects();
        set.setNPC();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // -------------------- GAME LOOP --------------------
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // -------------------- UPDATE --------------------
    public void update() {

        // Update popup first
        if (popup != null) {
            popup.update();
        }

        // Game logic
        if (gameState == playState) {
            player.update();
            eHandler.checkEvent();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) npc[i].update();
            }
        }
    }

    // -------------------- RENDER --------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Tiles
        tileM.draw(g2);

        // Objects
        for (SuperObject o : obj) {
            if (o != null) o.Draw(g2, this);
        }

        // NPCs
        for (Entity n : npc) {
            if (n != null) n.draw(g2);
        }

        // Player
        player.draw(g2);

        // HUD/UI
        hudUI.draw(g2);

        // Popup drawn last
        if (popup != null) {
            popup.draw(g2);
        }

        g2.dispose();
    }
}
