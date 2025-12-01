package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_TableSet extends SuperObject {
	
	public Obj_TableSet() {

	    name = "TableSet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 143.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 200;
	    height = 120;
	}

}
