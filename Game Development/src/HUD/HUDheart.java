package HUD;
import java.awt.*;
import javax.imageio.ImageIO;

import Main.GamePanel;

import java.io.IOException;

public class HUDheart {
    GamePanel gp;

    private Image charProfileImg;
	public String currentDialogue;

    public HUDheart(GamePanel gp) {
        this.gp = gp;
        try {
           // charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/SmallHeart.png"));
            charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/BigHeart.png"));
          //  charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/EmptyHeart.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("heart image not found!");
        }
    }

    public void draw(Graphics2D g2) {
        drawCharacterProfile(g2);
    }

    private void drawCharacterProfile(Graphics2D g2) {
        if (charProfileImg != null) {

            // heart size
            int w = 50;  // width of each heart
            int h = 50;  // height of each heart

            // starting position
            int x = 20;
            int y = 150;

            // draw 3 hearts
            for (int i = 0; i < 3; i++) {
                g2.drawImage(charProfileImg, x + (i * (w + 10)), y, w, h, null);
            }
        }
    }

	public void update() {
		// TODO Auto-generated method stub
		
	}

}