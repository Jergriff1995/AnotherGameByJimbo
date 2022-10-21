package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_KnightSword extends Entity {
    public OBJ_KnightSword(GamePanel gamePanel) {
        super(gamePanel);
        name = "Knight Sword";
        type = type_Sword;
        attackArea.width = 40;
        attackArea.height = 40;
        down1 = setUp("/res/Objects/KnightSword", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 2;
        description = "[" + name + "]##The sword of a true knight.";
    }
}
