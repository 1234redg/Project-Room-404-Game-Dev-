package Main;

import java.awt.*;
// no AWT event imports needed; GamePanel forwards clicks
import java.util.List;
import javax.swing.JPanel;

public class ClueTrackerUI extends JPanel {
    
    private ClueTracker tracker;
    private GamePanel gamePanel;
    private boolean visible = false;
    
    // Customizable colors
    public Color bgColor = new Color(239, 228, 176); //  background color
    public Color textColor = new Color(0, 0, 0); // Black text
    public Color headerColor = new Color(165, 42, 42); // Dark gray headers
    public Color borderColor = new Color(101, 67, 33); // Border color
    public Color checkboxUncheckedColor = new Color(200, 200, 200); // Unchecked box color
    public Color checkboxCheckedColor = new Color(255, 0, 0); // Checked box color (green)
    
    private static final int PADDING = 20;
    private static final int CELL_HEIGHT = 30;
    private static final int CHECKBOX_SIZE = 20;
    private static final int CLUE_SECTION_HEIGHT = 150;
    private static final int NAME_COL_WIDTH = 150;
    private static final int HEADER_HEIGHT = 30;
    
    public ClueTrackerUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tracker = ClueTracker.getInstance();
        
        // Handle mouse clicks for checkbox toggling
        // Note: This UI is drawn by GamePanel; GamePanel forwards mouse clicks to `handleClick`.
    }
    
    /**
     * Toggle visibility
     */
    public void toggleVisibility() {
        visible = !visible;
        gamePanel.repaint();
    }
    
    public void setVisible(boolean v) {
        visible = v;
        gamePanel.repaint();
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * Draw the tracker UI
     */
    public void draw(Graphics2D g2) {
        if (!visible) return;

        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();

        // Panel dimensions (centered, with margins)
        int panelWidth = Math.min(600, screenWidth - 40);
        int panelHeight = Math.min(500, screenHeight - 40);
        int panelX = (screenWidth - panelWidth) / 2;
        int panelY = (screenHeight - panelHeight) / 2;

        // Background
        g2.setColor(bgColor);
        g2.fillRect(panelX, panelY, panelWidth, panelHeight);

        // Border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(13));
        g2.drawRect(panelX, panelY, panelWidth, panelHeight);

        // Title
        g2.setColor(headerColor);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Clue Tracker", panelX + PADDING, panelY + PADDING + 20);

        int currentY = panelY + PADDING + 40;

        // ===== CLUES SECTION =====
        g2.setColor(headerColor);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("Clues Discovered:", panelX + PADDING, currentY);
        currentY += 25;

        g2.setColor(textColor);
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        List<String> clues = tracker.getClues();
        if (clues.isEmpty()) {
            g2.drawString("(No clues yet)", panelX + PADDING + 10, currentY);
        } else {
            for (int i = 0; i < clues.size() && currentY < panelY + CLUE_SECTION_HEIGHT; i++) {
                String formattedClue = tracker.getFormattedClue(i);
                g2.drawString(formattedClue, panelX + PADDING + 10, currentY);
                currentY += 20;
            }
        }

        currentY = panelY + CLUE_SECTION_HEIGHT + 10;

        // ===== SUSPECT TABLE SECTION =====
        g2.setColor(headerColor);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("Suspect Status (click a cell to mark 'NOT this profession'):", panelX + PADDING, currentY);
        currentY += 30;

        // Table header (professions)
        List<String> professions = tracker.getProfessions();
        List<String> npcNames = tracker.getNPCNames();

        int tableTopY = currentY;
        int tableLeftX = panelX + PADDING;
        int tableWidth = panelWidth - 2 * PADDING;

        // Compute column sizes
        int nameColWidth = Math.min(NAME_COL_WIDTH, tableWidth / 3);
        int remaining = tableWidth - nameColWidth;
        int colWidth = professions.isEmpty() ? remaining : (remaining / professions.size());

        // Draw header background
        g2.setColor(new Color(230, 230, 230));
        g2.fillRect(tableLeftX, tableTopY, tableWidth, HEADER_HEIGHT);

        // Header: Name
        g2.setColor(headerColor);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("Name", tableLeftX + 8, tableTopY + 18);

        // Header: professions
        for (int c = 0; c < professions.size(); c++) {
            int hx = tableLeftX + nameColWidth + c * colWidth;
            g2.drawString(professions.get(c), hx + 30, tableTopY + 18);
        }

        // Draw rows
        int rowY = tableTopY + HEADER_HEIGHT;
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        for (String npcName : npcNames) {
            if (rowY + CELL_HEIGHT > panelY + panelHeight - PADDING) break;

            // Row background
            g2.setColor(new Color(245, 245, 220));
            g2.fillRect(tableLeftX, rowY, tableWidth, CELL_HEIGHT);

            // Draw name
            g2.setColor(textColor);
            g2.drawString(npcName, tableLeftX + 8, rowY + 20);

            // Draw profession checkboxes
            for (int c = 0; c < professions.size(); c++) {
                String prof = professions.get(c);
                int cx = tableLeftX + nameColWidth + c * colWidth + (colWidth - CHECKBOX_SIZE) / 2;
                int cy = rowY + (CELL_HEIGHT - CHECKBOX_SIZE) / 2;
                boolean ruledOut = tracker.isRuledOut(npcName, prof);
                drawCheckbox(g2, cx, cy, CHECKBOX_SIZE, ruledOut);
            }

            rowY += CELL_HEIGHT;
        }

        // Close instruction
        g2.setColor(new Color(100, 100, 100));
        g2.setFont(new Font("Arial", Font.ITALIC, 11));
        g2.drawString("Press J to close", panelX + PADDING, panelY + panelHeight - 10);
    }

    /**
     * Handle checkbox clicks. Public so GamePanel can forward mouse events.
     * Returns true if the click was handled by the UI (i.e., toggled a cell).
     */
    public boolean handleClick(int clickX, int clickY) {
        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();
        int panelWidth = Math.min(600, screenWidth - 40);
        int panelHeight = Math.min(500, screenHeight - 40);
        int panelX = (screenWidth - panelWidth) / 2;
        int panelY = (screenHeight - panelHeight) / 2;
        int tableLeftX = panelX + PADDING;
        int tableTopY = panelY + CLUE_SECTION_HEIGHT + 40;
        int tableWidth = panelWidth - 2 * PADDING;

        // Compute column sizes (must match draw())
        int nameColWidth = Math.min(NAME_COL_WIDTH, tableWidth / 3);
        List<String> professions = tracker.getProfessions();
        int remaining = tableWidth - nameColWidth;
        int colWidth = professions.isEmpty() ? remaining : (remaining / professions.size());

        List<String> npcNames = tracker.getNPCNames();

        int rowY = tableTopY + HEADER_HEIGHT;
        for (String npcName : npcNames) {
            if (rowY + CELL_HEIGHT > panelY + panelHeight - PADDING) break;

            // Check each profession column
            for (int c = 0; c < professions.size(); c++) {
                int cx = tableLeftX + nameColWidth + c * colWidth + (colWidth - CHECKBOX_SIZE) / 2;
                int cy = rowY + (CELL_HEIGHT - CHECKBOX_SIZE) / 2;
                if (clickX >= cx && clickX <= cx + CHECKBOX_SIZE && clickY >= cy && clickY <= cy + CHECKBOX_SIZE) {
                    String prof = professions.get(c);
                    tracker.toggleRuledOut(npcName, prof);
                    gamePanel.repaint();
                    return true;
                }
            }

            rowY += CELL_HEIGHT;
        }
        return false;
    }
    private void drawCheckbox(Graphics2D g2, int x, int y, int size, boolean checked) {
        // Box outline
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x, y, size, size);
        
        // Fill
        g2.setColor(checked ? checkboxCheckedColor : checkboxUncheckedColor);
        g2.fillRect(x + 1, y + 1, size - 2, size - 2);
        
        // X sign if checked
        if (checked) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            // X sign: two diagonal lines forming an X
            g2.drawLine(x + 5, y + 5, x + 15, y + 15);
            g2.drawLine(x + 15, y + 5, x + 5, y + 15);
        }
    }
    
}
