package object;

public class Obj_KitchenWoodenSign  extends SuperObject {
    
    public Obj_KitchenWoodenSign() {

        name = "KitchenWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Frame 169.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();  
        }
        width = 100;
        height = 100;
    }  

}
