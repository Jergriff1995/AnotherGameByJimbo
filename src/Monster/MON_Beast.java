package Monster;

import Entity.Entity;
import MainPackage.GamePanel;

import java.util.Random;

public class MON_Beast extends Entity {

    GamePanel gamePanel;

    public MON_Beast(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = 2;
        name = "Beast";
        speed = 1;
        maxLife = 6;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage(){
        up1 = setUp("/res/Monsters/NPC1B1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Monsters/NPC1B2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Monsters/NPC1F1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Monsters/NPC1F2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Monsters/NPC1L1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Monsters/NPC1L2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Monsters/NPC1R1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Monsters/NPC1R2", gamePanel.tileSize, gamePanel.tileSize);
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

}
