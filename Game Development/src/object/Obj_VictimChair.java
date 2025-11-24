package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_VictimChair extends SuperObject {
	
	public Obj_VictimChair() {

	    name = "VictimChair";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/SET_TABLE_AND_CHAIR.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 185;
	    height = 90;
	}

}
