package Object;

import Entity.Projectile;
import MainPackage.GamePanel;

import java.awt.*;

public class OBJ_GBall extends Projectile {
    GamePanel gamePanel;

    public OBJ_GBall(GamePanel gamePanel) {

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
        up1 = setUp("/res/Objects/GBallU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Objects/GBallU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Objects/GBallD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Objects/GBallD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Objects/GBallL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Objects/GBallL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Objects/GBallR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Objects/GBallR2", gamePanel.tileSize, gamePanel.tileSize);
    }
    public Color getParticleColor(){
        Color color = new Color(47, 218, 2);
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
