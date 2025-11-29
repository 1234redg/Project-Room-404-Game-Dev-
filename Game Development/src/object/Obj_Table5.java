package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Table5 extends SuperObject {
    
    public Obj_Table5() {

        name = "Table5";
        try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 115.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 300;
	    height = 160;
	}

}
