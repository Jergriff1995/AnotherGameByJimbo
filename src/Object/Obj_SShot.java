package Object;

import Entity.Entity;
import Entity.Projectile;
import MainPackage.GamePanel;

public class Obj_SShot extends Projectile {

    GamePanel gamePanel;

    public Obj_SShot(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Slime Shot";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }
    public void getImage(){
        up1 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Objects/SShot", gamePanel.tileSize, gamePanel.tileSize);
    }
    public boolean haveResource(Entity entity){
        boolean haveResource = false;
        if(entity.ammo >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity entity){
        entity.ammo -= useCost;
    }
}
