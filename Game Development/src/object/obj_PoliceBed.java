package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class obj_PoliceBed extends SuperObject {
	
	public obj_PoliceBed() {

	    name = "PoliceBed";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/SET_BED_CABINETS_LAMPS_CARPET.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 240;
	    height = 240;
	}

}
