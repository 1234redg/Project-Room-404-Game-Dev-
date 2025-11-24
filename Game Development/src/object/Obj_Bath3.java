package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Bath3 extends SuperObject{
	
	public Obj_Bath3() {

	    name = "Bath3";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/BATHSET3.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 180;
	    height = 150;
	}

}
