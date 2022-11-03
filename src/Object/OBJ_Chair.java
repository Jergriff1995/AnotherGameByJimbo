package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Chair extends Entity {



    public OBJ_Chair(GamePanel gp){
        super(gp);

        name = "Chest";
        type = 8;
        down1 = setUp("/res/Objects/1Chair", gp.tileSize, gp.tileSize);
        collision = true;
    }
}