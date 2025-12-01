package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_storagetable extends SuperObject {
    
    public Obj_storagetable() {

        name = "storagetable";
         try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/storage table.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        // adjust size of the object
        width = 150;
        height = 100;
    }   

}
