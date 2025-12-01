package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_NurseCab2 extends SuperObject {
	
	public Obj_NurseCab2() {

	    name = "Nurse";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Component 1.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 400;
	    height = 120;
	}

}
