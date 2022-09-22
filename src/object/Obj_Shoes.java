package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Shoes extends SuperObject{

    public Obj_Shoes(){

        name = "Shoes";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Shoes_1.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
