package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Scab2 extends SuperObject {
	
	public Obj_Scab2() {

	    name = "Scab2";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 52.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 770;
	    height = 200;
	}

}
