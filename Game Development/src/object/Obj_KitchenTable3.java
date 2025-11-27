package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_KitchenTable3 extends SuperObject {
	
	public Obj_KitchenTable3() {

	    name = "table3";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 91 (1).png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of object
	    width = 220;
	    height = 180;
	}

}
