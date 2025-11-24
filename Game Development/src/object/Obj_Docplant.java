package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Docplant extends SuperObject {
	
	public Obj_Docplant() {

	    name = "Docplant";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/plant_doc_room.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 75;
	    height = 70;
	}

}
