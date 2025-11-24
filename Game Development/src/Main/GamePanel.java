package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import object.SuperObject;
import tile.TileManager;

//Game Panel - works as the GAME SCREEN
public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //Each tile is 16x16 || default size of any player character map tiles and etc.
	final int scale = 3;             //Scale tiles up to make them larger
	
	public final int tileSize =  originalTileSize * scale; // Each tile = 48x48 pixels
	public final int maxScreenCol = 30;//Number of columns (width)
	public final int maxScreenRow = 20;// Number of rows (height)
	
	//GAME SCREEN SIZE
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//WORLD SETTINGS
	public final int maxWorldCol = 65;
	public final int maxWorldRow = 58;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	//FPS (Frames Per Second)
	int FPS = 60;
	
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();//Keyboard input
	Thread gameThread;//Runs the game loop in a separate thread
	public collisionChecker Checker = new collisionChecker(this);
	public AssetSetter set = new AssetSetter(this);
	public Player player = new Player(this,keyH);//The player object
	public SuperObject obj[] = new SuperObject[50];// objects in the game
	
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));// Set panel size
		this.setBackground(Color.black );// Background color
		this.setDoubleBuffered(true);// Smooth graphics rendering
		this.addKeyListener(keyH);//Listen for key inputs
		this.setFocusable(true);//Make sure GamePanel receives key input
	}
	
	public void SetUpGame() {
		set.setObjects();
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);// Create a new thread for the game
		gameThread.start(); // Start running the game loop
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //update the screen 0.0166 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {	
			
			//Update character position (time)
			update();
			
			//draw the screen with the updated position
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);// Pause until next frame
				
				nextDrawTime += drawInterval;// Schedule next frame
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
		}
	}
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//TILE
		tileM.draw(g2);
		
		
		//OBJECT
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].Draw(g2, this);
			
			}
		}
		
		
		//PLAYER
		player.draw(g2);
		
		g2.dispose();
	}
}
