package HUD;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Main.GamePanel;
import java.io.IOException;

public class HUDBag {
    GamePanel gp;

    private Image charProfileImg;

    private int x = 1330;
    private int y = 700;
    private int size = 80;
    
    public HUDBag(GamePanel gp) {
        this.gp = gp;
        try {
            charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/Bag.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Profile image not found!");
        }
    }

    public void draw(Graphics2D g2) {
        drawCharacterProfile(g2);
    }
    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + size &&
               mouseY >= y && mouseY <= y + size;
    }

    private void drawCharacterProfile(Graphics2D g2) {
        int x = 1330;
        int y = 675;
        int size = 80;

        // Draw bag icon
        if (charProfileImg != null) {
            g2.drawImage(charProfileImg, x, y, size, size, null);
        }

        // Draw the text "Inventory" with stroke
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = "Inventory";
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(label);

        int textX = x + (size / 2) - (textWidth / 2);
        int textY = y + size + 20;   // 20px below the icon

        // Create a GlyphVector for outlining the text
        GlyphVector gv = g2.getFont().createGlyphVector(g2.getFontRenderContext(), label);
        Shape textShape = gv.getOutline(textX, textY);

        // Draw the outline (stroke)
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(textShape);

        // Fill the text
        g2.setColor(Color.WHITE);
        g2.fill(textShape);
    }
    
    

    public void update() {
        // Nothing to update for now
    }
}
