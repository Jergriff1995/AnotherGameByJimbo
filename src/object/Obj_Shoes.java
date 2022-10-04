package object;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Shoes extends SuperObject{

    GamePanel gp;

    public Obj_Shoes(GamePanel gp){
        this.gp = gp;

        name = "Shoes";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Shoes_1.png"));
            utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
