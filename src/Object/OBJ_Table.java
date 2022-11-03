package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Table extends Entity {



    public OBJ_Table(GamePanel gp){
        super(gp);

        name = "Chest";
        type = 8;
        down1 = setUp("/res/Objects/1Table", gp.tileSize, gp.tileSize);
        collision = true;
    }
}