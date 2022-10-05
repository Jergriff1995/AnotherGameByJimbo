package MainPackage;

import object.Obj_Heart;
import object.Obj_Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI  {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    Font knightsQuest;
    Font knightsQuest2;
    BufferedImage heartFull;
    BufferedImage heartHalf;
    BufferedImage heartEmpty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime; //double is used so we can display fractions of a second.
    public String currentDialogue = "";
    public int commandNum = 0;

    DecimalFormat decimalFormat = new DecimalFormat("#0.00"); //a handy method for formatting decimal values.


    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/Font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/Font/Knights Quest.ttf");
            knightsQuest = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/Font/Knights Quest Callig.ttf");
            knightsQuest2 = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Create HUD Object
        SuperObject heart = new Obj_Heart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.white);
        //Title State
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //Play State
        if(gp.gameState == gp.playState){
            drawPlayerLife();

        }
        //Pause State
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //Dialogue State
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife(){


        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //Draw Blank Hearts
        while(i < gp.player.maxLife/2){
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //Reset Values
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;
         //Draw Current Health
        while(i < gp.player.life){
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void drawTitleScreen(){
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        //Title Name
        g2.setFont(knightsQuest);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Deck's Quest";
        int x = getXforCenterText(text);
        int y = gp.tileSize * 3;

        //Shadow
        g2.setColor(new Color(31, 193, 173));
        g2.drawString(text, x+5,y+5);

        //Main Color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // Player Image
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);


        //Menu
        //g2.setFont(knightsQuest);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45F));
        text = "NEW GAME";
        x = getXforCenterText(text);
        y += gp.tileSize*3.2;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

    }

    public void drawPauseScreen(){

        String text = "PAUSED";
        g2.setFont(knightsQuest);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 44F));
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - getXforCenterText(text)/2;
        int y = gp.screenHeight/2;

        int windowX = gp.tileSize * 4;
        int windowY = gp.tileSize * 5 - 10 ;
        int width = gp.screenWidth - (gp.tileSize * 8);
        int height = gp.tileSize * 2;
        drawSubWindow(windowX, windowY ,width, height);


        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen(){

      //Window for dialogue
     int x = gp.tileSize * 2;
     int y = gp.tileSize / 2;
     int width = gp.screenWidth - (gp.tileSize * 4);
     int height = gp.tileSize * 4;
     drawSubWindow(x, y ,width, height);

     g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 27F));
     x += gp.tileSize;
     y += gp.tileSize;
     for(String line : currentDialogue.split("##")){
        g2.drawString(line, x, y);
        y += 40;
     }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0, 205);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35); //method to draw a rounded rectangle.

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    public int getXforCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }
}
