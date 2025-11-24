package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_VictimBed extends SuperObject {
	
	public Obj_VictimBed() {

	    name = "VictimBed";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 57.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 240;
	    height = 240;
	}

}
