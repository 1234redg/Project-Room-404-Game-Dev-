package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Stable2 extends SuperObject{
	
	public Obj_Stable2() {

	    name = "Stable2";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 53.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of object
	    width = 200;
	    height = 140;
	}

}
