package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Gem1 extends Entity {
    GamePanel gamePanel;


    public OBJ_Gem1(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = 6;
        name = "Red Gem";
        down1 = down1 = setUp("/res/Objects/Gem1", gamePanel.tileSize-15, gamePanel.tileSize-15);
        value = 1;
    }

    public void use(Entity entity){
        gamePanel.playSoundEffect(1);
        gamePanel.ui.addMessage("Coin + " + value);
        gamePanel.player.coin += value;
    }
}
