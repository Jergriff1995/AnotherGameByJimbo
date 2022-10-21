package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);
        name = "Woodcutter's Axe";
        type = type_Axe;
        attackArea.width = 40;
        attackArea.height = 40;
        down1 = setUp("/res/Objects/Axe", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]##An axe suitable for cutting down trees. ##Not very good in battle.";
    }
}
