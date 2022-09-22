package MainPackage;

import object.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI  {
    GamePanel gp;
    Font arial_40;
    Font arial_70B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;


    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Book Antiqua", Font.PLAIN, 40);
        arial_70B = new Font("Book Antiqua", Font.BOLD, 70);
        Obj_Key key = new Obj_Key();
        keyImage = key.image;
    }

    public void Draw(Graphics2D g2){

        if(gameFinished == true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            // the following line returns an int value corresponding to the length of the above text message.
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x = gp.screenWidth/2 - textLength/2;
             y = gp.screenHeight/2  - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_70B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            // the following line returns an int value corresponding to the length of the above text message.
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2  + (gp.tileSize*2);
            g2.drawString(text, x, y);

            //STOP THE GAME
            gp.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.hasKey, 74, 65); //the drawString method does NOT use x and y coordinates like other draw methods...

            //MESSAGE
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F)); //resizes the current font. The method accepts a float for some reason....
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
                messageCounter++;

                // this statement is responsible for removing the message after a set number of frames.
                if(messageCounter > 90){ // this line contains the number of frames a message will be displayed.
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }


    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
}
