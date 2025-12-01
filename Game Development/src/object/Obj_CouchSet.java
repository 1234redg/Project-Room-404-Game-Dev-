package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_CouchSet extends SuperObject {
	
	public Obj_CouchSet() {

	    name = "couchSet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 142.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 200;
	    height = 150;
	}

}
