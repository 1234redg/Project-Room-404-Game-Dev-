package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_KitchenTable2 extends SuperObject {
	
	public Obj_KitchenTable2() {

	    name = "table";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 92.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of object
	    width = 160;
	    height = 170;
	}

}
