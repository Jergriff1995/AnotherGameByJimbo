package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gamePanel;

    public OBJ_ManaCrystal(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Mana Crystal";
        image = setUp("/res/Objects/Mana1", gamePanel.tileSize-15, gamePanel.tileSize-15);
        image2 = setUp("/res/Objects/Mana2", gamePanel.tileSize-15 , gamePanel.tileSize-15);
    }
}
