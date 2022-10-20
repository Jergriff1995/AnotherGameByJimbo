package Object;

import Entity.Entity;
import MainPackage.GamePanel;

public class OBJ_MPotion extends Entity {
    GamePanel gp;
    public OBJ_MPotion(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        type = type_Consumable;
        value = 3;
        name = "Mana Potion";
        down1 = setUp("/res/Objects/MPotion", gamePanel.tileSize -15 , gamePanel.tileSize -15);
        description = "[" + name + "]##Restores 3 mana.";
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drank the " + name + "##" +
                "Your mana was restored by " + value + ".";

        entity.mana += value;
        if(gp.player.mana > gp.player.maxMana){
            gp.player.mana = gp.player.maxMana;
        }
        gp.playSoundEffect(2);
    }
}
