package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;

public class Obj_Flashlight extends SuperObject {

    public Obj_Flashlight() {

	    name = "Flashlight";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/flashlight.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 40;
	    height = 30;
	    
        offsetX = -30;   // move 8 pixels left
        offsetY = 16;    // keep vertical position
        
        // Auto-adjust hitbox based on width and height
        autoAdjustHitbox();
	}

}
