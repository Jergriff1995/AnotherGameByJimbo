package object;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends  SuperObject{ //represents the "Key" object
    GamePanel gp;

    public Obj_Key(GamePanel gp){
        this.gp = gp;



        name = "Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Key_1.png"));
            utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
