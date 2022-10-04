package MainPackage;

import Entity.Bean;
import Entity.NPC_One;
import Entity.Tanner;
import object.*;

public class AssetSetter { // this class is responsible for displaying objects in the game.

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){ // this method creates objects and places them on the map.
//        gp.obj[0] = new Obj_Door(gp);
//        gp.obj[0].worldX = gp.tileSize*58;
//        gp.obj[0].worldY = gp.tileSize*33;
//
//        gp.obj[1] = new Obj_Key(gp);
//        gp.obj[1].worldX = gp.tileSize*61;
//        gp.obj[1].worldY = gp.tileSize*30;

    }

    public void setNPC(){

        gp.npc[0] = new NPC_One(gp);
        gp.npc[0].worldX = gp.tileSize*60;
        gp.npc[0].worldY = gp.tileSize*50;

        gp.npc[1] = new Tanner(gp);
        gp.npc[1].worldX = gp.tileSize*58;
        gp.npc[1].worldY = gp.tileSize*55;

        gp.npc[2] = new NPC_One(gp);
        gp.npc[2].worldX = gp.tileSize*62;
        gp.npc[2].worldY = gp.tileSize*55;

    }
}
