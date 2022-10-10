package MainPackage;

import Entity.Johnny;
import Entity.Tanner;
import Monster.MON_Beast;
import Monster.MON_Slime;
import object.*;

public class AssetSetter { // this class is responsible for displaying objects in the game.

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){ // this method creates objects and places them on the map.


    }

    public void setNPC(){

        gp.npc[0] = new Tanner(gp);
        gp.npc[0].worldX = gp.tileSize*58;
        gp.npc[0].worldY = gp.tileSize*55;


        gp.npc[1] = new Johnny(gp);
        gp.npc[1].worldX = gp.tileSize*59;
        gp.npc[1].worldY = gp.tileSize*55;

    }

    public void setMonster(){
        gp.monster[0] = new MON_Slime(gp);
        gp.monster[0].worldX = gp.tileSize*65;
        gp.monster[0].worldY = gp.tileSize*32;

        gp.monster[1] = new MON_Beast(gp);
        gp.monster[1].worldX = gp.tileSize*53;
        gp.monster[1].worldY = gp.tileSize*35;

        gp.monster[2] = new MON_Slime(gp);
        gp.monster[2].worldX = gp.tileSize*52;
        gp.monster[2].worldY = gp.tileSize*44;

        gp.monster[3] = new MON_Slime(gp);
        gp.monster[3].worldX = gp.tileSize*63;
        gp.monster[3].worldY = gp.tileSize*51;
    }
}
