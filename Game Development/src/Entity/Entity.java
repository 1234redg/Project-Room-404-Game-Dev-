package Entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	// Common properties for all entities (player, enemies, NPCs)
	public int worldX, worldY;
	public int speed;
	
	// Common properties for all entities (player, enemies, NPCs)
	public BufferedImage up1, up2,up3,up4,up5,up6,up7,up8, down1, down2, down3, down4, down5,down6,down7,
	down8, left1, left2,left3,left4,left5,left6,left7,left8, right1, right2,right3,
	right4,right5,right6,right7,right8;
	public String direction;// Current facing direction
	
	public int spriteCounter = 0;// Counts frames for switching sprites
	public int spriteNum = 1;// Chooses which sprite frame to show
}
