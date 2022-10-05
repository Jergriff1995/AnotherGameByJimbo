package Entity;


import MainPackage.GamePanel;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// the parent class for all entities in the game. players, monsters, npcs, etc...
public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;

    public String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //represents what area of an entity will be considered solid for collision detection.
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;
    public int actionLookCounter;
    GamePanel gamePanel;

    //Character Status
    public int maxLife;
    public int life;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2){

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){

            BufferedImage image = null;

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

            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }

    }
    public BufferedImage setUp(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            scaledImage = utilityTool.scaleImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }

        return scaledImage;

    }

    public void setAction(){

    }

    public void update() throws IOException {
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);


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
        if(spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2 ){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++ ;

        switch (gamePanel.player.direction){
            case "up":
                direction = "down";
                break;

            case "down":
                direction = "up";
                break;

            case "left":
                direction = "right";
                break;

            case "right":
                direction = "left";
                break;
        }
    }
}
