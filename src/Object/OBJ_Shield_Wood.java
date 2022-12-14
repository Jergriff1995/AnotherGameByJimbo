package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);

        name = "Wood Shield";
        type = type_Shield;
        down1 = setUp("/res/Objects/Shield", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]##A simple wooden shield";
    }
}
