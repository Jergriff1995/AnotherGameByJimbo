package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Gem2 extends Entity {
    GamePanel gamePanel;


    public OBJ_Gem2(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = 6;
        name = "Green Gem";
        down1 = down1 = setUp("/res/Objects/Gem2", gamePanel.tileSize-15, gamePanel.tileSize-15);
        value = 5;
    }

    public void use(Entity entity){
        gamePanel.playSoundEffect(1);
        gamePanel.ui.addMessage("Coin + " + value);
        gamePanel.player.coin += value;
    }
}
