package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class Obj_VertDoor extends Entity {



    public Obj_VertDoor(GamePanel gp){
        super(gp);
        name = "VertDoor";
        down1 = setUp("/res/Objects/Door_4", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
