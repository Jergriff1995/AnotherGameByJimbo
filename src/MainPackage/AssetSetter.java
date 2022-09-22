package MainPackage;

import object.*;

public class AssetSetter { // this class is responsible for displaying objects in the game.

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){ // this method creates objects and places them on the map.

        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 40 * gp.tileSize;
        gp.obj[0].worldY = 50 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 9 * gp.tileSize;
        gp.obj[1].worldY = 37 * gp.tileSize;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = 25 * gp.tileSize;
        gp.obj[2].worldY = 15 * gp.tileSize;

        gp.obj[3] = new Obj_Chest();
        gp.obj[3].worldX = 24 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new Obj_Shoes();
        gp.obj[4].worldX = 34 * gp.tileSize;
        gp.obj[4].worldY = 30 * gp.tileSize;

        gp.obj[5] = new Obj_VertDoor();
        gp.obj[5].worldX = 15 * gp.tileSize;
        gp.obj[5].worldY = 38 * gp.tileSize;

        gp.obj[6] = new Obj_Door();
        gp.obj[6].worldX = 25 * gp.tileSize;
        gp.obj[6].worldY = 23 * gp.tileSize;

        gp.obj[7] = new Obj_Door();
        gp.obj[7].worldX = 26 * gp.tileSize;
        gp.obj[7].worldY = 19 * gp.tileSize;

        gp.obj[8] = new Obj_Key();
        gp.obj[8].worldX = 35 * gp.tileSize;
        gp.obj[8].worldY = 23 * gp.tileSize;

        gp.obj[9] = new Obj_Key();
        gp.obj[9].worldX = 12 * gp.tileSize;
        gp.obj[9].worldY = 15 * gp.tileSize;





    }
}
