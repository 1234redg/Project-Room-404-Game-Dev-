package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_DocCarpet extends SuperObject{
	
	public Obj_DocCarpet() {

	    name = "DocCarpet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/bed carpet_docROOM.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 195;
	    height = 120;
	}

}
