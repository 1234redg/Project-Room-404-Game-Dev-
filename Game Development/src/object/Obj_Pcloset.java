package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Pcloset extends SuperObject {
	
	public Obj_Pcloset() {

	    name = "Pcloset";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 40.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 380;
	    height = 120;
	}


}
