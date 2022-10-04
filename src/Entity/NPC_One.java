package Entity;

import MainPackage.GamePanel;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_One extends Entity{

    public NPC_One(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage(){ //retrieves the sprites for the NPC.

        up1 = setUp("/res/NPC/NPC1B1");
        up2 = setUp("/res/NPC/NPC1B2");
        down1 = setUp("/res/NPC/NPC1F1");
        down2 = setUp("/res/NPC/NPC1F2");
        left1 = setUp("/res/NPC/NPC1L1");
        left2 = setUp("/res/NPC/NPC1L2");
        right1 = setUp("/res/NPC/NPC1R1");
        right2 = setUp("/res/NPC/NPC1R2");
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


}
