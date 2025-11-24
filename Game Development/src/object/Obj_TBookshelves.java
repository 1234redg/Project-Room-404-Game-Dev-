package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_TBookshelves extends SuperObject {
	
	public Obj_TBookshelves() {

	    name = "TBookshelves";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/SET_BOOKSHELVES.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // adjust the size of the objects
	    width = 380;
	    height = 160;
	}

}
