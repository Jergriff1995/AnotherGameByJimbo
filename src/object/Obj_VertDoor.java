package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_VertDoor extends SuperObject{

    public Obj_VertDoor(){

        name = "VertDoor";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Door_4.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
