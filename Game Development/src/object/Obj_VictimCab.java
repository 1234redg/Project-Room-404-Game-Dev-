package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_VictimCab extends SuperObject{
	
	public Obj_VictimCab() {

	    name = "VictimCab";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/victim closet set.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 600;
	    height = 150;
	}

}
