package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class Obj_Shoes extends Entity {

    public Obj_Shoes(GamePanel gp){
        super(gp);
        name = "Shoes";
        down1 = setUp("/res/Objects/Shoes_1", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
