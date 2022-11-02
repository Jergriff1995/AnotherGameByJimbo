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
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 36;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNum][i] = new OBJ_MPotion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 36;
        gp.obj[mapNum][i].worldY = gp.tileSize * 16;
        i++;

        gp.obj[mapNum][i] = new OBJ_Gem1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNum][i] = new OBJ_Gem2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 34;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNum][i] = new OBJ_Gem2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 34;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;

        gp.obj[mapNum][i] = new OBJ_Gem3(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 34;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;


        mapNum = 1;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 46;
        gp.obj[mapNum][i].worldY = gp.tileSize * 55;
        i++;


    }

    public void setNPC(){
        int mapNum = 0;
        gp.npc[mapNum][0] = new Tanner(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize*57;
        gp.npc[mapNum][0].worldY = gp.tileSize*21;


        gp.npc[mapNum][1] = new Johnny(gp);
        gp.npc[mapNum][1].worldX = gp.tileSize*59;
        gp.npc[mapNum][1].worldY = gp.tileSize*21;

        gp.npc[mapNum][2] = new Hoodie(gp);
        gp.npc[mapNum][2].worldX = gp.tileSize*58;
        gp.npc[mapNum][2].worldY = gp.tileSize*21;

        mapNum = 1;
        gp.npc[mapNum][2] = new Hoodie(gp);
        gp.npc[mapNum][2].worldX = gp.tileSize*48;
        gp.npc[mapNum][2].worldY = gp.tileSize*55;

    }

    public void setMonster(){
        int i = 0;
        int mapNum = 0;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*49;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*53;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*57;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*56;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*59;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
        i++;

        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*52;
        gp.monster[mapNum][i].worldY = gp.tileSize*22;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*51;
        gp.monster[mapNum][i].worldY = gp.tileSize*20;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*53;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*52;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
    }
    public void setInteractiveTile(){
        int i = 0;
        int mapNum = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 20 );
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 21 );
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 22 );
        i++;

    }
}
