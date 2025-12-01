package object;

public class Obj_PWoodenSign extends SuperObject {
    
    public Obj_PWoodenSign() {

        name = "PWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 173.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        width = 100;
        height = 100;
    }

}
