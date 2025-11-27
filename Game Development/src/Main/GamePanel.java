package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {


// SCREEN SETTINGS  
final int originalTileSize = 16;  
final int scale = 3;  
public final int tileSize = originalTileSize * scale;  
public final int maxScreenCol = 20;  
public final int maxScreenRow = 15;  
public final int screenWidth = tileSize * maxScreenCol;  
public final int screenHeight = tileSize * maxScreenRow;  

// WORLD SETTINGS  
public final int maxWorldCol = 65;  
public final int maxWorldRow = 77;  
public final int worldWidth = tileSize * maxWorldCol;  
public final int worldHeight = tileSize * maxWorldRow;  

// FPS  
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

// Game state  
  
public final int playState = 1;  
public final int pauseState = 2;  
public final int dialogueState = 3;  
public int gameState = playState;

public int currentNPC = -1; // index of NPC being interacted with  

public GamePanel() {  
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));  
    this.setBackground(Color.black);  
    this.setDoubleBuffered(true);  
    this.addKeyListener(keyH);  
    this.setFocusable(true);  
}  

public void SetUpGame() {  
    set.setObjects();  
    set.setNPC();  
    gameState = playState;  
}  

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
            remainingTime = remainingTime / 1000000;  
            if (remainingTime < 0) remainingTime = 0;  
            Thread.sleep((long) remainingTime);  
            nextDrawTime += drawInterval;  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  

public void update() {  
    if (gameState == playState) {  
    	//player
        player.update();  
        //npc
        for(int i = 0; i< npc.length; i++) {
        		if(npc[i] != null) {
        			npc[i].update();
        		}
        }
    }
    
    if(gameState == pauseState) {
    	
    }

        
}  

   
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
    for(int i = 0; i < npc.length; i++){
    		if(npc[i] != null) {
    			npc[i].draw(g2);
    		}
    }

    // Player  
    player.draw(g2);  

    // Dialogue UI  
   ui.draw(g2);

    g2.dispose();  
}  


}
