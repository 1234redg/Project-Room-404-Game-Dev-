package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Bath2 extends SuperObject {
	
	public Obj_Bath2() {

	    name = "Bath2";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/BATHSET2.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 180;
	    height = 150;
	}

}
