package MainPackage;

import Entity.Entity;
import Object.Obj_Heart;
import Object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI  {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    Font knightsQuest;
    Font knightsQuest2;
    BufferedImage heartFull;
    BufferedImage heartHalf;
    BufferedImage heartEmpty;
    BufferedImage crystalFull;
    BufferedImage crystalEmpty;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    double playTime; //double is used so we can display fractions of a second.
    public String currentDialogue = "";

    public int commandNum = 0;
    public int slotCol= 0;
    public int slotRow = 0;
    int substate = 0;

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

        Entity crystal = new OBJ_ManaCrystal(gp);
        crystalFull = crystal.image;
        crystalEmpty = crystal.image2;

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
            drawMessage();

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
            drawInventory();
        }
        //Options State
        if(gp.gameState == gp.optionsState){
           drawOptionsScreen();
        }
        //Game Over State
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }

    public void drawPlayerLife(){


        int x = gp.tileSize/2 - 5;
        int y = gp.tileSize/2;
        int i = 0;
        //Draw Blank Hearts
        while(i < gp.player.maxLife/2){
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //Reset Values
         x = gp.tileSize/2 - 5;
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
        //Draw Max Mana
        x = (gp.tileSize/2) - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.player.maxMana){
            g2.drawImage(crystalEmpty, x, y, null);
            i++;
            x += 38;
        }
        //Draw Current Mana
        x = (gp.tileSize/2) - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystalFull, x, y, null);
            i++;
            x += 38;
        }


    }

    public void drawMessage(){
        int messageX =  gp.tileSize/2;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26F));
        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                // messageCounter ++
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);

                messageY += 50;

                if(messageCounter.get(i) > 120){
                  message.remove(i);
                  messageCounter.remove(i);
                }
            }

        }
    }

    public void addMessage(String text){

        message.add(text);
        messageCounter.add(0);
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
        g2.setColor(new Color(94, 100, 100));
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
        int x = getXforCenterText(text);
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
     int y = gp.tileSize * 2;
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
        final int frameX = gp.tileSize * 1;
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
        g2.drawString("Mana : " , textX, textY);
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

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
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

    public void drawInventory(){
        //Frame
        int frameX = gp.tileSize * 13;
        int frameY = gp.tileSize;
        int frameWidth  = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Item Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //Draw Items
        for(int i = 0; i < gp.player.inventory.size(); i++){
            //Draw Equip Cursor
            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
            gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(Color.orange);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10 ,10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }


        //Cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //Draw Cursor
        g2.setColor(Color.yellow);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //Description Window
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;

        //Draw Description Text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(20F));
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for(String line : gp.player.inventory.get(itemIndex).description.split("##")){
                g2.drawString(line, textX, textY);
                textY += 32;

            }

        }


    }

    public int getItemIndexOnSlot(){
        int itemIndex = slotCol+(slotRow * 5);
        return itemIndex;
    }

    public void drawOptionsScreen(){

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30F));

        //Sub Window
        int frameX = gp.tileSize *6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(substate){
            case 0: optionsTop(frameX, frameY); break;
            case 1: optionsFullScreenNotification(frameX, frameY); break;
            case 2: optionsControl(frameX, frameY); break;
            case 3: endGameConfirm(frameX, frameY); break;
        }
        gp.keyHandler.enterPressed = false;
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0, 0, 0, 120));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(knightsQuest);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        text = "Game Over!";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x , y);
        //Main
        g2.setColor(Color.white);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x-4, y -4);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        text = "C'mon Dude...";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenterText(text);
        y = gp.tileSize * 6;
        g2.drawString(text, x , y);
        //Main
        g2.setColor(Color.white);
        x = getXforCenterText(text);
        y = gp.tileSize * 6;
        g2.drawString(text, x-4, y -4);

        //Restart
        g2.setFont(g2.getFont().deriveFont(28));
        text = "Restart Game";
        x = getXforCenterText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - 40, y);
        }

        //Back To Title
        text = "Return To Title";
        x = getXforCenterText(text);
        y += 50;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - 40, y);
        }



    }
    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;
        //TITLE
        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else if (gp.fullScreenOn == true){
                    gp.fullScreenOn = false;

                }
                substate = 1;
            }

        }

        //MUSIC
        textY +=  gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
        }

        //SOUND EFFECTS
        textY +=  gp.tileSize;
        g2.drawString("Sound FX", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 25, textY);
        }

        //CONTROLS
        textY +=  gp.tileSize;
        g2.drawString("Controls", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                substate = 2;
                commandNum = 0;
            }
        }

        //END THE GAME
        textY +=  gp.tileSize;
        g2.drawString("Quit Game", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                substate = 3;
                commandNum = 0;
            }
        }

        //RETURN TO GAME
        textY +=  gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN TEXT BOX
        textX = frameX + gp.tileSize*5 + 70;
        textY = frameY + gp.tileSize*2 + 30;
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }



        //MUSIC VOLUME
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 19);
        int volumeWidth = 24* gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 19);

        //SE VOLUME
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 19);
        int seWidth = 24* gp.sound.volumeScale;
        g2.fillRect(textX, textY, seWidth, 19);

        gp.config.saveConfig();
    }
    public void optionsFullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 4;

        currentDialogue = "Display mode will##be changed after##restarting the game!";
        for(String line : currentDialogue.split("##")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                substate = 0;
            }
        }
    }
    public void optionsControl(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "Controls";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Select/Attack", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Special", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        //BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                substate = 0;
                commandNum = 3;
            }
        }
    }

    public void endGameConfirm(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        currentDialogue = "Are you sure you##want to end the game?";

        for(String line : currentDialogue.split("##")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        String text = "Yes";
        textX = getXforCenterText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                substate = 0;
                gp.gameState = gp.titleState;
            }
        }
        text = "No Way!";
        textX = getXforCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyHandler.enterPressed == true){
                substate = 0;
                commandNum = 4;
            }
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

    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
