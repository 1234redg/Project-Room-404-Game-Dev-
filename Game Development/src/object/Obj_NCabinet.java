package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_NCabinet extends SuperObject {
	
	public Obj_NCabinet() {

	    name = "NCabinet";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/CABINET WITH MIRROR BEHIND_SET.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 130;
	    height = 120;
	}

}
