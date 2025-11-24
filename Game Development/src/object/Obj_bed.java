package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_bed extends SuperObject {

	public Obj_bed() {

	    name = "bed";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Bed Set_doc.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 200;
	    height = 150;
	}

}
