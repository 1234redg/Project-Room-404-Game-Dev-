package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Fkitchentable extends SuperObject {

    public Obj_Fkitchentable() {

        name = "Fkitchentable";
try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 144.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust size of the object
	    width = 180;
	    height = 200;
    }

}
