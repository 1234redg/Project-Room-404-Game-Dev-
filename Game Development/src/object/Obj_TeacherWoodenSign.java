package object;

public class Obj_TeacherWoodenSign extends SuperObject {
    
    public Obj_TeacherWoodenSign() {

        name = "TeacherWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 166.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        // Adjust the size of the object
        width = 100;
        height = 100;
    }

}
