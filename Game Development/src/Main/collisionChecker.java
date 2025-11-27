package Main;

import Entity.Entity;
import object.SuperObject;
import java.awt.Rectangle;

public class collisionChecker {

    GamePanel gp;

    public collisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    // --- Tile collision ---
    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.murderTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.murderTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.murderTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.murderTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.murderTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.murderTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.murderTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.murderTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // --- Object collision ---
    public void checkObject(Entity entity, SuperObject[] objects) {

        for (SuperObject obj : objects) {
            if (obj != null) {

                // Entity hitbox in world coordinates
                Rectangle entityRect = new Rectangle(
                        entity.worldX + entity.solidArea.x,
                        entity.worldY + entity.solidArea.y,
                        entity.solidArea.width,
                        entity.solidArea.height
                );

                // Object hitbox in world coordinates
                Rectangle objRect = new Rectangle(
                        obj.worldX + obj.solidArea.x,
                        obj.worldY + obj.solidArea.y,
                        obj.solidArea.width,
                        obj.solidArea.height
                );

                // Check collision
                if (entityRect.intersects(objRect) && obj.collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
    
    
    
    
 // check npc collision
    public int checkEntity(Entity entity, Entity[] target) {
     int index = 999;
     
     for(int i = 0; i < target.length; i++) {
    	 	
    	 	if(target[i] != null) {
    	 		
    	 		entity.solidArea.x = entity.worldX + entity.solidArea.x;
    	 		entity.solidArea.y = entity.worldY + entity.solidArea.y;
    	 		
    	 		target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
    	 		target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
    	 		
    	 		switch(entity.direction) {
    	 		case "up":
    	 			entity.solidArea.y -= entity.speed;
    	 			if(entity.solidArea.intersects(target[i].solidArea)) {   	 				 
    	 					entity.collisionOn = true;
    	 				 index = i;
    	 			}
	 				 break;
    	 		case "down":
    	 			entity.solidArea.y += entity.speed;
    	 			if(entity.solidArea.intersects(target[i].solidArea)) {   	 				 
    	 					entity.collisionOn = true;
    	 				 index = i;
    	 			}
	 				 break;
    	 		case "left":
    	 			entity.solidArea.x -= entity.speed;
    	 			if(entity.solidArea.intersects(target[i].solidArea)) {   	 				 
    	 					entity.collisionOn = true;
    	 				 index = i;
    	 			}
	 				 break;
    	 		case "right":
    	 			entity.solidArea.x += entity.speed;
    	 			if(entity.solidArea.intersects(target[i].solidArea)) {   	 				 
    	 					entity.collisionOn = true;
    	 				 index = i;
    	 				 break;
    	 			}
	 				
    	 		}
    	 	entity.solidArea.x = entity.solidAreaDefaultX;
    	 	entity.solidArea.y = entity.solidAreaDefaultY;
    	 	target[i].solidArea.x = target[i].solidAreaDefaultX;
    	 	target[i].solidArea.y = target[i].solidAreaDefaultY;
    	 		  	 		
    	 	}
     }
     return index; 
  }


    // --- NPC collision check: returns index of NPC collided or -1 ---
    public int checkNPC(Entity entity) {

        for (int i = 0; i < gp.npc.length; i++) {
            Entity target = gp.npc[i];

            if (target != null && target != entity) {

                Rectangle entityRect = new Rectangle(
                        entity.worldX + entity.solidArea.x,
                        entity.worldY + entity.solidArea.y,
                        entity.solidArea.width,
                        entity.solidArea.height
                );

                Rectangle targetRect = new Rectangle(
                        target.worldX + target.solidArea.x,
                        target.worldY + target.solidArea.y,
                        target.solidArea.width,
                        target.solidArea.height
                );

                if (entityRect.intersects(targetRect)) {
                    entity.collisionOn = true;
                    return i; // returns which NPC collided
                }
            }
        }
        return -1;
    }
    
 // --- Check collision between an NPC and the player ---
    public void checkPlayer(Entity entity  ) {
    	
    		entity.solidArea.x = entity.worldX + entity.solidArea.x;
 		entity.solidArea.y = entity.worldY + entity.solidArea.y;
 		
 		 gp.player.solidArea.x = gp.player.worldX +gp.player.solidArea.x;
 		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
 		
 		switch(entity.direction) { 
 		case "up":
 			entity.solidArea.y -= entity.speed;
 			if(entity.solidArea.intersects(gp.player.solidArea)) {   	 				 
 					entity.collisionOn = true;			 
 			}
				 break;
 		case "down":
 			entity.solidArea.y += entity.speed;
 			if(entity.solidArea.intersects(gp.player.solidArea)) {   	 				 
 					entity.collisionOn = true;
 			}
				 break;
 		case "left":
 			entity.solidArea.x -= entity.speed;
 			if(entity.solidArea.intersects(gp.player.solidArea)) {   	 				 
 					entity.collisionOn = true;
 			}
				 break;
 		case "right":
 			entity.solidArea.x += entity.speed;
 			if(entity.solidArea.intersects(gp.player.solidArea)) {   	 				 
 					entity.collisionOn = true;
 				 break;
 			}				
 		}
 	entity.solidArea.x = entity.solidAreaDefaultX;
 	entity.solidArea.y = entity.solidAreaDefaultY;
 	gp.player.solidArea.x =gp.player.solidAreaDefaultX;
 	gp.player.solidArea.y = gp.player.solidAreaDefaultY;
   
    }

        

}
