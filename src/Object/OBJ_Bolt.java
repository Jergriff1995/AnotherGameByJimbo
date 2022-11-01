package Object;

import Entity.Projectile;
import MainPackage.GamePanel;

import java.awt.*;

public class OBJ_Bolt extends Projectile {
    GamePanel gamePanel;

    public OBJ_Bolt(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Lightning Bolt";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        attack = 5;
        useCost = 1;
        alive = false;
        getImage();

    }
    public void getImage(){
        up1 = setUp("/res/Objects/BoltU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Objects/BoltU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Objects/BoltD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Objects/BoltD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Objects/BoltL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Objects/BoltL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Objects/Bolt1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Objects/Bolt2", gamePanel.tileSize, gamePanel.tileSize);
    }
    public Color getParticleColor(){
        Color color = new Color(255, 240, 78);
        return color;
    }
    public int getParticleSize(){
        int size = 8;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
