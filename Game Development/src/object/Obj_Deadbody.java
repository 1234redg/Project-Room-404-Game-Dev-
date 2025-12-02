package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Deadbody extends SuperObject {

    public Obj_Deadbody() {

        name = "Deadbody";

         try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/deadbody.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        // adjust size of the object
        width = 100;
        height = 48;
    }

}
