package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Ptable extends SuperObject {

	public Obj_Ptable() {

	    name = "Ptable";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 41.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 185;
	    height = 100;
	}

}
