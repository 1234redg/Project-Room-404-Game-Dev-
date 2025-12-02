package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameUI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;
    public boolean inventoryOpen = false;  
    
    public GameUI(GamePanel gp) {
    	this.gp = gp;
    	
    	arial_40 = new Font("Arial", Font.PLAIN, 40);
    	arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    
    public void showMessage(String text) {
    currentDialogue = text;     // <-- update currentDialogue
    messageOn = true;
    gp.gameState = gp.dialogueState;  // <-- switch to dialogue state
}

    
    public void draw(Graphics2D g2) {
    	
    	this.g2 = g2;
    	
    	g2.setFont(arial_40);
    	g2.setColor(Color.white);
    	
    	if(gp.gameState == gp.playState) {
    		//drawInventory();
    	}
    	if(gp.gameState == gp.pauseState) {
    		drawPauseScreen();
    	}
    	if(gp.gameState == gp.dialogueState) {
    		drawDialogueScreen();
    	}
    }
    
    public void drawPauseScreen() {
    	
    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
    	String text = "PAUSE";
    	int x = getXforCenteredText(text);
    	int y = gp.screenHeight/2;
    }
    
    public void drawDialogueScreen() {
    		// window 
    		int x = 50;
    		int y = gp.screenHeight - 150;
    		int width = gp.screenWidth - 100;
    		int height =  100;
    		
    		drawSubWindow(x, y, width, height);
    		
    		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

			g2.setColor(Color.white);

    		x += gp.tileSize;
    		y += gp.tileSize;
    		
    		for(String line : currentDialogue.split("\n")) {
    			g2.drawString(line, x, y);
    			y += 40;
    		}
    		
    }
    
    public void drawSubWindow(int x, int y, int width, int height) {
    	Color c = new Color(0, 0, 0, 100);
    	g2.setColor(c);
    	g2.fillRoundRect(x, y, width, height, 35, 35);
    	
    	c = new Color(101, 67, 33);
    	g2.setColor(c);
    	g2.setStroke(new BasicStroke(5));
    	g2.drawRoundRect(x, y, width, height, 35, 35);
    }

    public int getXforCenteredText(String text) {
    	
    	int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
    	int x =gp.screenWidth/2 - length/2;
    	return x;
    }
    

}
