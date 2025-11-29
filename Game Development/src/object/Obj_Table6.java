package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Table6 extends Obj_Table5 {
    
    public Obj_Table6() {
        name = "Table6";
        try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 135 (2).png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 290;
	    height = 100;
	}

}
