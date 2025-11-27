package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_KitchenCabinet extends SuperObject {
	
	public Obj_KitchenCabinet() {

	    name = "kitchenCabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Cabinets 2.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 245;
	    height = 150;
	}

}
