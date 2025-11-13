package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        //Move screen along with the movement of the player
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        //the solid area size in the character
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
    	
    	//Location sa player || Starting point
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 10;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/1n.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/2n.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/3n.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/4n.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/5n.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/6n.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/player/7n.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/player/8n.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/1ws.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/2ws.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/3ws.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/4ws.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/5ws.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/6ws.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/7ws.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/8ws.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/1wb.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/2wb.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/3wb.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/4wb.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/5wb.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/player/6wb.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/player/7wb.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/player/8wb.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/1wc.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/2wc.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/3wc.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/4wc.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/5wc.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/6wc.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/7wc.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/8wc.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            
            if (keyH.upPressed) {
                direction = "up";               
            } 
            else if (keyH.downPressed) {
                direction = "down";             
            } 
            else if (keyH.leftPressed) {
                direction = "left";                
            } 
            else if (keyH.rightPressed) {
                direction = "right";                
            }
            
            
            //CHECK TILE COLLISION 
            collisionOn = false;
            gp.Checker.checkTile(this);
            // if collision is false player can move
            if(collisionOn == false) {
            	
            	switch(direction) {
            	case "up":
            		worldY -= speed;
            		break;
            	case "down":
            		 worldY += speed;
            		break;
            	case "left": 
            		worldX -= speed;
            		break;
            	case "right":
            		worldX += speed;
            		break;
            	}
            }

            spriteCounter++;
            if (spriteCounter > 7) {
                spriteNum++;
                if (spriteNum > 8) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }


    public void draw(Graphics g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = selectFrame(up1, up2, up3, up4, up5, up6, up7, up8);
                break;
            case "down":
                image = selectFrame(down1, down2, down3, down4, down5, down6, down7, down8);
                break;
            case "left":
                image = selectFrame(left1, left2, left3, left4, left5, left6, left7, left8);
                break;
            case "right":
                image = selectFrame(right1, right2, right3, right4, right5, right6, right7, right8);
                break;
        }

        // Use worldX and worldY instead of x, y
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize + 15, null);
    }

    private BufferedImage selectFrame(BufferedImage... frames) {
        if (spriteNum < 1 || spriteNum > frames.length) spriteNum = 1;
        return frames[spriteNum - 1];
    }
}
