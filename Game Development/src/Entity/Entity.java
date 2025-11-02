package Entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	// Common properties for all entities (player, enemies, NPCs)
	public int x, y;
	public int speed;
	
	// Common properties for all entities (player, enemies, NPCs)
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;// Current facing direction
	
	public int spriteCounter = 0;// Counts frames for switching sprites
	public int spriteNum = 1;// Chooses which sprite frame to show
}
