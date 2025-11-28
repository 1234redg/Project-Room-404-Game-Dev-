package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Portrait extends SuperObject {
    
    public Obj_Portrait() {

        name = "Portrait";

         try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 137.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

        // adjust the size of the objects
        width = 400;
	    height = 70;

    }

}
