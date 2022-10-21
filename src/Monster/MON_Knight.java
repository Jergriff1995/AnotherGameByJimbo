package Monster;

import Entity.Entity;
import MainPackage.GamePanel;

import java.util.Random;
import java.util.Set;

import Object.OBJ_KnightSword;

public class MON_Knight extends Entity {

    GamePanel gamePanel;


    public MON_Knight(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_Monster;
        name = "Green Knight";
        speed = 2;
        maxLife = 8;
        life = maxLife;
        attack = 4;
        defense = 0;
        exp = 5;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 52;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage(){
        up1 = setUp("/res/Monsters/KnightU1", gamePanel.tileSize+10, gamePanel.tileSize+10);
        up2 = setUp("/res/Monsters/KnightU2", gamePanel.tileSize+10, gamePanel.tileSize+10);
        down1 = setUp("/res/Monsters/KnightD1", gamePanel.tileSize+10, gamePanel.tileSize+10);
        down2 = setUp("/res/Monsters/KnightD2", gamePanel.tileSize+10, gamePanel.tileSize+10);
        left1 = setUp("/res/Monsters/KnightL1", gamePanel.tileSize+10, gamePanel.tileSize+10);
        left2 = setUp("/res/Monsters/KnightL2", gamePanel.tileSize+10, gamePanel.tileSize+10);
        right1 = setUp("/res/Monsters/KnightR1", gamePanel.tileSize+10, gamePanel.tileSize+10);
        right2 = setUp("/res/Monsters/KnightR2", gamePanel.tileSize+10, gamePanel.tileSize+10);
    }

    public void setAction(){
        actionLookCounter ++;
        if(actionLookCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <=50){
                direction = "down";
            }
            if(i > 50 && i <=75){
                direction = "left";
            }
            if(i > 75 && i <=100){
                direction = "right";
            }
            actionLookCounter = 0;

        }
    }
    public void damageReaction(){
        actionLookCounter = 0;

        if(gamePanel.player.direction == "up"){
            direction = "down";
        }
        if(gamePanel.player.direction == "down"){
            direction = "up";
        }
        if(gamePanel.player.direction == "left"){
            direction = "right";
        }
        if(gamePanel.player.direction == "right"){
            direction = "left";
        }
    }
    public void checkDrop(){

        int i = new Random().nextInt(200)+1;

        //Set the Monster drop.

        if(i < 50){
            dropItem(new OBJ_KnightSword(gamePanel));
        }
        if(i >= 50 && i < 75 || i >= 115){
            dropItem(new OBJ_KnightSword(gamePanel));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_KnightSword(gamePanel));
        }
        if(i >= 100 && i < 115){
            dropItem(new OBJ_KnightSword(gamePanel));
        }
    }
}
