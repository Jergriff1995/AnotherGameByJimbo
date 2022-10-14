package object;

import Entity.Entity;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends Entity { //represents the "Key" object


    public Obj_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setUp("/res/Objects/Key_1", gp.tileSize, gp.tileSize);
        collision = true;
        description = "[" + name + "]##A mysterious key##I wonder what it unlocks...";
    }
}
