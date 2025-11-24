package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Bath1 extends SuperObject {
		
		public Obj_Bath1() {

		    name = "Bath1";

		    try {
		        image = ImageIO.read(getClass().getResourceAsStream("/objects/BATHSET1.png"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    // adjust size of the object
		    width = 180;
		    height = 150;
		}

}
