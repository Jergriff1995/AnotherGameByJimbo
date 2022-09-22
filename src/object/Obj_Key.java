package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends  SuperObject{ //represents the "Key" object

    public Obj_Key(){

        name = "Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Key_1.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
