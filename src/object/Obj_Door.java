package object;

import Entity.Entity;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends Entity {


    public Obj_Door(GamePanel gp){
      super(gp);
      name = "Door";
      down1 = setUp("/res/Objects/Door_1", gp.tileSize, gp.tileSize);
      collision = true;

      solidArea.x = 0;
      solidArea.y = 16;
      solidArea.width = 48;
      solidArea.height = 32;
      solidAreaDefaultX = solidArea.x;
      solidAreaDefaultY = solidArea.y;
    }
}
