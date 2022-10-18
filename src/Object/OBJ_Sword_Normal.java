package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);

        name = "Normal Sword";
        type = type_Sword;
        attackArea.width = 36;
        attackArea.height = 36;
        down1 = setUp("/res/Objects/Sword", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]##An old sword";

    }
}
