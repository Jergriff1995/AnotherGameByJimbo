package Entity;

import MainPackage.GamePanel;
import MainPackage.KeyHandler;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    //public int hasKey = 0; // an integer that hold how many keys the player has






    public Player(GamePanel gamePanel, KeyHandler keyHandler){ // constructor for the player class. Takes a game panel and key handler object.
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(11, 10, 26, 28); // creates a rectangle on the character tile that is used for collision.
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValue();
        getPlayerImage();

    }

    public void setDefaultValue(){ // the default values associated with the player.
        // the next two lines are the coordinates of the player's starting location
        worldX = gamePanel.tileSize * 59;
        worldY = gamePanel.tileSize * 31;

//        QUICKLY FIND COORDS ON MAP
//        worldX = gp.tileSize * 34;
//        worldY = gp.tileSize * 30;


        speed = 4; // how fast the player moves
        direction = "down";

        //Player Status
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage(){ //retrieves the sprites for the player.

        up1 = setUp("/res/Player/Up_1");
        up2 = setUp("/res/Player/Up_2");
        down1 = setUp("/res/Player/Down_1");
        down2 = setUp("/res/Player/Down_2");
        left1 = setUp("/res/Player/Left_1");
        left2 = setUp("/res/Player/Left_2");
        right1 = setUp("/res/Player/Right_1");
        right2 = setUp("/res/Player/Right_2");
    }

    public void update() throws IOException { //updates in 60 fps to reflect changes on the game screen.

        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true){


            // the following 4 if statements determine the player's direction based on the key listener.

            if(keyHandler.upPressed == true){
                direction = "up";

            }
            if(keyHandler.downPressed == true){
                direction = "down";

            }
            if(keyHandler.rightPressed == true){
                direction = "right";

            }
            if(keyHandler.leftPressed == true){
                direction = "left";

            }
            // the following lines are responsible for checking for the player's tile collision.
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this); // calls the collision checker passing the player as the entity to be checked.

            // the following lines are responsible for checking for the player's object collision.
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            //CHECK EVENT
            gamePanel.eventHandler.checkEvent();
            gamePanel.keyHandler.enterPressed = false;

            if(collisionOn == false){ // the player can only move if collisionOn is false, meaning the tiles are not solid.

                switch (direction){
                    // the following 4 if increment or decrement the player's direction.
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


            // The following if statment is responsible for the speed at which the player animation sprites are changed.
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2 ){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }
    public void pickUpObject(int index) throws IOException {
        if(index != 999){}
    }

    public void interactNPC(int index){
        if(index != 999){
            if(gamePanel.keyHandler.enterPressed == true){
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();
            }
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        //this switch case handles drawing the correct sprite to correspond to player direction.
        switch(direction) {
            case "up" :
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down" :
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left" :
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right" :
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,null); //this line is responsible for actually drawing the player image.

    }

}
