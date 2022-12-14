package Monster;

import Entity.Entity;
import MainPackage.GamePanel;

import java.util.Random;
import Object.OBJ_Gem1;
import Object.OBJ_Gem2;
import Object.OBJ_HPotion;
import Object.OBJ_PickUpHeart;
import Object.OBJ_MPotion;


public class MON_PSlime extends Entity {

    GamePanel gamePanel;

    public MON_PSlime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_Monster;
        name = "Pink Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage(){
        up1 = setUp("/res/Monsters/PSlime1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Monsters/PSlime2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Monsters/PSlime1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Monsters/PSlime2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Monsters/PSlime1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Monsters/PSlime2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Monsters/PSlime1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Monsters/PSlime2", gamePanel.tileSize, gamePanel.tileSize);
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
        direction = gamePanel.player.direction;
    }
    public void checkDrop(){

        int i = new Random().nextInt(200)+1;

        //Set the Monster drop.

        if(i < 50){
            dropItem(new OBJ_Gem1(gamePanel));
        }
        if(i >= 50 && i < 75 || i >= 115){
            dropItem(new OBJ_PickUpHeart(gamePanel));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_MPotion(gamePanel));
        }
        if(i >= 100 && i < 115){
            dropItem(new OBJ_Gem2(gamePanel));
        }
    }
}
