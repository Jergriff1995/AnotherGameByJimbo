package MainPackage;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];
    int previousEventX;
    int previousEventY;
    int tempMap;
    int tempCol;
    int tempRow;

    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

    }
    public void checkEvent(){
        //Check if the player is more than one tile away from the last event.
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true){
            if(hit(0,21, 16, "down") == true){healingPool(gp.dialogueState);}
            else if(hit(0,22, 16, "down") == true){healingPool(gp.dialogueState);}
            else if(hit(0,20, 18, "right") == true){healingPool(gp.dialogueState);}
            else if(hit(0,21, 19, "up") == true){healingPool(gp.dialogueState);}
            else if(hit(0,22, 19, "up") == true){healingPool(gp.dialogueState);}
            else if(hit(0,23, 18, "left") == true){healingPool(gp.dialogueState);}

            else if(hit(0,53, 18, "down") == true){healingPool(gp.dialogueState);}
            else if(hit(0,54, 18, "down") == true){healingPool(gp.dialogueState);}
            else if(hit(0,55, 20, "right") == true){healingPool(gp.dialogueState);}
            else if(hit(0,53, 21, "up") == true){healingPool(gp.dialogueState);}
            else if(hit(0,54, 21, "up") == true){healingPool(gp.dialogueState);}
            else if(hit(0,52, 20, "left") == true){healingPool(gp.dialogueState);}

            //ENTER AND EXIT MATT'S HOUSE
            //EXIT
            else if(hit(1,48, 56, "any") == true){teleport(0, 22, 17);}
            else if(hit(1,47, 56, "any") == true){teleport(0, 22, 17);}
            //ENTER
            else if(hit(0,22, 17, "any") == true){teleport(1, 48, 56);}


            //ENTER AND EXIT SHOP
            else if(hit(0,71, 36, "any") == true){teleport(2, 53, 61);}
            else if(hit(2,53, 61, "any") == true){teleport(0, 71, 36);}


            //JOHNNY'S HOUSE
            else if(hit(0,52, 38, "any") == true){teleport(3, 21, 31);}
            else if(hit(0,51, 38, "any") == true){teleport(3, 21, 31);}
            else if(hit(3,21, 31, "any") == true){teleport(0, 53, 39);}

            //TANNER'S HOUSE
            else if(hit(0,76, 43, "any") == true){teleport(4, 21, 31);}
            else if(hit(4,21, 31, "any") == true){teleport(0, 76, 43);}

        }



    }
    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;

        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.playSoundEffect(11);
        gp.ui.currentDialogue = "You fell into a pit! ##Ouch!";
        gp.player.life -= 1;
        canTouchEvent = false;
        //eventRect[col][row] = true;
    }

    public void healingPool(int gameState){
        if(gp.keyHandler.enterPressed == true){
            gp.playSoundEffect(3);
            gp.player.attackCancelled = true;
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink some water. ##You are feeling better!";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            //Respawn all enemies;
            //gp.assetSetter.setMonster();

        }
    }

    public void teleport(int map, int col, int row){
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSoundEffect(21);

    }
}
