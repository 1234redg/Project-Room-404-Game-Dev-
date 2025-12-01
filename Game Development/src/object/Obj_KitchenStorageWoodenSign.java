package object;

public class Obj_KitchenStorageWoodenSign extends   SuperObject {
    
    public Obj_KitchenStorageWoodenSign() {

        name = "KitchenStorageWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 175.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        width = 100;
        height = 100;
    }

}
