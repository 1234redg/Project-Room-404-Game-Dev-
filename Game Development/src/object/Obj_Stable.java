package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Stable extends SuperObject {
	
	public Obj_Stable() {

	    name = "Stable";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 55.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of object
	    width = 140;
	    height = 170;
	}

}
