package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuestManager {
    private List<Quest> quests = new ArrayList<>();
    // Border color for the quest window (default set as requested)
    public Color borderColor = new Color(139, 69, 19, 150);

    public void registerQuest(Quest q) {
        quests.add(q);
    }

    /** Called when an item is picked up; matches quests by itemTarget */
    public void onItemPicked(String itemName) {
        for (Quest q : quests) {
            if (!q.isCompleted() && q.getItemTarget() != null && q.getItemTarget().equalsIgnoreCase(itemName)) {
                q.increment(1);
                System.out.println("Quest progress updated: " + q.getId() + " " + q.getCurrentCount() + "/" + q.getTargetCount());
            }
        }
    }

    public List<Quest> getQuests() {
        return quests;
    }

    /** Simple UI draw: shows active quests on the middle-left of the screen */
    public void draw(Graphics2D g2, GamePanel gp) {
        int width = 260;
        int height = Math.min(200, gp.screenHeight - 40);
        int x = 20; // left margin
        int y = gp.screenHeight / 2 - height / 2; // middle-left vertically

        // Background
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRoundRect(x, y, width, height, 12, 12);

        // Border (configurable)
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, width, height, 12, 12);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Quests", x + 12, y + 26);

        g2.setFont(new Font("Arial", Font.PLAIN, 13));
        int lineY = y + 50;
        for (Quest q : quests) {
            String status = q.isCompleted() ? "(Done)" : (q.getCurrentCount() + "/" + q.getTargetCount());
            String text = q.getDescription() + " " + status;
            g2.drawString(text, x + 12, lineY);
            lineY += 20;
            if (lineY > y + height - 12) break;
        }
    }
}
