package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8;

    public String direction; // current facing direction

    public int spriteCounter = 0; // counts frames for switching sprites
    public int spriteNum = 1;     // chooses which sprite frame to show

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Entity() {
        // Default hitbox size; adjust if your player is smaller/larger
        solidArea = new Rectangle(0, 0, 38, 30);
    }
}
