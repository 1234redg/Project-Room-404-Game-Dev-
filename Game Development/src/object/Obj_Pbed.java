package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Pbed extends SuperObject {
	
	public Obj_Pbed() {

	    name = "Pbed";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 39.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 230;
	    height = 200;
	}

}
