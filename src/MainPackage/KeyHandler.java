package MainPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {   //KeyListener if the interface for receiving key inputs.

    //booleans to handle if a key is being pressed or not.
    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns the integer key code associated with the pressed key.

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
