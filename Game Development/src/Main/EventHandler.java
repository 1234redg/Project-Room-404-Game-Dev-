package Main;

import java.awt.Rectangle;
import object.SuperObject;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        // Make the eventRect match a standard tile/item size (adjust if needed)
        eventRect = new Rectangle();
        eventRect.x = 0; // offset within the object, usually 0
        eventRect.y = 0;
        eventRect.width = gp.tileSize;  // clickable area matches item sprite size
        eventRect.height = gp.tileSize;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    // Check if mouse clicked an object
    public void checkEvent() {
        // Loop through all objects
        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject obj = gp.obj[i];

            if (obj != null && obj.canPickUp && gp.mouseClicked) {
                int objScreenX = obj.worldX - gp.player.worldX + gp.player.screenX;
                int objScreenY = obj.worldY - gp.player.worldY + gp.player.screenY;

                // Custom offset only for key and knife
                int extraLeft = 0;
                int extraUp = 0;
                if (obj.name.equals("Key") || obj.name.equals("Knife")) {
                    extraLeft = 20; // expand clickable area to the left
                    extraUp = 20;   // expand clickable area upward
                }

                // Check if mouse is inside the (possibly expanded) area
                if (gp.mouseX >= objScreenX - extraLeft && gp.mouseX <= objScreenX + obj.width &&
                    gp.mouseY >= objScreenY - extraUp && gp.mouseY <= objScreenY + obj.height) {

                    gp.obj[i] = null; // remove object
                    gp.hudUI.currentDialogue = "You picked up a " + obj.name + ".";
                    gp.mouseClicked = false;
                }
            }
        }
    }




    // Check if player hits an event rectangle (like a door or trigger)
    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        // Save original solid area
        int originalX = gp.player.solidArea.x;
        int originalY = gp.player.solidArea.y;

        // Move solid area to world position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        // Position the event rectangle
        eventRect.x = eventCol * gp.tileSize + eventRectDefaultX;
        eventRect.y = eventRow * gp.tileSize + eventRectDefaultY;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }

        // Reset positions
        gp.player.solidArea.x = originalX;
        gp.player.solidArea.y = originalY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
}
