package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class Obj_Heart extends Entity {


    public Obj_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = setUp("/res/Objects/Heart1", gp.tileSize, gp.tileSize);
        image2 = setUp("/res/Objects/Heart2", gp.tileSize, gp.tileSize);
        image3 = setUp("/res/Objects/Heart3", gp.tileSize, gp.tileSize);

    }
}
