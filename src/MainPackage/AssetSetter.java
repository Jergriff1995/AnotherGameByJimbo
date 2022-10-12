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
        gp.npc[0].worldX = gp.tileSize*57;
        gp.npc[0].worldY = gp.tileSize*21;


        gp.npc[1] = new Johnny(gp);
        gp.npc[1].worldX = gp.tileSize*59;
        gp.npc[1].worldY = gp.tileSize*21;

    }

    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize*49;
        gp.monster[i].worldY = gp.tileSize*25;
        i++;
        gp.monster[i] = new MON_Beast(gp);
        gp.monster[i].worldX = gp.tileSize*53;
        gp.monster[i].worldY = gp.tileSize*25;
        i++;
        gp.monster[i] = new MON_Beast(gp);
        gp.monster[i].worldX = gp.tileSize*53;
        gp.monster[i].worldY = gp.tileSize*27;
        gp.monster[i] = new MON_Beast(gp);
        gp.monster[i].worldX = gp.tileSize*53;
        gp.monster[i].worldY = gp.tileSize*27;
        i++;
        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize*52;
        gp.monster[i].worldY = gp.tileSize*24;
        i++;
        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize*53;
        gp.monster[i].worldY = gp.tileSize*20;
    }
}
