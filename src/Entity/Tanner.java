package Entity;

import MainPackage.GamePanel;

import java.util.Random;

public class Tanner extends Entity{

    public Tanner(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){ //retrieves the sprites for the NPC.

        up1 = setUp("/res/NPC/TanU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/NPC/TanU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/NPC/TanD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/NPC/TanD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/NPC/TanL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/NPC/TanL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/NPC/TanR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/NPC/TanR2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialogue(){
      dialogues[0] = "Dude, no way...";
      dialogues[1] = "I'm 16 bit.";
      dialogues[2] = "That's fire.";
      dialogues[3] = "Good luck on your quest Deck...";

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
//        if(dialogues[dialogueIndex] == null){
//            dialogueIndex = 0;
//        }
//
//        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
//        dialogueIndex++ ;
//
//        switch (gamePanel.player.direction){
//            case "up":
//                direction = "down";
//                break;
//
//            case "down":
//                direction = "up";
//                break;
//
//            case "left":
//                direction = "right";
//                break;
//
//            case "right":
//                direction = "left";
//                break;
//        }
    }
}
