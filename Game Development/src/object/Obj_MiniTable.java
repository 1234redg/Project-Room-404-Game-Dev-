package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_MiniTable extends SuperObject {
	
	public Obj_MiniTable() {

	    name = "MiniTable";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/PORTRAIT PIC.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of object
	    width = 50;
	    height = 70;
	}

}
