package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;

public class Obj_Handcuffs extends SuperObject {
    
    public Obj_Handcuffs() {
        
        name = "Handcuffs";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Handcuffs.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 30;
	    height = 30;
	    
        offsetX = -15;   // move 8 pixels left
        offsetY = 0;    // keep vertical position
        
        // Auto-adjust hitbox based on width and height
        autoAdjustHitbox();
	}
}
    


