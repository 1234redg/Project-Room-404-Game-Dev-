package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Popup {

    private Image image;
    private int x, y;
    private int targetY;
    private boolean show = false;
    private long triggerTime;
    private int screenWidth, screenHeight;

    // Scale factor
    private double scaleFactor = 0.7;
    private int width, height;

    // Hide button area
    private FadeButton hideButton;
    private boolean visible = true;

    public Popup(int screenWidth, int screenHeight, String imagePath) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        image = new ImageIcon(getClass().getResource("/MurderRoomMaps/Intro1.png")).getImage();

        // image size
        width = (int)(image.getWidth(null) * scaleFactor);
        height = (int)(image.getHeight(null) * scaleFactor);

        // start off-screen
        x = (screenWidth - width) / 2;
        y = screenHeight;
        targetY = (screenHeight - height) / 2;

        // trigger after 1 sec
        triggerTime = System.currentTimeMillis() + 1000;

        // hide button
        hideButton = new FadeButton(
            x + (width - 160) / 2,
            y + height - 40 - 170,
            150, 40
        );
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        // Show popup after 1 sec
        if (!show && currentTime >= triggerTime) {
            show = true;
        }

        // Slide animation
        if (show && y > targetY) {
            y -= 20;
            if (y < targetY) y = targetY;
        }

        // update button position to follow popup
        hideButton.setPosition(
            x + (width - hideButton.getWidth()) / 2,
            y + height - hideButton.getHeight() - 170
        );

        hideButton.update();
    }

    public void draw(Graphics2D g2) {
        if (show && visible) {
            // Draw popup image
            g2.drawImage(image, x, y, width, height, null);

            // Draw hide button (transparent)
            hideButton.draw(g2);
        }
    }

    public void handleClick(int mouseX, int mouseY) {
        if (hideButton.contains(mouseX, mouseY)) {
            visible = false;
        }
    }

    public void handleHover(int mouseX, int mouseY) {
        hideButton.handleHover(mouseX, mouseY);
    }

    public boolean isShowing() {
        return show;
    }

    // ---------------- FadeButton class (like MainMenu)
    private class FadeButton {
        private int x, y, width, height;
        private float alpha = 0f;
        private float targetAlpha = 0f;
        private final int interval = 10;
        private final float speed = 0.04f; // adjust for smoothness
        private Timer fadeTimer;

        public FadeButton(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            fadeTimer = new Timer(interval, e -> animateFade());
            fadeTimer.start();
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getWidth() { return width; }
        public int getHeight() { return height; }

        public void update() {
            animateFade();
        }

        private void animateFade() {
            if (alpha < targetAlpha)
                alpha = Math.min(alpha + speed, targetAlpha);
            else if (alpha > targetAlpha)
                alpha = Math.max(alpha - speed, targetAlpha);
        }

        public void handleHover(int mouseX, int mouseY) {
            if (contains(mouseX, mouseY)) {
                targetAlpha = 0.4f;
            } else {
                targetAlpha = 0f;
            }
        }

        public void draw(Graphics2D g2) {
            g2.setColor(new Color(10, 10, 10, (int)(alpha * 150)));
            g2.fillRoundRect(x, y, width, height, 10, 10);
        }

        public boolean contains(int mouseX, int mouseY) {
            return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        }
    }
}
