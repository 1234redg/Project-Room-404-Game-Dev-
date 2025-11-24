package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_CrSink extends SuperObject {
	
	public Obj_CrSink() {

	    name = "CrSink";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 67.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 150;
	    height = 100;
	}

}
