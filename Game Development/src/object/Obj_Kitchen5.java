package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Kitchen5 extends SuperObject {

	
	public Obj_Kitchen5() {

	    name = "kitchen5";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/down right.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 140;
	    height = 100;
	}
}
