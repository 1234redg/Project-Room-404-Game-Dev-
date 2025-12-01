package HUD;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Main.GamePanel;
import java.io.IOException;

public class HUDInvestigate {
    GamePanel gp;

    private Image charProfileImg;

    // You can adjust these values to move the HUD
    private int x = 727;
    private int y = 667;
    private int size = 80;
    
    public HUDInvestigate(GamePanel gp) {
        this.gp = gp;
        try {
            charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/Investigative.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Objectives image not found!");
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
        // Use instance variables here (do NOT redeclare)
        
        // Draw the icon
        if (charProfileImg != null) {
            g2.drawImage(charProfileImg, x, y, size, size, null);
        }

        // Draw the text "Objectives" with stroke
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = "Investigate";
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

    // Optional: setters to adjust position dynamically
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
