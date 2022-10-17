package Monster;

import Entity.Entity;
import MainPackage.GamePanel;

import java.util.Random;

public class MON_Slime extends Entity {

    GamePanel gamePanel;

    public MON_Slime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_Monster;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
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
        up1 = setUp("/res/Monsters/Slime1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Monsters/Slime2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Monsters/Slime1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Monsters/Slime2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Monsters/Slime1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Monsters/Slime2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Monsters/Slime1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Monsters/Slime2", gamePanel.tileSize, gamePanel.tileSize);
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

}
