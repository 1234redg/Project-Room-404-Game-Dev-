package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_PoliceCouch extends SuperObject {
	
	public Obj_PoliceCouch() {

	    name = "PoliceCouch";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/police couch.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 150;
	    height = 185;
	}

}
