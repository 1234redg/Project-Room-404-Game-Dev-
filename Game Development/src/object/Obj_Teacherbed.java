package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Teacherbed extends SuperObject{
	
	public Obj_Teacherbed() {

	    name = "Teacherbed";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/BED_SET_LAMPS_CABINET_CARPET.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 270;
	    height = 250;
	}

}
