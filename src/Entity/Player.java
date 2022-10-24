package Entity;

import MainPackage.GamePanel;
import MainPackage.KeyHandler;
import Object.OBJ_Fireball;
import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword_Normal;
import Object.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int standCounter;
    public boolean attackCancelled = false;
    public ArrayList<Entity> inventory = new ArrayList<Entity>();
    public final int inventorySize = 20;
    //public int hasKey = 0; // an integer that hold how many keys the player has






    public Player(GamePanel gamePanel, KeyHandler keyHandler){ // constructor for the player class. Takes a game panel and key handler object.
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32); // creates a rectangle on the character tile that is used for collision.
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;



        setDefaultValue();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();

    }

    public void setDefaultValue(){ // the default values associated with the player.
        // the next two lines are the coordinates of the player's starting location
        worldX = gamePanel.tileSize * 18;
        worldY = gamePanel.tileSize * 15;

//        QUICKLY FIND COORDS ON MAP
//        worldX = gp.tileSize * 34;
//        worldY = gp.tileSize * 30;


        speed = 4; // how fast the player moves
        direction = "down";

        //Player Status
        level = 1;
        strength = 1; // more strength equals more damage dealt
        dexterity = 1; // the more dex the less damage taken
        exp = 0;
        nextLvlExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gamePanel);
        currentShield = new OBJ_Shield_Wood(gamePanel);
        projectile = new OBJ_Fireball(gamePanel);
        attack = getAttack(); // this total is decided by strength * weapon's attack
        defense = getDefense(); // this total is decided by dex * shield's defense
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Obj_Key(gamePanel));
        inventory.add(new Obj_Key(gamePanel));

    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    public void getPlayerImage(){ //retrieves the sprites for the player.

        up1 = setUp("/res/Player/DeckU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setUp("/res/Player/DeckU2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setUp("/res/Player/DeckD1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setUp("/res/Player/DeckD2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setUp("/res/Player/DeckL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setUp("/res/Player/DeckL2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setUp("/res/Player/DeckR1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setUp("/res/Player/DeckR2", gamePanel.tileSize, gamePanel.tileSize);


    }

    public void getPlayerAttackImage(){
        if(currentWeapon.name == "Normal Sword"){
        attackUp1 = setUp("/res/Player/DeckAttackUp1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackUp2 = setUp("/res/Player/DeckAttackUp2", gamePanel.tileSize, gamePanel.tileSize*2);
        attackDown1 = setUp("/res/Player/DeckAttackDown1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackDown2 = setUp("/res/Player/DeckAttackDown2", gamePanel.tileSize, gamePanel.tileSize*2);
        attackLeft1 = setUp("/res/Player/DeckAttackL1", gamePanel.tileSize*2, gamePanel.tileSize);
        attackLeft2 = setUp("/res/Player/DeckAttackL2", gamePanel.tileSize*2, gamePanel.tileSize);
        attackRight1 = setUp("/res/Player/DeckAttackRight1", gamePanel.tileSize*2, gamePanel.tileSize);
        attackRight2 = setUp("/res/Player/DeckAttackRight2", gamePanel.tileSize*2, gamePanel.tileSize);
        }
        if(currentWeapon.name == "Magic Sword"){
            attackUp1 = setUp("/res/Player/DeckAttackUp1MS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackUp2 = setUp("/res/Player/DeckAttackUp2MS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackDown1 = setUp("/res/Player/DeckAttackDown1MS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackDown2 = setUp("/res/Player/DeckAttackDown2MS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackLeft1 = setUp("/res/Player/DeckAttackL1MS", gamePanel.tileSize*2, gamePanel.tileSize);
            attackLeft2 = setUp("/res/Player/DeckAttackL2MS", gamePanel.tileSize*2, gamePanel.tileSize);
            attackRight1 = setUp("/res/Player/DeckAttackRight1MS", gamePanel.tileSize*2, gamePanel.tileSize);
            attackRight2 = setUp("/res/Player/DeckAttackRight2MS", gamePanel.tileSize*2, gamePanel.tileSize);
        }
        if(currentWeapon.name == "Knight Sword"){
            attackUp1 = setUp("/res/Player/DeckAttackUp1KS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackUp2 = setUp("/res/Player/DeckAttackUp2KS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackDown1 = setUp("/res/Player/DeckAttackDown1KS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackDown2 = setUp("/res/Player/DeckAttackDown2KS", gamePanel.tileSize, gamePanel.tileSize*2);
            attackLeft1 = setUp("/res/Player/DeckAttackL2KS", gamePanel.tileSize*2, gamePanel.tileSize);
            attackLeft2 = setUp("/res/Player/DeckAttackL1KS", gamePanel.tileSize*2, gamePanel.tileSize);
            attackRight1 = setUp("/res/Player/DeckAttackR1KS", gamePanel.tileSize*2, gamePanel.tileSize);
            attackRight2 = setUp("/res/Player/DeckAttackR2KS", gamePanel.tileSize*2, gamePanel.tileSize);
        }
        if(currentWeapon.name == "Woodcutter's Axe"){
            attackUp1 = setUp("/res/Player/DeckAxeU2", gamePanel.tileSize, gamePanel.tileSize*2);
            attackUp2 = setUp("/res/Player/DeckAxeU1", gamePanel.tileSize, gamePanel.tileSize*2);
            attackDown1 = setUp("/res/Player/DeckAxeD1", gamePanel.tileSize, gamePanel.tileSize*2);
            attackDown2 = setUp("/res/Player/DeckAxeD2", gamePanel.tileSize, gamePanel.tileSize*2);
            attackLeft1 = setUp("/res/Player/DeckAxeL1", gamePanel.tileSize*2, gamePanel.tileSize);
            attackLeft2 = setUp("/res/Player/DeckAxeL2", gamePanel.tileSize*2, gamePanel.tileSize);
            attackRight1 = setUp("/res/Player/DeckAxeR2", gamePanel.tileSize*2, gamePanel.tileSize);
            attackRight2 = setUp("/res/Player/DeckAxeR1", gamePanel.tileSize*2, gamePanel.tileSize);
        }

    }

    public void update() throws IOException { //updates in 60 fps to reflect changes on the game screen.

        if(attacking == true){
            attacking();
        }

        else if(keyHandler.upPressed == true || keyHandler.downPressed == true ||
                keyHandler.leftPressed == true || keyHandler.rightPressed == true ||
        keyHandler.enterPressed == true){


            // the following 4 if statements determine the player's direction based on the key listener.

            if(keyHandler.upPressed == true){
                direction = "up";

            }
            if(keyHandler.downPressed == true){
                direction = "down";

            }
            if(keyHandler.rightPressed == true){
                direction = "right";

            }
            if(keyHandler.leftPressed == true){
                direction = "left";

            }
            // the following lines are responsible for checking for the player's tile collision.
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this); // calls the collision checker passing the player as the entity to be checked.

            // the following lines are responsible for checking for the player's object collision.
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            contactMonster(monsterIndex);

            //CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.iTile);


            //CHECK EVENT
            gamePanel.eventHandler.checkEvent();


            if(collisionOn == false && keyHandler.enterPressed == false){ // the player can only move if collisionOn is false, meaning the tiles are not solid.

                switch (direction){
                    // the following 4 if increment or decrement the player's direction.
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
            if(keyHandler.enterPressed == true && attackCancelled == false){
                if(gamePanel.player.currentWeapon.type == type_Sword){
                    gamePanel.playSoundEffect(9);
                } else {
                    gamePanel.playSoundEffect(16);
                }
                attacking = true;
                spriteCounter = 0;
            }

            attackCancelled = false;
            gamePanel.keyHandler.enterPressed = false;


            // The following if statement is responsible for the speed at which the player animation sprites are changed.
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2 ){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            } else {
                standCounter++;
                if(standCounter == 20){
                    spriteNum = 1;
                    standCounter = 0;
                }

            }

        }
        if(gamePanel.keyHandler.shotKeyPressed == true && projectile.alive == false
         && shotAvailableCounter == 60 && projectile.haveResource(this) == true){

            //Set default coordinates, direction, and user.
            projectile.set(worldX, worldY, direction, true, this);

            //Subtract projectile cost
            projectile.subtractResource(this);

            //Add to the list
            gamePanel.projectileList.add(projectile);
            gamePanel.playSoundEffect(15);
            shotAvailableCounter = 0;
        }
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 80){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 60){
            shotAvailableCounter ++;

        }
    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5 ){
            spriteNum = 1;
        }
        if( spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //Save Current Player Position
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust Player's Area For Attacking
            switch (direction){
                case "up" : worldY -= attackArea.height; break;
                case "down" : worldY += attackArea.height; break;
                case "left" : worldX -= attackArea.width; break;
                case "right" : worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.iTile);
                damageInteractiveTile(iTileIndex);


            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int index) throws IOException {
        if(index != 999){


            //Pickup Only Items
            if(gamePanel.obj[index].type == type_PickUp){

                gamePanel.obj[index].use(this);
                gamePanel.obj[index] = null;
            }else {
                String text;
                if(inventory.size() != inventorySize){
                    inventory.add(gamePanel.obj[index]);
                    gamePanel.playSoundEffect(1);
                    text = "Got A " + gamePanel.obj[index].name + "!";


                }else {
                    text = "Your inventory is full.";
                }
                gamePanel.ui.addMessage(text);
                gamePanel.obj[index] = null;
            }
            }

            }




    public void interactNPC(int index){
      if(gamePanel.keyHandler.enterPressed == true){
          if(index != 999){
              attackCancelled = true;
              gamePanel.gameState = gamePanel.dialogueState;
              gamePanel.npc[index].speak();
          }
      }
      }

    public void contactMonster(int index){
        if(index != 999){
            if(invincible == false && gamePanel.monster[index].dying == false){
                gamePanel.playSoundEffect(8);
                //Damage Calculation Player to Monster
                int damage = gamePanel.monster[index].attack - defense;
                if(damage < 0 ){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }

        }
    }

    public void damageMonster(int index, int attack){
        if(index != 999){
            if(gamePanel.monster[index].invincible == false){
                gamePanel.playSoundEffect(10);

                //Damage Calculation Player to Monster
                int damage = attack - gamePanel.monster[index].defense;
                if(damage < 0 ){
                    damage = 0;
                }
                gamePanel.monster[index].life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
                gamePanel.monster[index].invincible = true;
                gamePanel.monster[index].damageReaction();

                if(gamePanel.monster[index].life <= 0){
                    gamePanel.monster[index].dying = true;
                    if(gamePanel.monster[index].dying == true){
                        gamePanel.monster[index].attack = 0;
                        gamePanel.ui.addMessage("Killed the " + gamePanel.monster[index].name+ "!");
                        gamePanel.ui.addMessage("Exp gained " + gamePanel.monster[index].exp);
                        exp += gamePanel.monster[index].exp;
                        checkLevelUp();
                    }
                }
            }
        }
    }
    public void damageInteractiveTile(int index){

        if(index != 999 && gamePanel.iTile[index].destructible == true &&
                gamePanel.iTile[index].isCorrectItem(this) == true &&
                gamePanel.iTile[index].invincible == false){

            gamePanel.iTile[index].life--;
            gamePanel.iTile[index].invincible = true;

            generateParticle(gamePanel.iTile[index], gamePanel.iTile[index]);

            if(gamePanel.iTile[index].life == 0){
                gamePanel.iTile[index] = gamePanel.iTile[index].getDestroyedForm();
            }
        }
    }
    public void checkLevelUp(){
        if(exp >= nextLvlExp){
            level++;
            nextLvlExp = nextLvlExp + 20;
            maxLife += 2;
            life += 2;
            if(life > maxLife){
                life = maxLife;
            }
            strength ++;
            dexterity ++;
            attack = getAttack();
            defense = getDefense();
            gamePanel.playSoundEffect(12);

            gamePanel.gameState = gamePanel.dialogueState;
            gamePanel.ui.currentDialogue = "You are level " + level + " now! ## " +
                    "You feel stronger!";
        }
    }

    public void selectItem(){
        int itemIndex = gamePanel.ui.getItemIndexOnSlot();
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_Sword || selectedItem.type == type_Axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_Shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_Consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        //this switch case handles drawing the correct sprite to correspond to player direction.
        switch(direction) {
            case "up" :
                if(attacking == false){
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                }
                if(attacking == true){
                    tempScreenY = screenY - gamePanel.tileSize;
                    if(spriteNum == 1){
                        image = attackUp1;
                    }
                    if(spriteNum == 2){
                        image = attackUp2;
                    }
                }
                break;
            case "down" :
                if(attacking == false){
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1){
                        image = attackDown2;
                    }
                    if(spriteNum == 2){
                        image = attackDown1;
                    }
                }
                break;
            case "left" :
                if(attacking == false){
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                }
                if(attacking == true){
                    tempScreenX = screenX - gamePanel.tileSize;
                    if(spriteNum == 1){
                        image = attackLeft1;
                    }
                    if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                break;
            case "right" :
                if(attacking == false){
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                }
                if(attacking == true){
                    if(spriteNum == 1){
                        image = attackRight2;
                    }
                    if(spriteNum == 2){
                        image = attackRight1;
                    }
                }
                break;
        }

        //MAKES THE PLAYER HALF TRANSPARENT WHEN INVINCIBLE
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null); //this line is responsible for actually drawing the player image.

        //RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible: " + invincibleCounter, 10, 400);

    }

}
