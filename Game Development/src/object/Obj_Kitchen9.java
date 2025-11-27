package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Kitchen9 extends SuperObject {
	
	
	public Obj_Kitchen9() {

	    name = "kitchen9";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 93.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 80;
	    height = 400;
	}
	

}
