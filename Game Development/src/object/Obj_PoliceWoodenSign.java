package object;

public class Obj_PoliceWoodenSign  extends SuperObject {
    
    public Obj_PoliceWoodenSign() {

        name = "PoliceWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 171.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        width = 100;
        height = 100;

    }

}
