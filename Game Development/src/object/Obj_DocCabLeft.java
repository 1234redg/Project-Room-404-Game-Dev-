package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_DocCabLeft extends SuperObject {
	
	public Obj_DocCabLeft() {

	    name = "DocCabLeft";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/doc cabinet set.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 420;
	    height = 150;
	}

}
