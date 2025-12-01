package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;
import java.awt.Rectangle;

public class Obj_SRKey extends SuperObject {
    
    public Obj_SRKey() {

        name = "key";
        try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width =  30;
	    height = 30;
	    
        offsetX = -13;   // move 8 pixels left
        offsetY = 0;    // keep vertical position
	}

}
