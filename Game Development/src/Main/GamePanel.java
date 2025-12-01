package Main;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Entity.Entity;
import Entity.Player;
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
    public GameUI ui = new GameUI(this);
    public ClueTrackerUI clueTrackerUI;

    // -------------------- POPUP --------------------
    public Popup popup;

    // -------------------- GAME STATES --------------------
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public int gameState = playState;

    public int currentNPC = -1;

    // -------------------- CONSTRUCTOR --------------------
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Initialize popup (loads Intro1.png)
        popup = new Popup(screenWidth, screenHeight, "/MurderRoomMaps/Intro1.png");

        // MOUSE LISTENERS FOR POPUP
        MouseInputAdapter mouseHandler = new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // Forward click to ClueTrackerUI first if visible (UI drawn by GamePanel)
                if (clueTrackerUI != null && clueTrackerUI.isVisible()) {
                    boolean handled = clueTrackerUI.handleClick(e.getX(), e.getY());
                    if (handled) return;
                }

                if (popup != null) {
                    popup.handleClick(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                if (popup != null) {
                    popup.handleHover(e.getX(), e.getY());
                }
            }
        };

        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        
        // Initialize ClueTrackerUI
        clueTrackerUI = new ClueTrackerUI(this);
    }

    // -------------------- GAME SETUP --------------------
    public void SetUpGame() {
        // Initialize gameplay audio
        AudioPlayer.getInstance().playMusic("/sounds/01 - buffy - old fashion - intro.wav");
        
        set.setObjects();
        set.setNPC();
        
        // Register all NPCs in the ClueTracker (use class name as identifier)
        ClueTracker tracker = ClueTracker.getInstance();
        for (Entity npc : npc) {
            if (npc != null) {
                String npcName = npc.getClass().getSimpleName();
                tracker.registerNPC(npcName);
            }
        }
        // Assign random professions to registered NPCs for this game start
        tracker.assignRandomProfessions();
        
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

        // Update popup FIRST
        if (popup != null) {
            popup.update();
        }

        // Game updates
        if (gameState == playState) {
            player.update();

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

        // Draw tiles
        tileM.draw(g2);

        // Draw objects
        for (SuperObject o : obj) {
            if (o != null) o.Draw(g2, this);
        }

        // Draw NPCs
        for (Entity n : npc) {
            if (n != null) n.draw(g2);
        }

        // Draw player
        player.draw(g2);

        // Draw UI
        ui.draw(g2);

        // -------------------- DRAW CLUE TRACKER --------------------
        if (clueTrackerUI != null) {
            clueTrackerUI.draw(g2);
        }

        // -------------------- DRAW POPUP LAST --------------------
        if (popup != null) {
            popup.draw(g2);
        }

        g2.dispose();
    }
}
