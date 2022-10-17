package object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_MagicSword extends Entity {
    public OBJ_MagicSword(GamePanel gamePanel) {
        super(gamePanel);
        name = "Magic Sword";
        type = type_Sword;
        attackArea.width = 50;
        attackArea.height = 50;
        down1 = setUp("/res/Objects/MagicSword", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 3;
        description = "[" + name + "]##A longsword made from purple crystal. ##It glows with magic energy.";
    }
}
