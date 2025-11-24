package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_docPictureFrame extends SuperObject{
	
	public Obj_docPictureFrame() {

	    name = "docPictureFrame";

	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/objects/pictureFRAME_docROOM.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Best proportional size for 48px tiles
	    width = 95;
	    height = 50;
	}

}
