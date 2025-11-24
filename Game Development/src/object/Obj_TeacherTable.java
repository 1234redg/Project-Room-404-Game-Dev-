package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_TeacherTable extends SuperObject {
	
	public Obj_TeacherTable() {

	    name = "TeacherTable";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/DINERSET.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 180;
	    height = 200;
	}

}
