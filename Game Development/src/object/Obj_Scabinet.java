package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Scabinet extends SuperObject {
	
	public Obj_Scabinet() {

	    name = "Scabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 54.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 150;
	    height = 350;
	}

}
