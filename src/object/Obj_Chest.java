package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject{

    public Obj_Chest(){

        name = "Chest";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Chest_1.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
