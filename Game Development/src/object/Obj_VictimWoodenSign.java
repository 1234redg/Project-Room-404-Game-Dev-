package object;

public class Obj_VictimWoodenSign extends SuperObject {
    
    public Obj_VictimWoodenSign() {

        name = "VictimWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 174.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        width = 100;
        height = 100;

    }

}
