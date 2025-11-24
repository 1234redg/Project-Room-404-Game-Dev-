package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_DocCabinet extends SuperObject {
	
	public Obj_DocCabinet() {

	    name = "DocCabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/book cabinet_left_DOCroom.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Adjust the size of the object
	    width = 130;
	    height = 120;
	}

}
