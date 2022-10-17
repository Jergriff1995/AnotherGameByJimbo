package Entity;

import MainPackage.GamePanel;

public class Projectile extends Entity{

    Entity user;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;


    }
    public void update(){

        if(user == gamePanel.player){
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            if(monsterIndex != 999){
                gamePanel.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }
        switch(direction){
            case "up" : worldY -= speed; break;
            case "down" : worldY += speed; break;
            case "left" : worldX -= speed; break;
            case "right" : worldX += speed; break;
        }
        life--;
        if(life <=0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }



    }
}

