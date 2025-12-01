package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_NurseTable extends SuperObject {
	
	public Obj_NurseTable() {

	    name = "NurseTable";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/nurse table.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 200;
	    height = 110;
	}


}
