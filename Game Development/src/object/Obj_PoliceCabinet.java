package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_PoliceCabinet extends SuperObject {
	
	public Obj_PoliceCabinet() {

	    name = "PoliceCabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/wardrobes_CABINET.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 180;
	    height = 190;
	}

}
