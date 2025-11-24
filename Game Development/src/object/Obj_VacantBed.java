package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_VacantBed extends SuperObject {
	
	public Obj_VacantBed() {

	    name = "VacantBed";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 60.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 240;
	    height = 240;
	}

}
