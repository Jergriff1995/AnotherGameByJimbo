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
            if(code == KeyEvent.VK_W){ // if the player has pressed the "W" key.
                gp.playSoundEffect(1);
               gp.ui.commandNum--;
               if(gp.ui.commandNum < 0){
                   gp.ui.commandNum = 2;
               }
            }
            if(code == KeyEvent.VK_S){ // if the player has pressed the "S" key.
                gp.playSoundEffect(1);
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
        //PLAY STATE
        if(gp.gameState == gp.playState){
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

            //DEBUG
            if(code == KeyEvent.VK_T){  // if the player has pressed the "T" key.
                if(checkDrawTime == false){
                    checkDrawTime = true;
                } else if (checkDrawTime = true){
                    checkDrawTime = false;
                }
            }
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }

        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

    }
            //NOTE: never turn column selection mode on in IntelliJ...
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
    }


}
