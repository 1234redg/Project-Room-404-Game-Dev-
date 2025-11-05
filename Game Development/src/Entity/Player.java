package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp; // Reference to the GamePanel
	KeyHandler keyH;// Handles input
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();// Set initial values for player
		getPlayerImage();// Load character sprites
	}
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;// Movement speed
		direction = "down";//Default facing direction
	}
	
	public void getPlayerImage() {
		
		try {
			//Load images here
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
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || 
		keyH.leftPressed == true  || keyH.rightPressed == true) {
		
			if(keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			}
			
			spriteCounter++;
			if(spriteCounter > 16) { //adjust the number if character blink too fast
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		}	
	//}
	public void draw(Graphics g2) {
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			if(spriteNum == 4) {
				image = up4;
			}
			if(spriteNum == 5) {
				image = up5;
			}
			if(spriteNum == 6) {
				image = up6;
			}
			if(spriteNum == 7) {
				image = up7;
			}
			if(spriteNum == 8) {
				image = up8;
			}
			break;
			
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down3;
			}
			if(spriteNum == 4) {
				image = down4;
			}
			if(spriteNum == 5) {
				image = down5;
			}
			if(spriteNum == 6) {
				image = down6;
			}
			if(spriteNum == 7) {
				image = down7;
			}
			if(spriteNum == 8) {
				image = down8; 
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
			}
			if(spriteNum == 4) {
				image = left4;
			}
			if(spriteNum == 5) {
				image = left5;
			}
			if(spriteNum == 6) {
				image = left6;
			}
			if(spriteNum == 7) {
				image = left7;
			}
			if(spriteNum == 8) {
				image = left8;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right3;
			}
			if(spriteNum == 4) {
				image = right4;
			}
			if(spriteNum == 5) {
				image = right5;
			}
			if(spriteNum == 6) {
				image = right6;
			}
			if(spriteNum == 7) {
				image = right7;
			}
			if(spriteNum == 8) {
				image = right8;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize + 15, null);
	}
}





