package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Kitchen4 extends SuperObject{
	
	public Obj_Kitchen4() {

	    name = "kitchen4";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/left (1).png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 70;
	    height = 150;
	}

}
