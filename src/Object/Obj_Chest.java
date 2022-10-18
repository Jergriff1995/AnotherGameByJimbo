package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class Obj_Chest extends Entity {



    public Obj_Chest(GamePanel gp){
        super(gp);

        name = "Chest";
        down1 = setUp("/res/Objects/Chest_1", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
