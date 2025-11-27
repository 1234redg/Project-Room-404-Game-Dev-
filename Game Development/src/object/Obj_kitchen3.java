package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_kitchen3 extends SuperObject {
	
	public Obj_kitchen3() {

	    name = "kitchen3";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/right2.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 75;
	    height = 335;
	}

}
