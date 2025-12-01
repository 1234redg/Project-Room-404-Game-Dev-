package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;

public class Obj_Watch  extends SuperObject {

    public Obj_Watch() {

        name = "Watch";

       try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Watch.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 60;
	    height = 60;
	    
        offsetX = -30;   // move 8 pixels left
        offsetY = 16;    // keep vertical position
	}
}
