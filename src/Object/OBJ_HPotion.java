package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_HPotion extends Entity {

    GamePanel gp;
    public OBJ_HPotion(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        type = type_Consumable;
        value = 6;
        name = "Health Potion";
        down1 = setUp("/res/Objects/HPotion", gamePanel.tileSize-15, gamePanel.tileSize-15);
        description = "[" + name + "]##Restores 6 health.";
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drank the " + name + "##" +
                "You were healed for " + value + ".";

        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSoundEffect(2);
    }
}
