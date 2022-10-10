package MainPackage;

import Entity.Entity;
import object.Obj_Heart;

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
        Entity heart = new Obj_Heart(gp);
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
        //CharacterState
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
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
    public void drawCharacterScreen(){

        //Create a frame.
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize *10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 36;

        //Names
        g2.drawString("Level : ", textX, textY);
        textY += lineHeight;
        g2.drawString("Life : " , textX, textY);
        textY += lineHeight;
        g2.drawString("Strength : ", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity : ", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack : ", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense : ", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp : ", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin : ", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon : ", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield : ", textX, textY);
        textY += lineHeight;

        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY -13, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 13, null);

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

    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
