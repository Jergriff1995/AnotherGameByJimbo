package Tiles_Interactive;

import Entity.Entity;
import MainPackage.GamePanel;

public class IT_Stump extends InteractiveTile{
    GamePanel gp;

    public IT_Stump(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setUp("/res/Tiles_Interactable/Stump", gamePanel.tileSize, gamePanel.tileSize);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;

    }

}
