package MainPackage;

import Entity.Hoodie;
import Entity.Johnny;
import Entity.Tanner;
import Monster.*;
import Object.*;
import Tiles_Interactive.IT_DryTree;

public class AssetSetter { // this class is responsible for displaying objects in the game.

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){ // this method creates objects and places them on the map.
        int i = 0;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 36;
        gp.obj[i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[i] = new OBJ_MPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 36;
        gp.obj[i].worldY = gp.tileSize * 16;
        i++;

        gp.obj[i] = new OBJ_Gem1(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[i] = new OBJ_Gem2(gp);
        gp.obj[i].worldX = gp.tileSize * 34;
        gp.obj[i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[i] = new OBJ_Gem2(gp);
        gp.obj[i].worldX = gp.tileSize * 34;
        gp.obj[i].worldY = gp.tileSize * 17;
        i++;

        gp.obj[i] = new OBJ_Gem3(gp);
        gp.obj[i].worldX = gp.tileSize * 34;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;


    }

    public void setNPC(){

        gp.npc[0] = new Tanner(gp);
        gp.npc[0].worldX = gp.tileSize*57;
        gp.npc[0].worldY = gp.tileSize*21;


        gp.npc[1] = new Johnny(gp);
        gp.npc[1].worldX = gp.tileSize*59;
        gp.npc[1].worldY = gp.tileSize*21;

        gp.npc[2] = new Hoodie(gp);
        gp.npc[2].worldX = gp.tileSize*58;
        gp.npc[2].worldY = gp.tileSize*21;

    }

    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*49;
        gp.monster[i].worldY = gp.tileSize*25;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*53;
        gp.monster[i].worldY = gp.tileSize*27;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*57;
        gp.monster[i].worldY = gp.tileSize*27;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*56;
        gp.monster[i].worldY = gp.tileSize*28;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*59;
        gp.monster[i].worldY = gp.tileSize*27;
        i++;

        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*52;
        gp.monster[i].worldY = gp.tileSize*22;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*51;
        gp.monster[i].worldY = gp.tileSize*20;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*53;
        gp.monster[i].worldY = gp.tileSize*24;
        i++;
        gp.monster[i] = new MON_Wizzy(gp);
        gp.monster[i].worldX = gp.tileSize*52;
        gp.monster[i].worldY = gp.tileSize*27;
    }
    public void setInteractiveTile(){
        int i = 0;
        gp.iTile[i] = new IT_DryTree(gp, 37, 20 );
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 37, 21 );
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 37, 22 );
        i++;

    }
}
