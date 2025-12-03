package HUD;

import java.awt.*;
import java.awt.font.GlyphVector;
import javax.imageio.ImageIO;
import Main.GamePanel;
import java.io.IOException;

public class HUDBag {
    GamePanel gp;

    private Image charProfileImg;

    // Adjustable bag HUD position + size
    private int x = 1330;   // Change this to move left/right
    private int y = 740;    // Change this to move up/down
    private int size = 80;  // Change to resize the Bag icon
    
    public HUDBag(GamePanel gp) {
        this.gp = gp;
        try {
            charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/Bag.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Bag image not found!");
        }
    }

    public void draw(Graphics2D g2) {
        drawCharacterProfile(g2);
    }

    /**
     * Check if the Bag HUD is clicked
     */
    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + size &&
               mouseY >= y && mouseY <= y + size;
    }

    private void drawCharacterProfile(Graphics2D g2) {
        // Draw the icon
        if (charProfileImg != null) {
            g2.drawImage(charProfileImg, x, y, size, size, null);
        }

        // Draw the text "Inventory" with outline stroke
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = "Inventory";
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(label);

        int textX = x + (size / 2) - (textWidth / 2);
        int textY = y + size + 20; // 20px below icon

        GlyphVector gv = g2.getFont().createGlyphVector(g2.getFontRenderContext(), label);
        Shape textShape = gv.getOutline(textX, textY);

        // Outline
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(textShape);

        // Fill
        g2.setColor(Color.WHITE);
        g2.fill(textShape);
    }

    public void update() {
        // Nothing to update yet
    }

    // Optional: dynamic position setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setSize(int size) { this.size = size; }
}
