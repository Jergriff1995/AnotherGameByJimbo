package Entity;

import MainPackage.GamePanel;

import java.io.IOException;
import java.util.Random;

public class Chase extends Entity{

    public Chase(GamePanel gp){
        super(gp);
        name = "Chase";

        direction = "down";
        speed = 0;

        getImage();
        setDialogue();

    }
    public void getImage(){ //retrieves the sprites for the NPC.

        up1 = setUp("/res/NPC/Chase1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/NPC/Chase2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/NPC/Chase1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/NPC/Chase2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/NPC/Chase1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/NPC/Chase2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/NPC/Chase1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/NPC/Chase2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "*BuuuUuuRrrp*...";
        dialogues[1] = "...";
        dialogues[2] = "*BuuuUuuRrrp*...";
        dialogues[3] = "...";

    }



    public void setAction(){

        actionLookCounter ++;
        if(actionLookCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "down";
            }
            if(i > 25 && i <=50){
                direction = "down";
            }
            if(i > 50 && i <=75){
                direction = "down";
            }
            if(i > 75 && i <=100){
                direction = "down";
            }
            actionLookCounter = 0;

        }
    }

    public void speak(){

        super.speak();

    }
    @Override
    public void update() throws IOException {
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.iTile);

        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        //If a monster contacts a player
        if(this.type == type_Monster && contactPlayer == true){
            damagePlayer(attack);
        }


        if(collisionOn == false){ // the NPC can only move if collisionOn is false, meaning the tiles are not solid.

            switch (direction){
                // the following 4 if increment or decrement the NPC's direction.
                case "up" :
                    worldY -= speed;
                    break;
                case "down" :
                    worldY  += speed;
                    break;
                case "left" :
                    worldX -= speed;
                    break;
                case "right" :
                    worldX += speed;
                    break;
            }
        }


        // The following if statment is responsible for the speed at which the NPC animation sprites are changed.
        spriteCounter++;
        if(spriteCounter > 200){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2 ){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        //Responsible for player invincibility timer
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
}