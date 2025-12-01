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

    // -------------------- UI --------------------
    public HUDManager hudUI = new HUDManager(this);
    public GameUI ui = new GameUI(this);
    public ClueTrackerUI clueTrackerUI;

    public EventHandler eHandler = new EventHandler(this);

    // -------------------- ITEM PICKUP --------------------
    public ItemPickupManager itemPickupManager;

    // -------------------- POPUP --------------------
    public Popup popup;
    private String popupText = null;
    private long popupStartTime = 0;
    private final int POPUP_DURATION = 3000; // 3 seconds

    // -------------------- GAME STATES --------------------
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public int gameState = playState;
    public int mouseX, mouseY;
    public boolean mouseClicked = false;
    public int currentNPC = -1;

    // -------------------- INVENTORY SETTINGS --------------------
    private final int invSlotSize = 40;
    private final int invPadding = 5;
    private final int invCols = 4;

    // -------------------- CONSTRUCTOR --------------------
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Initialize ItemPickupManager
        itemPickupManager = new ItemPickupManager(obj, player);

        // Initialize Popup
        popup = new Popup(screenWidth, screenHeight, "/MurderRoomMaps/Intro1.png");

        // Initialize ClueTrackerUI
        clueTrackerUI = new ClueTrackerUI(this);

        // -------------------- MOUSE HANDLER --------------------
        MouseInputAdapter mouseHandler = new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {

                mouseX = e.getX();
                mouseY = e.getY();
                mouseClicked = true;

                // Priority: ClueTrackerUI > Popup > Item Pickup
                if (clueTrackerUI != null && clueTrackerUI.isVisible()) {
                    boolean handled = clueTrackerUI.handleClick(mouseX, mouseY);
                    if (handled) return;
                }

                if (popup != null) {
                    popup.handleClick(mouseX, mouseY);
                }

                String pickedItem = itemPickupManager.checkPickup(mouseX, mouseY);
                if (pickedItem != null) {
                    popupText = "You picked up a " + pickedItem + ".";
                    popupStartTime = System.currentTimeMillis();
                    repaint();
                }
            }
        };

        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    // -------------------- GAME SETUP --------------------
    public void SetUpGame() {
        // Initialize gameplay audio
        AudioPlayer.getInstance().playMusic("/sounds/01 - buffy - old fashion - intro.wav");

        set.setObjects();
        set.setNPC();

        // Register all NPCs in the ClueTracker
        ClueTracker tracker = ClueTracker.getInstance();
        for (Entity npc : npc) {
            if (npc != null) {
                String npcName = npc.getClass().getSimpleName();
                tracker.registerNPC(npcName);
            }
        }
        tracker.assignRandomProfessions();

        gameState = playState;
    }

    // -------------------- GAME THREAD --------------------
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

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
        if (popup != null) popup.update();

        if (gameState == playState) {
            player.update();
            eHandler.checkEvent();

            for (Entity n : npc) {
                if (n != null) n.update();
            }
        }
    }

    // -------------------- DRAW --------------------
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
        if (hudUI != null) hudUI.draw(g2);
        if (ui != null) ui.draw(g2);

        // ClueTrackerUI
        if (clueTrackerUI != null) clueTrackerUI.draw(g2);

        // Popup (draw last)
        if (popup != null) popup.draw(g2);

        // -------------------- INVENTORY GRID --------------------
        drawInventoryGrid(g2);

        g2.dispose();
    }

    // -------------------- INVENTORY DRAWING --------------------
    private void drawInventoryGrid(Graphics2D g2) {
        int rows = (int) Math.ceil((double) player.inventory.size() / invCols);
        int boxWidth = (invSlotSize + invPadding) * invCols + invPadding;
        int boxHeight = (invSlotSize + invPadding) * rows + invPadding;

        int startX = screenWidth - boxWidth - 20;
        int startY = 20;

        // Background
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(startX, startY, boxWidth, boxHeight, 15, 15);

        // Draw items
        for (int i = 0; i < player.inventory.size(); i++) {
            int row = i / invCols;
            int col = i % invCols;

            int x = startX + invPadding + col * (invSlotSize + invPadding);
            int y = startY + invPadding + row * (invSlotSize + invPadding);

            g2.setColor(Color.white);
            g2.drawRect(x, y, invSlotSize, invSlotSize);

            SuperObject item = player.inventory.get(i);
            if (item.image != null) {
                g2.drawImage(item.image, x + 4, y + 4, invSlotSize - 8, invSlotSize - 8, null);
            }
        }
    }
}
