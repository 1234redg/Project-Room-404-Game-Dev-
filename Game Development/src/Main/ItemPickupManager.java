package Main;

import object.SuperObject;
import Entity.Player;

public class ItemPickupManager {

    private SuperObject[] obj;
    private Player player;

    public ItemPickupManager(SuperObject[] obj, Player player) {
        this.obj = obj;
        this.player = player;
    }

    // Returns the picked object name or null if nothing picked
    public String checkPickup(int mouseX, int mouseY) {
        for (int i = 0; i < obj.length; i++) {
            SuperObject o = obj[i];

            if (o != null) {
                Package p = o.getClass().getPackage();

                // Only items from "items" package are clickable
                if (p != null && "items".equals(p.getName())) {

                    int objScreenX = o.worldX - player.worldX + player.screenX;
                    int objScreenY = o.worldY - player.worldY + player.screenY;

                    if (mouseX >= objScreenX && mouseX <= objScreenX + o.width &&
                        mouseY >= objScreenY && mouseY <= objScreenY + o.height) {

                        // Add to inventory
                        if (player.inventory.size() < player.maxInventorySize) {
                            player.inventory.add(o);
                        }

                        // Remove from world
                        obj[i] = null;

                        // RETURN picked item name
                        return o.name;
                    }
                }
            }
        }

        return null; // nothing picked
    }
}
