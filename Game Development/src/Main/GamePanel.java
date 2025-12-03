package Main;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Entity.Entity;
import Entity.Player;
import HUD.HUDManager;
import HUD.HUDObjectives;
import HUD.HUDInvestigate; // added import
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
    public HUDObjectives hudObjectives; // ✅ Added Objectives HUD
    public HUDInvestigate hudInvestigate; // ✅ Investigate HUD (clickable)
    public GameUI ui = new GameUI(this);
    public ClueTrackerUI clueTrackerUI;
    public EventHandler eHandler = new EventHandler(this);

    // -------------------- ITEM PICKUP --------------------
    public ItemPickupManager itemPickupManager;
    public QuestManager questManager;

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
    private final int invCols = 5;   // fixed columns
    private final int invRows = 6;   // fixed rows (5x6 = 30 slots)
    private boolean showInventory = false; // inventory visibility

    // -------------------- BAG HUD --------------------
    public HUD.HUDBag gpBag;

    // -------------------- CONSTRUCTOR --------------------
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Initialize ItemPickupManager
        itemPickupManager = new ItemPickupManager(obj, player);

        // Initialize QuestManager and register sample quests
        questManager = new QuestManager();
        questManager.registerQuest(new Quest("quest_key", "Find Key", "key", 1, false));
        questManager.registerQuest(new Quest("quest_gun", "Find Gun", "Gun", 1, false));
        questManager.registerQuest(new Quest("quest_knife", "Find Knife", "Knife", 2, false));
        questManager.registerQuest(new Quest("quest_handcuffs", "Find Handcuffs", "Handcuffs", 1, false));
        questManager.registerQuest(new Quest("quest_flashlight", "Find flashlight", "flashlight", 1, false));
        questManager.registerQuest(new Quest("quest_watch", "Find watch", "watch", 3, false));
        questManager.registerQuest(new Quest("quest_Notebook", "Find Notebook", "Notebook", 1, false));

        // Initialize Popup
        popup = new Popup(screenWidth, screenHeight, "/MurderRoomMaps/Intro1.png");

        // Initialize ClueTrackerUI
        clueTrackerUI = new ClueTrackerUI(this);

        // Initialize HUDInvestigate (clickable HUD that toggles clue tracker)
        hudInvestigate = new HUDInvestigate(this);

        // Initialize Bag HUD
        gpBag = new HUD.HUDBag(this);

        // Initialize Objectives HUD
        hudObjectives = new HUDObjectives(this);

        // -------------------- MOUSE HANDLER --------------------
        MouseInputAdapter mouseHandler = new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                mouseClicked = true;

                // Check Objectives click first
                if (hudObjectives != null && hudObjectives.isClicked(mouseX, mouseY)) {
                    repaint();
                    return; // stop further clicks
                }

                // Toggle inventory if bag clicked
                if (gpBag != null && gpBag.isClicked(mouseX, mouseY)) {
                    showInventory = !showInventory;
                    return; // stop further actions
                }

                // Check Investigate HUD click (opens/closes Clue Tracker)
                if (hudInvestigate != null && hudInvestigate.isClicked(mouseX, mouseY)) {
                    repaint();
                    return; // stop further actions so click doesn't fall through
                }

                // ClueTrackerUI click (only if visible)
                if (clueTrackerUI != null && clueTrackerUI.isVisible()) {
                    boolean shiftHeld = (e.getModifiersEx() & java.awt.event.InputEvent.SHIFT_DOWN_MASK) != 0;
                    boolean handled = clueTrackerUI.handleClick(mouseX, mouseY, shiftHeld);
                    if (handled) return;
                }

                // Popup click
                if (popup != null) popup.handleClick(mouseX, mouseY);

                // Item pickup
                String pickedItem = itemPickupManager.checkPickup(mouseX, mouseY);
                if (pickedItem != null) {
                    popupText = "You picked up a " + pickedItem + ".";
                    popupStartTime = System.currentTimeMillis();
                    // Update quest progress when item picked
                    if (questManager != null) {
                        questManager.onItemPicked(pickedItem);
                        System.out.println("Quest system: Item picked - " + pickedItem);
                    }
                    repaint();
                }
            }
        };

        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    // -------------------- GAME SETUP --------------------
    public void SetUpGame() {
        AudioPlayer.getInstance().playMusic("/sounds/01 - buffy - old fashion - intro.wav");

        set.setObjects();
        set.setNPC();

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
        if (gpBag != null) gpBag.draw(g2);
        if (hudObjectives != null) hudObjectives.draw(g2); // ✅ Draw Objectives popup
        if (hudInvestigate != null) hudInvestigate.draw(g2); // ✅ Draw Investigate HUD (clickable)
        if (ui != null) ui.draw(g2);

        // ClueTrackerUI
        if (clueTrackerUI != null) clueTrackerUI.draw(g2);

        // Quest Window
        if (questManager != null) questManager.draw(g2, this);

        // Bag HUD
        // Popup (draw last)
        if (popup != null) popup.draw(g2);

        // Inventory grid and label only if toggled
        if (showInventory) drawInventoryGrid(g2);

        g2.dispose();
    }

    // -------------------- INVENTORY DRAWING --------------------
    private void drawInventoryGrid(Graphics2D g2) {
        int boxWidth = (invSlotSize + invPadding) * invCols + invPadding;
        int boxHeight = (invSlotSize + invPadding) * invRows + invPadding;

        int startX = screenWidth - boxWidth - 40;
        int startY = 20;

        // Background box
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(startX, startY, boxWidth, boxHeight, 15, 15);

        // Draw all fixed grid slots
        for (int row = 0; row < invRows; row++) {
            for (int col = 0; col < invCols; col++) {
                int x = startX + invPadding + col * (invSlotSize + invPadding);
                int y = startY + invPadding + row * (invSlotSize + invPadding);

                // Slot border
                g2.setColor(new Color(139, 69, 19, 150)); // brown with transparency
                g2.drawRect(x, y, invSlotSize, invSlotSize);

                // Draw item if exists
                int index = row * invCols + col;
                if (index < player.inventory.size()) {
                    SuperObject item = player.inventory.get(index);
                    if (item.image != null) {
                        g2.drawImage(item.image, x + 4, y + 4, invSlotSize - 8, invSlotSize - 8, null);
                    }
                }
            }
        }

        // Inventory label below the box (centered)
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = "Inventory";
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(label);
        int textX = startX + (boxWidth / 2) - (textWidth / 2);
        int textY = startY + boxHeight + 20;

        // Black stroke outline
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawString(label, textX, textY);

        // Fill text
        g2.setColor(new Color(255, 255, 255, 200));
        g2.drawString(label, textX, textY);
    }
}