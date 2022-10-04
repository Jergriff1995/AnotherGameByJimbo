package object;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject{

    GamePanel gp;

    public Obj_Chest(GamePanel gp){
        this.gp = gp;

        name = "Chest";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Chest_1.png"));
            utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
