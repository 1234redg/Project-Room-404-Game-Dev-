package object;

public class Obj_DoctorWoodenSign extends SuperObject {
    
    public Obj_DoctorWoodenSign() {

        name = "DoctorWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 168.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        width = 100;
        height = 100;

}

}
