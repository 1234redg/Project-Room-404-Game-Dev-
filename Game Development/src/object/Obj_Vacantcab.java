package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Vacantcab extends SuperObject{
	
	public Obj_Vacantcab() {

	    name = "Vacantcab";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 58.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 270;
	    height = 150;
	}

}
