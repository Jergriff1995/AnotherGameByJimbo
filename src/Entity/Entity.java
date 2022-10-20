package Entity;


import MainPackage.GamePanel;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// the parent class for all entities in the game. players, monsters, npcs, etc...
public class Entity {
    public int worldX;
    public int worldY;
    //WALKING
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;

    //ATTACKING
    public BufferedImage attackUp1;
    public BufferedImage attackUp2;
    public BufferedImage attackDown1;
    public BufferedImage attackDown2;
    public BufferedImage attackLeft1;
    public BufferedImage attackLeft2;
    public BufferedImage attackRight1;
    public BufferedImage attackRight2;


    //EXTRA
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;

    public String name;
    public Boolean invincible = false;
    public int invincibleCounter;
    public boolean collision = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter;
    public int shotAvailableCounter = 0;
    boolean hpBarOn = false;
    int hpBarCounter;
    public String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public int value;

    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //represents what area of an entity will be considered solid for collision detection.
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;
    public int actionLookCounter;
    GamePanel gamePanel;

    //Type
    public int type; // 0 = player, 1 = npc, 2 = monster.
    public final int type_Player = 0;
    public final int type_NPC = 1;
    public final int type_Monster = 2;
    public final int type_Sword = 3;
    public final int type_Shield = 4;
    public final int type_Consumable = 5;
    public final int type_PickUp = 6;

    //Character Status
    public int maxLife;
    public int maxMana;
    public int mana;
    public int ammo;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLvlExp;
    public int coin;
    public int speed;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    //Item Attributes
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2){

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){

            BufferedImage image = null;

            switch(direction) {
                case "up" :
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down" :
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left" :
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right" :
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    break;
            }

            //MONSTER HP BAR
            if(type == type_Monster && hpBarOn == true && life > 0){
                double oneScale = (double)gamePanel.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                g2.setColor(new Color(35 ,35 ,35));
                g2.fillRect(screenX -1 , screenY - 16, gamePanel.tileSize +2, 12);
                g2.setColor(new Color(208, 6, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);


                hpBarCounter++;
                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }

            }


            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4F);
            }
            if(dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1.0F);
        }

    }
    public void dyingAnimation(Graphics2D g2){

        int interval= 5;

        dyingCounter++;
        if(dyingCounter <= interval){
            changeAlpha(g2, 0.8F);
        }
        if(dyingCounter > interval*2  && dyingCounter <= interval*3){
            changeAlpha(g2, 0.0F);
        }
        if(dyingCounter > interval*3  && dyingCounter <= interval*4){
            changeAlpha(g2, 0.8F);
        }
        if(dyingCounter > interval*4 && dyingCounter <= interval*5){
            changeAlpha(g2, 0.8F);
        }
        if(dyingCounter > interval*5  && dyingCounter <= interval*6){
            changeAlpha(g2, 0.0F);
        }
        if(dyingCounter > interval*6  && dyingCounter <= interval*7){
            changeAlpha(g2, 0.8F);
        }
        if(dyingCounter > interval*7  && dyingCounter <= interval*8){
            changeAlpha(g2, 0.0F);
        }
        if(dyingCounter > interval*8  && dyingCounter <= interval*9){
            changeAlpha(g2, 0.8F);
        }
        if(dyingCounter > interval * 9){
            dying = true;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setUp(String imageName, int width, int height){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            scaledImage = utilityTool.scaleImage(scaledImage, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }

        return scaledImage;

    }

    public void setAction(){

    }

    public void damageReaction(){

    }
    public void use(Entity entity){

    }

    public void update() throws IOException {
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        //If a monster contacts a player
        if(this.type == type_Monster && contactPlayer == true){
          damagePlayer(attack);
        }


        if(collisionOn == false){ // the NPC can only move if collisionOn is false, meaning the tiles are not solid.

            switch (direction){
                // the following 4 if increment or decrement the NPC's direction.
                case "up" :
                    worldY -= speed;
                    break;
                case "down" :
                    worldY  += speed;
                    break;
                case "left" :
                    worldX -= speed;
                    break;
                case "right" :
                    worldX += speed;
                    break;
            }
        }


        // The following if statment is responsible for the speed at which the NPC animation sprites are changed.
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2 ){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        //Responsible for player invincibility timer
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
    public void damagePlayer(int attack){
        //player is not invincible
        if(gamePanel.player.invincible == false){
            //damage dealt
            gamePanel.playSoundEffect(8);
            int damage = attack - gamePanel.player.defense;
            if(damage < 0 ){
                damage = 0;
            }
            gamePanel.player.life -= damage;
            gamePanel.player.invincible = true;

        }
    }
    public void checkDrop(){

    }
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] == null){
                gamePanel.obj[i] = droppedItem;
                gamePanel.obj[i].worldX = worldX;
                gamePanel.obj[i].worldY = worldY;
                break;
            }
        }
    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++ ;

        switch (gamePanel.player.direction){
            case "up":
                direction = "down";
                break;

            case "down":
                direction = "up";
                break;

            case "left":
                direction = "right";
                break;

            case "right":
                direction = "left";
                break;
        }
    }
}
