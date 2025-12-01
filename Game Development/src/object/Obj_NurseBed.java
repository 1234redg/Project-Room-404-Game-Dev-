package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_NurseBed extends SuperObject {

	
	public Obj_NurseBed() {

	    name = "NurseBed";
	    
	             

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/nurse bed.png"));
	        width = image.getWidth();
            height = image.getHeight();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 240;
	    height = 240;
	}
}
