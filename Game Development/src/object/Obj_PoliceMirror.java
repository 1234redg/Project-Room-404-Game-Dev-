package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_PoliceMirror extends SuperObject {
	
	public Obj_PoliceMirror() {

	    name = "policeMirror";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/SET_MIRROR_PLANT.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 40;
	    height = 120;
	}

}
