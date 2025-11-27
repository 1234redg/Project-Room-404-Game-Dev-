package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_KitchenSink extends SuperObject {
	
	 
		public Obj_KitchenSink() {

		    name = "Sink";

		    try {
		        image = ImageIO.read(getClass().getResourceAsStream("/objects/middle.png"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    // Adjust the size of the object
		    width = 290;
		    height = 120;
		}

}
