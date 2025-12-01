package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_NurseWoodenSign  extends SuperObject {
    
    public Obj_NurseWoodenSign() {

        name = "NurseWoodenSign";

        
	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/nurse sign.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

        // Adjust the size of the object
        width = 100;
        height = 100;
    }   

}
