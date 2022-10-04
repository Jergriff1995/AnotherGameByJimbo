package object;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_VertDoor extends SuperObject{

    GamePanel gp;

    public Obj_VertDoor(GamePanel gp){
        this.gp = gp;

        name = "VertDoor";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Door_4.png"));
            utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
