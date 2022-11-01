package Entity;

import MainPackage.GamePanel;

import java.util.Random;

public class Hoodie extends Entity{

    public Hoodie(GamePanel gp){
        super(gp);
        name = "Hoodie";

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();

    }
    public void getImage(){ //retrieves the sprites for the NPC.

        up1 = setUp("/res/NPC/HoodieU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/NPC/HoodieU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/NPC/HoodieD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/NPC/HoodieD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/NPC/HoodieL2", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/NPC/HoodieL1", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/NPC/HoodieR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/NPC/HoodieR2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "Greetings...";
        dialogues[1] = "...";
        dialogues[2] = "I am Hoodie...";
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

        super.speak();

    }
}