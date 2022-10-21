package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_PickUpHeart extends Entity {
    GamePanel gp;
    public OBJ_PickUpHeart(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        type = type_PickUp;
        value = 1;
        name = "Heart Restore";
        down1 = setUp("/res/Objects/Heart4", gamePanel.tileSize -15 , gamePanel.tileSize -15);
        description = "[" + name + "]##Restores 1 HP.";
    }

    public void use(Entity entity){
        gp.playSoundEffect(1);
        gp.ui.addMessage("Life restored by " + value);
        gp.player.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
    }
}
