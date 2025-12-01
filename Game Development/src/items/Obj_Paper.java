package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;

public class Obj_Paper extends SuperObject{

    public Obj_Paper() {

	    name = "Paper";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Paper.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 30;
	    height = 30;
	    
        offsetX = -13;   // move 8 pixels left
        offsetY = 8;    // keep vertical position
        
        // Auto-adjust hitbox based on width and height
        autoAdjustHitbox();
	}

}
