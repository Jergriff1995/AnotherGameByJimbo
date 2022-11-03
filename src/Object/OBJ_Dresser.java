package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Dresser extends Entity {



    public OBJ_Dresser(GamePanel gp){
        super(gp);

        name = "Chest";
        type = 8;
        down1 = setUp("/res/Objects/1Dresser", gp.tileSize, gp.tileSize);
        collision = true;
    }
}