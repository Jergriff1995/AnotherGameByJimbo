package Entity;

import MainPackage.GamePanel;

import java.util.Random;

public class Johnny extends Entity{

    public Johnny(GamePanel gp){
        super(gp);
        name = "Johnny";

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){ //retrieves the sprites for the NPC.

        up1 = setUp("/res/NPC/JohnU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/NPC/JohnU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/NPC/JohnD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/NPC/JohnD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/NPC/JohnL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/NPC/JohnL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/NPC/JohnR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/NPC/JohnR2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "Wassup!";
        dialogues[1] = "...";
        dialogues[2] = "How did i get here...";
        dialogues[3] = "...";

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

    public void speak(){

        super.speak();}
}