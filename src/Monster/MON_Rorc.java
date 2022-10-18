package Monster;

import Entity.Entity;
import MainPackage.GamePanel;
import Object.OBJ_GBall;
import java.util.Random;

public class MON_Rorc extends Entity {

    GamePanel gamePanel;


    public MON_Rorc(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_Monster;
        name = "Red Orc";
        speed = 2;
        maxLife = 8;
        life = maxLife;
        attack = 4;
        defense = 0;
        exp = 5;
        projectile = new OBJ_GBall(gamePanel);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage(){
        up1 = setUp("/res/Monsters/RorcU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Monsters/RorcU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Monsters/RorcD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Monsters/RorcD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Monsters/RorcL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Monsters/RorcL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Monsters/RorcR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Monsters/RorcR2", gamePanel.tileSize, gamePanel.tileSize);
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
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gamePanel.projectileList.add(projectile);
            shotAvailableCounter = 0;
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
