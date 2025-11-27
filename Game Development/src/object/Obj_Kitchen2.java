package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Kitchen2 extends SuperObject {
	
	public Obj_Kitchen2() {

	    name = "kitchen";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/right (1).png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 70;
	    height = 190;
	}

}
