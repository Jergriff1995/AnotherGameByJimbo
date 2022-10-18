package Object;

import Entity.Entity;
import Entity.Projectile;
import MainPackage.GamePanel;

public class OBJ_Fireball extends Projectile {

    GamePanel gamePanel;

    public OBJ_Fireball(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 5;
        useCost = 1;
        alive = false;
        getImage();

    }
    public void getImage(){
        up1 = setUp("/res/Objects/FireballU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Objects/FireballU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Objects/FireballD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Objects/FireballD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Objects/FireballL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Objects/FireballL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Objects/FireballR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Objects/FireballR2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public boolean haveResource(Entity entity){
        boolean haveResource = false;
        if(entity.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity entity){
        entity.mana -= useCost;
    }
}
