package Tiles_Interactive;

import Entity.Entity;
import MainPackage.GamePanel;

public class IT_DryTree extends InteractiveTile{
    GamePanel gp;

    public IT_DryTree(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setUp("/res/Tiles_Interactable/DryTree", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;
        life = 2;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        if(entity.currentWeapon.type == type_Axe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
    public void playSE(){
        gp.playSoundEffect(17);
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_Stump(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        this.playSE();
        return tile;
    }
}
