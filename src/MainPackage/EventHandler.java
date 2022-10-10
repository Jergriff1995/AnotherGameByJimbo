package MainPackage;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];
    int previousEventX;
    int previousEventY;
    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
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
            if(hit(21, 16, "down") == true){healingPool(gp.dialogueState);}
            if(hit(22, 16, "down") == true){healingPool(gp.dialogueState);}
            if(hit(20, 18, "right") == true){healingPool(gp.dialogueState);}
            if(hit(21, 19, "up") == true){healingPool(gp.dialogueState);}
            if(hit(22, 19, "up") == true){healingPool(gp.dialogueState);}
            if(hit(23, 18, "left") == true){healingPool(gp.dialogueState);}

            if(hit(53, 18, "down") == true){healingPool(gp.dialogueState);}
            if(hit(54, 18, "down") == true){healingPool(gp.dialogueState);}
            if(hit(55, 20, "right") == true){healingPool(gp.dialogueState);}
            if(hit(53, 21, "up") == true){healingPool(gp.dialogueState);}
            if(hit(54, 21, "up") == true){healingPool(gp.dialogueState);}
            if(hit(52, 20, "left") == true){healingPool(gp.dialogueState);}
        }



    }
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }
    public void damagePit(int col, int row, int gameState){
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

        }
    }
}
