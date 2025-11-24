package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_deco2 extends SuperObject{
	
	public Obj_deco2() {

	    name = "deco2";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/HANGING.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 30;
	    height = 105;
	}

}
