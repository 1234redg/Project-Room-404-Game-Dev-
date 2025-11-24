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

    public int width = 48;   // object width
    public int height = 48;  // object height

    // Collision hitbox
    public Rectangle solidArea;

    public SuperObject() {
        // Default hitbox matches object size
        solidArea = new Rectangle(0, 0, width, height);
    }

    public void Draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + width > gp.player.worldX - gp.player.screenX &&
            worldX - width < gp.player.worldX + gp.player.screenX &&
            worldY + height > gp.player.worldY - gp.player.screenY &&
            worldY - height < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, width, height, null);
        }
    }
}
