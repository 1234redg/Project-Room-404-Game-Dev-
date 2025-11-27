package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Scabinet extends SuperObject {
	
	public Obj_Scabinet() {

	    name = "Scabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 95.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 770;
	    height = 200;
	}

}
