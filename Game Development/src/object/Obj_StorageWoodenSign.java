package object;

public class Obj_StorageWoodenSign extends SuperObject {
    
    public Obj_StorageWoodenSign() {

        name = "StorageWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 167.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();

        }
        width = 100;
        height = 100;
    }

}
