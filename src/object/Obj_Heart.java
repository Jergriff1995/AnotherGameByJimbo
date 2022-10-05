package object;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Heart extends SuperObject{
    GamePanel gp;

    public Obj_Heart(GamePanel gp){
        this.gp = gp;



        name = "Heart";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Heart1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Heart2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Heart3.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = utilityTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = utilityTool.scaleImage(image3, gp.tileSize, gp.tileSize);


        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
