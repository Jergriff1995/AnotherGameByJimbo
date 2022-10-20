

package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Gem3 extends Entity {
    GamePanel gamePanel;


    public OBJ_Gem3(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = 6;
        name = "Blue Gem";
        down1 = setUp("/res/Objects/Gem3", gamePanel.tileSize-15, gamePanel.tileSize-15);
        value = 10;
    }

    public void use(Entity entity){
        gamePanel.playSoundEffect(1);
        gamePanel.ui.addMessage("Coin + " + value);
        gamePanel.player.coin += value;
    }
}
