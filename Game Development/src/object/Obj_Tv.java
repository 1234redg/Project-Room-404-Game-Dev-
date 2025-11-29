package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Tv extends SuperObject {
    
    public Obj_Tv() {

        name = "Tv";


         try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/Group 136.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        // adjust the size of the objects
       width = 200;
	    height = 100;
    }

}
