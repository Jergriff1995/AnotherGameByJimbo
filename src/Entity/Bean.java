package Entity;

import MainPackage.GamePanel;

public class Bean extends Entity{

    public Bean(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }

    public void getImage(){ //retrieves the sprites for the NPC.

        down1 = setUp("/res/NPC/Bean");

    }
}
