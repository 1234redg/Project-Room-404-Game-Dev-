package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false; // true if object blocks movement
    public int worldX, worldY;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public int width = 32;   // object width
    public int height = 32;  // object height
    
    public int offsetX = 0;   // optional pixel offset (right/left)
    public int offsetY = 0;   // optional pixel offset (up/down)


    // Collision hitbox
    public Rectangle solidArea;
	public boolean canPickUp;

    public SuperObject() {
        // Default hitbox matches object size
        solidArea = new Rectangle(0, 0, width, height);
    }

    // Method to set custom hitbox
    public void setHitbox(int x, int y, int w, int h) {
        solidArea = new Rectangle(x, y, w, h);
        solidAreaDefaultX = x;
        solidAreaDefaultY = y;
    }
    
    // Auto-adjust hitbox based on current width and height (call after setting width/height)
    public void autoAdjustHitbox() {
        int margin = 2;  // 2 pixel margin from edges
        int hitboxX = margin;
        int hitboxY = margin;
        int hitboxWidth = Math.max(width - (margin * 2), 1);
        int hitboxHeight = Math.max(height - (margin * 2), 1);
        
        setHitbox(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
    }

    public void Draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + width > gp.player.worldX - gp.player.screenX &&
            worldX - width < gp.player.worldX + gp.player.screenX &&
            worldY + height > gp.player.worldY - gp.player.screenY &&
            worldY - height < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(
                image,
                screenX + offsetX,   // apply horizontal offset
                screenY + offsetY,   // apply vertical offset
                width,
                height,
                null
            );
        }
    }

}
