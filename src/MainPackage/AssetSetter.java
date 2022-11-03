package MainPackage;

import Entity.Chase;
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

        mapNum = 1;
        gp.obj[mapNum][i] = new OBJ_Chair(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*48;
        gp.obj[mapNum][i].worldY = gp.tileSize*51;
        i++;

        gp.obj[mapNum][i] = new Obj_Chest(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*43;
        gp.obj[mapNum][i].worldY = gp.tileSize*50;
        i++;

        gp.obj[mapNum][i] = new OBJ_Table(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*48;
        gp.obj[mapNum][i].worldY = gp.tileSize*52;
        i++;

        gp.obj[mapNum][i] = new OBJ_Dresser(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*44;
        gp.obj[mapNum][i].worldY = gp.tileSize*50;
        i++;



    }

    public void setNPC(){
        int mapNum = 0;

        gp.npc[mapNum][0] = new Hoodie(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize*63;
        gp.npc[mapNum][0].worldY = gp.tileSize*37;

        mapNum = 2;
        gp.npc[mapNum][2] = new Chase(gp);
        gp.npc[mapNum][2].worldX = gp.tileSize*53;
        gp.npc[mapNum][2].worldY = gp.tileSize*55;

        mapNum = 3;
        gp.npc[mapNum][2] = new Johnny(gp);
        gp.npc[mapNum][2].worldX = gp.tileSize*20;
        gp.npc[mapNum][2].worldY = gp.tileSize*27;

        mapNum = 4;
        gp.npc[mapNum][2] = new Tanner(gp);
        gp.npc[mapNum][2].worldX = gp.tileSize*20;
        gp.npc[mapNum][2].worldY = gp.tileSize*27;




    }

    public void setMonster(){
        int i = 0;
        int mapNum = 0;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*50;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*51;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;
        gp.monster[mapNum][i] = new MON_TSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*53;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;
        gp.monster[mapNum][i] = new MON_TSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*54;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;
        gp.monster[mapNum][i] = new MON_PSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*55;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;

        gp.monster[mapNum][i] = new MON_PSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*56;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*80;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*80;
        gp.monster[mapNum][i].worldY = gp.tileSize*61;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*80;
        gp.monster[mapNum][i].worldY = gp.tileSize*62;
        i++;
        gp.monster[mapNum][i] = new MON_Aorc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*85;
        gp.monster[mapNum][i].worldY = gp.tileSize*68;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*78;
        gp.monster[mapNum][i].worldY = gp.tileSize*68;
        i++;
        gp.monster[mapNum][i] = new MON_Rorc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*76;
        gp.monster[mapNum][i].worldY = gp.tileSize*68;
        i++;
        gp.monster[mapNum][i] = new MON_Wizzy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*70;
        i++;
        gp.monster[mapNum][i] = new MON_Knight(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*40;
        gp.monster[mapNum][i].worldY = gp.tileSize*80;
        i++;
        gp.monster[mapNum][i] = new MON_Knight(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*52;
        gp.monster[mapNum][i].worldY = gp.tileSize*81;
        i++;
        gp.monster[mapNum][i] = new MON_Knight(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*81;
    }
    public void setInteractiveTile(){
//        int i = 0;
//        int mapNum = 0;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 20 );
//        i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 21 );
//        i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 22 );
//        i++;

    }
}
