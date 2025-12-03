package Main;

import java.awt.*;
import java.util.List;
import javax.swing.JPanel;

public class ClueTrackerUI extends JPanel {
    private ClueTracker tracker;
    private GamePanel gamePanel;
    private boolean visible = false;

    private boolean showFeedback = false;
    private String feedbackMessage = "";
    private int failCount = 0;

    // Win/Loss image state (handled locally)
    private Image resultImage = null;
    private boolean showResultImage = false;

    public Color bgColor = new Color(239, 228, 176);
    public Color textColor = new Color(0, 0, 0);
    public Color headerColor = new Color(165, 42, 42);
    public Color borderColor = new Color(101, 67, 33);
    public Color checkboxUncheckedColor = new Color(200, 200, 200);
    public Color checkboxCheckedColor = new Color(255, 0, 0);

    private static final int PADDING = 20;
    private static final int CELL_HEIGHT = 30;
    private static final int CHECKBOX_SIZE = 20;
    private static final int CLUE_SECTION_HEIGHT = 150;
    private static final int NAME_COL_WIDTH = 120;
    private static final int HEADER_HEIGHT = 30;

    public ClueTrackerUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tracker = ClueTracker.getInstance();
    }

    public void toggleVisibility() {
        visible = !visible;
        clearFeedback();
        failCount = 0;
        clearResultImage();
        gamePanel.repaint();
    }

    public void setVisible(boolean v) {
        visible = v;
        clearFeedback();
        failCount = 0;
        clearResultImage();
        gamePanel.repaint();
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(Graphics2D g2) {
        if (!visible) return;

        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();

        int panelWidth = Math.min(800, screenWidth - 40);
        int panelHeight = Math.min(500, screenHeight - 40);
        int panelX = (screenWidth - panelWidth) / 2;
        int panelY = (screenHeight - panelHeight) / 2;

        // Background for clue tracker panel
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
        g2.drawString("Suspect Status (click cell to X, shift + click for O):", panelX + PADDING, currentY);
        currentY += 30;

        List<String> professions = tracker.getProfessions();
        List<String> npcNames = tracker.getNPCNames();

        int tableTopY = currentY;
        int tableLeftX = panelX + PADDING;
        int tableWidth = panelWidth - 2 * PADDING;

        int nameColWidth = Math.min(NAME_COL_WIDTH, tableWidth / 3);
        int remaining = tableWidth - nameColWidth;
        int colWidth = professions.isEmpty() ? remaining : (remaining / professions.size());

        g2.setColor(new Color(230, 230, 230));
        g2.fillRect(tableLeftX, tableTopY, tableWidth, HEADER_HEIGHT);

        g2.setColor(headerColor);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("Name", tableLeftX + 8, tableTopY + 18);

        for (int c = 0; c < professions.size(); c++) {
            int hx = tableLeftX + nameColWidth + c * colWidth;
            g2.drawString(professions.get(c), hx + 30, tableTopY + 18);
        }

        int rowY = tableTopY + HEADER_HEIGHT;
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        for (String npcName : npcNames) {
            if (rowY + CELL_HEIGHT > panelY + panelHeight - PADDING) break;

            g2.setColor(new Color(245, 245, 220));
            g2.fillRect(tableLeftX, rowY, tableWidth, CELL_HEIGHT);

            g2.setColor(textColor);
            g2.drawString(npcName, tableLeftX + 8, rowY + 20);

            for (int c = 0; c < professions.size(); c++) {
                String prof = professions.get(c);
                int cx = tableLeftX + nameColWidth + c * colWidth + (colWidth - CHECKBOX_SIZE) / 2;
                int cy = rowY + (CELL_HEIGHT - CHECKBOX_SIZE) / 2;
                boolean ruledOut = tracker.isRuledOut(npcName, prof);
                String guessedProf = tracker.getGuessedProfession(npcName);
                boolean isGuessed = guessedProf != null && guessedProf.equals(prof);
                drawCheckbox(g2, cx, cy, CHECKBOX_SIZE, ruledOut, isGuessed);
            }
            rowY += CELL_HEIGHT;
        }

        // Confirm guesses hint
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Press K to confirm your guesses!", panelX + panelWidth - 275, panelY + panelHeight - 30);

        // Feedback display
        if (showFeedback) {
            g2.setColor(tracker.isVictory() ? Color.GREEN : Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 22));
            g2.drawString(feedbackMessage, panelX + PADDING + 20, panelY + panelHeight - 60);
        }

        g2.setColor(new Color(100, 100, 100));
        g2.setFont(new Font("Arial", Font.ITALIC, 11));
        g2.drawString("Press J to close", panelX + PADDING, panelY + panelHeight - 10);

        // ===== WIN/LOSS POPUP IMAGE OVERLAY =====
        if (showResultImage && resultImage != null) {
            // Gray transparent background
            g2.setColor(new Color(40, 40, 40, 180));
            g2.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());

            int imageWidth = resultImage.getWidth(null);
            int imageHeight = resultImage.getHeight(null);
            int x = (gamePanel.getWidth() - imageWidth) / 2;
            int y = (gamePanel.getHeight() - imageHeight) / 2;
            g2.drawImage(resultImage, x, y, null);
        }
    }

    public boolean handleClick(int clickX, int clickY, boolean shiftHeld) {
        // If result image showing, dismiss it on any click
        if (showResultImage) {
            clearResultImage();
            gamePanel.repaint();
            return true;
        }

        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();
        int panelWidth = Math.min(800, screenWidth - 40);
        int panelHeight = Math.min(500, screenHeight - 40);
        int panelX = (screenWidth - panelWidth) / 2;
        int panelY = (screenHeight - panelHeight) / 2;
        int tableLeftX = panelX + PADDING;
        int tableTopY = panelY + CLUE_SECTION_HEIGHT + 40;
        int tableWidth = panelWidth - 2 * PADDING;

        int nameColWidth = Math.min(NAME_COL_WIDTH, tableWidth / 3);
        List<String> professions = tracker.getProfessions();
        int remaining = tableWidth - nameColWidth;
        int colWidth = professions.isEmpty() ? remaining : (remaining / professions.size());
        List<String> npcNames = tracker.getNPCNames();

        int rowY = tableTopY + HEADER_HEIGHT;
        for (String npcName : npcNames) {
            if (rowY + CELL_HEIGHT > panelY + panelHeight - PADDING) break;
            for (int c = 0; c < professions.size(); c++) {
                int cx = tableLeftX + nameColWidth + c * colWidth + (colWidth - CHECKBOX_SIZE) / 2;
                int cy = rowY + (CELL_HEIGHT - CHECKBOX_SIZE) / 2;
                if (clickX >= cx && clickX <= cx + CHECKBOX_SIZE && clickY >= cy && clickY <= cy + CHECKBOX_SIZE) {
                    String prof = professions.get(c);
                    if (shiftHeld) {
                        tracker.setGuessedProfession(npcName, prof);
                    } else {
                        tracker.toggleRuledOut(npcName, prof);
                    }
                    gamePanel.repaint();
                    return true;
                }
            }
            rowY += CELL_HEIGHT;
        }
        return false;
    }

    private void drawCheckbox(Graphics2D g2, int x, int y, int size, boolean checked, boolean guessed) {
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x, y, size, size);

        if (guessed) {
            g2.setColor(Color.GREEN);
        } else {
            g2.setColor(checked ? checkboxCheckedColor : checkboxUncheckedColor);
        }
        g2.fillRect(x + 1, y + 1, size - 2, size - 2);

        if (checked) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x + 5, y + 5, x + 15, y + 15);
            g2.drawLine(x + 15, y + 5, x + 5, y + 15);
        }
        if (guessed) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(x + 4, y + 4, size - 8, size - 8);
        }
    }

    private void showResultImage(String imgPath) {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            resultImage = tk.getImage(getClass().getClassLoader().getResource(imgPath));
            showResultImage = true;
        } catch (Exception e) {
            resultImage = null;
            showResultImage = false;
        }
    }

    public void clearResultImage() {
        showResultImage = false;
        resultImage = null;
        gamePanel.repaint();
    }

    public void confirmGuesses() {
        int correct = tracker.countCorrectGuesses();
        boolean victory = tracker.isVictory();
        showFeedback = true;

        if (victory) {
            feedbackMessage = "Victory! All roles correctly deduced!";
            failCount = 0;
            showResultImage("objects/Solved.png");
        } else {
            failCount++;
            feedbackMessage = "You got " + correct + " out of " + tracker.getNPCNames().size() + " correct!";
            if (failCount >= 3) {
                showResultImage("objects/Failed.png");
                failCount = 0;
            }
        }
        gamePanel.repaint();
    }

    public void resetGuesses() {
        tracker.clearGuesses();
        clearFeedback();
        clearResultImage();
        gamePanel.repaint();
    }

    public boolean isFeedbackShowing() {
        return showFeedback;
    }

    public void clearFeedback() {
        showFeedback = false;
        feedbackMessage = "";
        gamePanel.repaint();
    }
}