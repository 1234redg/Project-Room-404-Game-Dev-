package HUD;

import java.awt.*;
import java.awt.font.GlyphVector;
import javax.imageio.ImageIO;
import Main.GamePanel;
import java.io.IOException;

public class HUDObjectives {
    GamePanel gp;

    private Image charProfileImg;
    private Image popupImg;
    private boolean showPopup = false; // tracks whether to show the popup

    // HUD position and size
    private int x = 1200;
    private int y = 678;
    private int size = 80;

    // Popup size (adjust here to make it smaller/larger)
    private int popupWidth = 400;  // adjust width
    private int popupHeight = 500; // adjust height

    public HUDObjectives(GamePanel gp) {
        this.gp = gp;
        try {
            charProfileImg = ImageIO.read(getClass().getClassLoader().getResource("objects/Objectives.png"));
            popupImg = ImageIO.read(getClass().getClassLoader().getResource("objects/ObjectivesM.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Objectives image not found!");
        }
    }

    public void draw(Graphics2D g2) {
        drawCharacterProfile(g2);

        // Draw popup if active
        if (showPopup && popupImg != null) {
            int centerX = gp.screenWidth / 2 - popupWidth / 2;
            int centerY = gp.screenHeight / 2 - popupHeight / 2;

            g2.drawImage(popupImg, centerX, centerY, popupWidth, popupHeight, null);
        }
    }

    public boolean isClicked(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x + size &&
            mouseY >= y && mouseY <= y + size) {
            showPopup = !showPopup; // toggle popup visibility on click
            return true;
        }
        return false;
    }

    private void drawCharacterProfile(Graphics2D g2) {
        // Draw the icon
        if (charProfileImg != null) {
            g2.drawImage(charProfileImg, x, y, size, size, null);
        }

        // Draw the text "Objectives" with stroke
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = "Objectives";
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(label);

        int textX = x + (size / 2) - (textWidth / 2);
        int textY = y + size + 20;   // 20px below the icon

        GlyphVector gv = g2.getFont().createGlyphVector(g2.getFontRenderContext(), label);
        Shape textShape = gv.getOutline(textX, textY);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(textShape);

        g2.setColor(Color.WHITE);
        g2.fill(textShape);
    }

    public void update() {
        // Nothing to update for now
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public boolean isPopupVisible() { return showPopup; }
    public void hidePopup() { showPopup = false; }

    // Optional: allow dynamic popup size adjustment
    public void setPopupSize(int width, int height) {
        this.popupWidth = width;
        this.popupHeight = height;
    }
}
