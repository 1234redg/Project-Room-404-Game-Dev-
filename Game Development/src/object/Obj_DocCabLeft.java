package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_DocCabLeft extends SuperObject {
	
	public Obj_DocCabLeft() {

	    name = "DocCabLeft";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/SET_BookSHELVES_cabinet_mirror.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 330;
	    height = 150;
	}

}
