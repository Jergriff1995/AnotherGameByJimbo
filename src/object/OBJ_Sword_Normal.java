package object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);

        name = "Normal Sword";
        down1 = setUp("/res/Objects/Sword", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
    }
}
