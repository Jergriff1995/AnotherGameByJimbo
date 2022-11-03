package MainPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {   //KeyListener if the interface for receiving key inputs.

    //booleans to handle if a key is being pressed or not.
    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean enterPressed;
    public boolean shotKeyPressed;


    GamePanel gp;

    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns the integer key code associated with the pressed key.
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
            }
        //PLAY STATE
        else if(gp.gameState == gp.playState){
          playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState){
           dialogueState(code);
        }
        //CHARACTER STATE
        else if (gp.gameState == gp.characterState){
            characterState(code);
        }
        //OPTIONS STATE
        else if (gp.gameState == gp.optionsState){
            optionState(code);
        }
        //GAME OVER STATE
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
    }
            //NOTE: never turn column selection mode on in IntelliJ...
    public void titleState(int code){
        if(code == KeyEvent.VK_W){ // if the player has pressed the "W" key.
                gp.playSoundEffect(7);
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
        if(code == KeyEvent.VK_S){ // if the player has pressed the "S" key.
                gp.playSoundEffect(7);
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
        if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(5);
                }
                if(gp.ui.commandNum == 1){
                    //LOAD GAME
                    //add later
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }


            }
    }
    public void playState(int code){

        if(code == KeyEvent.VK_W){ // if the player has pressed the "W" key.
                upPressed = true;
            }
        if(code == KeyEvent.VK_A){ // if the player has pressed the "A" key.
                leftPressed = true;
            }
        if(code == KeyEvent.VK_S){ // if the player has pressed the "S" key.
                downPressed = true;
            }
        if(code == KeyEvent.VK_D){  // if the player has pressed the "D" key.
                rightPressed = true;
            }
        if(code == KeyEvent.VK_P){  // if the player has pressed the "P" key.
                gp.gameState = gp.pauseState;
            }
        if(code == KeyEvent.VK_ENTER){  // if the player has pressed the "P" key.
                enterPressed = true;
            }
        if(code == KeyEvent.VK_C){  // if the player has pressed the "C" key.
                gp.gameState = gp.characterState;
            }
        if(code == KeyEvent.VK_F){  // if the player has pressed the "P" key.
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){ // if the player has pressed the "W" key.
            gp.gameState = gp.optionsState;
        }

            //DEBUG
            if(code == KeyEvent.VK_T){  // if the player has pressed the "T" key.
                if(checkDrawTime == false){
                    checkDrawTime = true;
                } else if (checkDrawTime = true){
                    checkDrawTime = false;
                }
            }
        if(code == KeyEvent.VK_L){
           switch (gp.currentMap){
               case 0: gp.tileManager.loadMap("res/Maps/Sheoah.txt", 0); break;
               case 1: gp.tileManager.loadMap("res/Maps/MattHouse.txt", 1); break;
           }
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;

        }

    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
        }

    }
    public void characterState(int code){

        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
           if(gp.ui.slotRow !=0){
               gp.ui.slotRow--;
               gp.playSoundEffect(14);
           }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol !=0){
                gp.ui.slotCol--;
                gp.playSoundEffect(14);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.playSoundEffect(14);
            }

        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.playSoundEffect(14);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }
    public void optionState(int code){

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
            gp.playSoundEffect(19);
        }
        int maxCommandNum = 0;
        switch (gp.ui.substate){
            case 0 : maxCommandNum = 5; break;
            case 3 : maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum --;
            gp.playSoundEffect(19);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum ++;
            gp.playSoundEffect(18);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.substate == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale --;
                    gp.music.checkVolume();
                    gp.playSoundEffect(18);
                }
                if(gp.ui.commandNum == 2 && gp.sound.volumeScale > 0){
                    gp.sound.volumeScale --;
                    gp.playSoundEffect(18);
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.substate == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale ++;
                    gp.music.checkVolume();
                    gp.playSoundEffect(19);
                }
                if(gp.ui.commandNum == 2 && gp.sound.volumeScale < 5){
                    gp.sound.volumeScale ++;
                    gp.playSoundEffect(19);

                }
            }
        }
    }
    public void gameOverState(int code){

        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
            gp.playSoundEffect(19);
        }

        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
            gp.playSoundEffect(18);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
            }
            if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
                gp.music.stop();
                gp.playMusic(0);
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // returns the integer key code associated with the pressed key.

        if(code == KeyEvent.VK_W){ // if the player has released the "W" key.
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){ // if the player has released the "A" key.
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){ // if the player has released the "S" key.
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){  // if the player has released the "D" key.
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){  // if the player has pressed the "P" key.
            shotKeyPressed = false;
        }
    }


}
