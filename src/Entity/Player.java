package Entity;

import MainPackage.GamePanel;
import MainPackage.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0; // an integer that hold how many keys the player has




    public Player(GamePanel gp, KeyHandler keyHandler){ // constructor for the player class. Takes a game panel and key handler object.
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 32 , 32, 16); // creates a rectangle on the character tile that is used for collision.
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValue();
        getPlayerImage();

    }

    public void setDefaultValue(){ // the default values associated with the player.
        // the next two lines are the coordinates of the player's starting location
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 38;

//        QUICKLY FIND COORDS ON MAP
//        worldX = gp.tileSize * 34;
//        worldY = gp.tileSize * 30;


        speed = 4; // how fast the player moves
        direction = "down";
    }
    public void getPlayerImage(){ //retrieves the sprites for the player.
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Right_2.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
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
            gp.collisionChecker.checkTile(this); // calls the collision checker passing the player as the entity to be checked.

            // the following lines are responsible for checking for the player's object collision.
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
        if(index != 999){
            String objectName = gp.obj[index].name;

            switch(objectName){
                //case for interacting with a key item.
                //the hasKey integer will increment
                //the key object will become null (disappear)
                case "Key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("You found a key!");
                    break;


                //case for interacting with a horizontal door.
                //if the player has a key the door will open, its sprite will change, its collision will become false...
                // ... hasKey will decrement, and the used will become true to insure that it can only be opened once.
                case "Door":
                    if(hasKey > 0){
                        if(gp.obj[index].used == false){
                            gp.playSoundEffect(4);
                            gp.obj[index].image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Door_2.png"));
                            gp.obj[index].collision = false;
                            hasKey--;
                            gp.obj[index].used = true;
                            gp.ui.showMessage("The door has been opened!");
                        }
                    } else if (gp.obj[index].used == false){
                        gp.ui.showMessage("This door is locked!");
                    }
                    break;


                //case for interacting with a vertical door.
                //if the player has a key the door will open, its sprite will change, its collision will become false...
                // ... hasKey will decrement, and the used will become true to ensure that it can only be opened once.
                case "VertDoor":
                    if(hasKey > 0){
                        if(gp.obj[index].used == false){
                            gp.playSoundEffect(4);
                            gp.obj[index].image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/Door_3.png"));
                            gp.obj[index].collision = false;
                            hasKey--;
                            gp.obj[index].used = true;
                            gp.ui.showMessage("The door has been opened!");
                        }
                    } else if (gp.obj[index].used == false){
                        gp.ui.showMessage("This door is locked!");
                    }
                    break;

                   //this case deals with the "Shoes" item.
                case "Shoes":
                    gp.playSoundEffect(3);
                    speed += 4;
                    gp.obj[index] = null;
                    gp.ui.showMessage("Speed up!");
                    break;

                //case for getting to the chest and ending the game.
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(2);
                    break;
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); //this line is responsible for actually drawing the player image.

    }

}
