package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_NurseTv extends SuperObject {
	
	public Obj_NurseTv() {

	    name = "NurseTv";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/TV SET W PLANT.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of object
	    width = 90;
	    height = 65;
	}

}
