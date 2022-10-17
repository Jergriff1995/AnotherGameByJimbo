package object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_MagicShield extends Entity {
    public OBJ_MagicShield(GamePanel gamePanel) {
        super(gamePanel);
        name = "Magic Shield";
        type = type_Shield;
        down1 = setUp("/res/Objects/MagicShield", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 3;
        description = "[" + name + "]##A golden shield with a blue gemstone.";
    }
}
