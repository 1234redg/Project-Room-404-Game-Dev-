package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Knife extends SuperObject {

	
	public Obj_Knife() {

	    name = "Knife";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Knife.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 30;
	    height = 30;
	}
}
