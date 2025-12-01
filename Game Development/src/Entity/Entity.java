package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class Entity {
	
	GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8;

    public String direction; // current facing direction

    public int spriteCounter = 0; // counts frames for switching sprites
    public int spriteNum = 1;     // chooses which sprite frame to show

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
	public boolean inDialogue = false;
    String dialogues[] = new String[30];
    int dialogueIndex = 0;
    String clues[] = new String[30]; // clues associated with each dialogue (null = no clue)

    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void setAction() { }
    
    public void speak() {
    	 if(dialogues[dialogueIndex] == null) {
			 dialogueIndex = 0;
		 }
		 gp.ui.currentDialogue = dialogues[dialogueIndex];
		 
		 // Add clue if this dialogue has one associated
		 if (clues[dialogueIndex] != null && !clues[dialogueIndex].isEmpty()) {
		 	Main.ClueTracker.getInstance().addClue(clues[dialogueIndex]);
		 }
		 
		 dialogueIndex++;
		 
		 switch(gp.player.direction) {
		 case "up":
			 direction = "down";
			 break;
		 case "down":
			 direction = "up";
			 break;
		 case "left":
			 direction = "right";
			 break;
		 case "right":
			 direction = "left";
			 break;
		 }
	 }
    
    
    public void update() {
    	
    		setAction();
    		
    		collisionOn = false;
    		gp.Checker.checkTile(this);
    		gp.Checker.checkObject(this, gp.obj);
    		gp.Checker.checkPlayer(this);
    		
    		if(collisionOn == false) {
    			 switch(direction) {
    			 case"up": worldY -= speed;break;
    			 case"down": worldY += speed;break;
    			 case"left": worldX -= speed;break;
    			 case"right": worldX += speed;break;
    			 }
    		}
    		
    		spriteCounter++;
    		if(spriteCounter == 12) {
    			if(spriteNum == 1) {
    				spriteNum = 2;
    			}
    			else if(spriteNum == 2) {
    				spriteNum = 1;
    			}
    			spriteCounter = 0;
    		}
    }
    
    public void draw(Graphics2D g2) {
    	BufferedImage image = null;
    	 int screenX = worldX - gp.player.worldX + gp.player.screenX;
         int screenY = worldY - gp.player.worldY + gp.player.screenY;

         if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
             worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
             worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
             worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        	 
        	 switch (direction) {
             case "up": image = selectFrame(up1, up2, up3, up4, up5, up6, up7, up8); break;
             case "down": image = selectFrame(down1, down2, down3, down4, down5, down6, down7, down8); break;
             case "left": image = selectFrame(left1, left2, left3, left4, left5, left6, left7, left8); break;
             case "right": image = selectFrame(right1, right2, right3, right4, right5, right6, right7, right8); break;
         }

             g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
         }

    }
    private BufferedImage selectFrame(BufferedImage... frames) {
        if (spriteNum < 1 || spriteNum > frames.length) spriteNum = 1;
        return frames[spriteNum - 1];
    }
}