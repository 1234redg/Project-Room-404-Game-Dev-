package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_VictimCab extends SuperObject{
	
	public Obj_VictimCab() {

	    name = "VictimCab";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 56.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 400;
	    height = 150;
	}

}
