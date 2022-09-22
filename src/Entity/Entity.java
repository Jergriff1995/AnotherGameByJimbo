package Entity;


import java.awt.*;
import java.awt.image.BufferedImage;

// the parent class for all entities in the game. players, monsters, npcs, etc...
public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea; //represents what area of an entity will be considered solid for collision detection.
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;
}
