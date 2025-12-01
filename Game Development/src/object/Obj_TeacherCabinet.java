package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_TeacherCabinet extends SuperObject {
	
	public Obj_TeacherCabinet() {

	    name = "TeacherCabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Cabinets 3.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 330;
	    height = 140;
	}

}
