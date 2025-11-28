package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_cab extends SuperObject {
    
    public Obj_cab() {

        name = "cabinet";

        try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 138.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 75;
	    height = 670;
	}
    }


