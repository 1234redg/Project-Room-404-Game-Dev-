package object;

public class Obj_VacantRoomWoodenSign extends SuperObject {
    
    public Obj_VacantRoomWoodenSign() {

        name = "VacantRoomWoodenSign";

        
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/objects/Group 170.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        width = 100;
        height = 100;

    }

}
