package HUD;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Main.GamePanel;

import java.io.IOException;

public class HUD {
    GamePanel gp;

    private Image charProfileImg;

    public HUD(GamePanel gp) {
        this.gp = gp;
        try {
            charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/Profilee.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Profile image not found!");
        }
    }

    public void draw(Graphics2D g2) {
        drawCharacterProfile(g2);

        // ...other HUD elements (health, inventory, etc)...
    }

    private void drawCharacterProfile(Graphics2D g2) {
        if (charProfileImg != null) {
            g2.drawImage(charProfileImg, 20, 20, 300, 120, null); // (x, y, width, height)
        }
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
      //  g2.drawString("Name: " + gp.player.name, 110, 50); // Adjust as needed
      //  g2.drawString("HP: " + gp.player.health + "/" + gp.player.maxHealth, 110, 75); // Example
        // Add more profile info as needed
    }

	public void update() {
		// TODO Auto-generated method stub
		
	}
}