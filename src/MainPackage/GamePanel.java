package MainPackage;

import Entity.Entity;
import Entity.Player;
import Tiles.TileManager;
import Tiles_Interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    public final int originalTileSize = 16; // num of pixels in the length/width of a tile.
    public final int scale = 3; // how many times we are multiplying the scale.
    public final int tileSize = originalTileSize * scale; // the actual size of a tile.

    // 4/3 ratio
    public final int maxScreenCol = 20; // the number of tiles displayed horizontally.
    public final int maxScreenRow = 12; // the number of tiles displayed vertically.
    public final int screenWidth = tileSize * maxScreenCol; // sets the appropriate width of the screen. 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // sets the appropriate height of the screen. 576 pixels

    //WORLD SIZE
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    //FULL SCREEN MODE
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    public final int fps = 60; // the number of frames per second the game will run at.

    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this); // instantiates the key handler.
    Sound sound = new Sound();
    Sound music = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this); // creates the collision checker object.
    public AssetSetter assetSetter = new AssetSetter(this); // creates the asset setter used to display objects.
    public UI ui = new UI(this); //creates the UI class object
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread; // a thread will keep processing the game continuously.

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler); // instantiates the player object.
    public Entity obj[] = new Entity[20]; //the number of objects the game can display.
    public Entity npc[] = new Entity[20];
    public Entity monster[] = new Entity[20];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;


    public GamePanel(){ //the constructor for the game's screen.
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //sets the window to the correct size.
        this.setBackground(Color.BLACK); //sets the default window bg to black.
        this.setDoubleBuffered(true); // all drawing from this component will be done in an offscreen painting buffer.
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //the game panel can be "focused" to receive key input.

    }
    public void setupGame(){
      assetSetter.setObject();
      assetSetter.setNPC();
      assetSetter.setMonster();
      assetSetter.setInteractiveTile();
      playMusic(0);
      gameState = titleState;

      tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
      g2 = (Graphics2D) tempScreen.createGraphics();
      setFullScreen();
    }
    public void setFullScreen(){

        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //GET SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();


    }

    public void startGameThread(){
        gameThread = new Thread(this); // passes the GamePanel class to the constructor.
        gameThread.start(); // starts the thread.
    }

    @Override
    public void run() { // handles how the thread will run.

      double drawInterval = 1000000000/fps; //redraw the screen every 0.01666666 seconds.
      double nextDrawTime = System.nanoTime() + drawInterval; // this computes the next time the screen will be drawn.

      while(gameThread != null){ // as long as the game thread exists processing continues.
          //UPDATE: update information such as character position.
          try {
              update();
          } catch (IOException e) {
              e.printStackTrace();
          }
          //DRAW: draw the screen with updated information.
          //repaint();
          drawToTempScreen();
          drawToScreen();

          try {
              double remainingTime = nextDrawTime - System.nanoTime(); // the time remaining until the next draw time.
              remainingTime = remainingTime/1000000; // converting remaining time from nanoseconds to milliseconds...
                                                     // because thread.sleep() accepts milliseconds.
              if(remainingTime < 0){ //remaining time cannot be less than 0.
                remainingTime = 0;
              }
              Thread.sleep((long) remainingTime); // puts the thread to sleep for the remaining time until the screen must be redrawn.
              nextDrawTime += drawInterval; // sets the next draw time.

          } catch (InterruptedException e) { //must be in a try/catch to handle this exception.
              e.printStackTrace();
          }
      }
    }

    public void update() throws IOException {

        if(gameState == playState){
            player.update(); // calls the player class update method.
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying ==false){
                        monster[i].update();
                    }
                    if(monster[i].alive != true){
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true){
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                }
            }
            for(int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null){
                    if(particleList.get(i).alive == true){
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }
            for(int i = 0; i < iTile.length; i++){
                if(iTile[i] != null){
                    iTile[i].update();
                }
            }
        }
        if(gameState == pauseState){
           //working on it...
        }

    }

    public void drawToTempScreen(){
        //DEBUG
        long drawStart = 0;
        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        } else {
            tileManager.draw(g2);


            //Interactive Tile
            for(int i = 0; i < iTile.length; i++){
                if(iTile[i] != null){
                    iTile[i].draw(g2);
                }
            }



            //ADD ENTITIES TO THE LIST
            entityList.add(player);
            for(int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            for(int i = 0; i < obj.length; i++){
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for(int i = 0; i < monster.length; i++){
                if (monster[i] != null){
                    entityList.add(monster[i]);
                }
            }
            for(int i = 0; i < projectileList.size(); i++){
                if (projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }
            for(int i = 0; i < particleList.size(); i++){
                if (particleList.get(i) != null){
                    entityList.add(particleList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });


            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();

            //DRAW UI
            ui.draw(g2);



            //DEBUG
            if(keyHandler.checkDrawTime == true){
                long drawEnd = System.nanoTime();
                long past = drawEnd - drawStart;
                g2.setColor(Color.cyan);
                g2.drawString("Draw time = " + past, 10, 400);
                System.out.println(past);
            }
        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0, 0 , screenWidth2, screenHeight2, null);
        g.dispose();
    }


//    public void paintComponent(Graphics g){ // how objects are drawn in the game. paintComponent is a method built into Java.
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g; //Graphics2D extends Graphics and provides more sophisticated control.
//
//        //DEBUG
//        long drawStart = 0;
//        if(keyHandler.checkDrawTime == true){
//            drawStart = System.nanoTime();
//        }
//
//        //TITLE SCREEN
//        if(gameState == titleState){
//            ui.draw(g2);
//        } else {
//            tileManager.draw(g2);
//
//
//            //Interactive Tile
//            for(int i = 0; i < iTile.length; i++){
//                if(iTile[i] != null){
//                    iTile[i].draw(g2);
//                }
//            }
//
//
//
//            //ADD ENTITIES TO THE LIST
//            entityList.add(player);
//            for(int i = 0; i < npc.length; i++){
//                if (npc[i] != null){
//                    entityList.add(npc[i]);
//                }
//            }
//            for(int i = 0; i < obj.length; i++){
//                if (obj[i] != null){
//                    entityList.add(obj[i]);
//                }
//            }
//            for(int i = 0; i < monster.length; i++){
//                if (monster[i] != null){
//                    entityList.add(monster[i]);
//                }
//            }
//            for(int i = 0; i < projectileList.size(); i++){
//                if (projectileList.get(i) != null){
//                    entityList.add(projectileList.get(i));
//                }
//            }
//            for(int i = 0; i < particleList.size(); i++){
//                if (particleList.get(i) != null){
//                    entityList.add(particleList.get(i));
//                }
//            }
//
//            //SORT
//            Collections.sort(entityList, new Comparator<Entity>() {
//                @Override
//                public int compare(Entity e1, Entity e2) {
//                    int result = Integer.compare(e1.worldY, e2.worldY);
//                    return result;
//                }
//            });
//
//
//            //DRAW ENTITIES
//            for(int i = 0; i < entityList.size(); i++){
//                entityList.get(i).draw(g2);
//            }
//            //EMPTY ENTITY LIST
//            entityList.clear();
//
//            //DRAW UI
//            ui.draw(g2);
//
//
//
//            //DEBUG
//            if(keyHandler.checkDrawTime == true){
//                long drawEnd = System.nanoTime();
//                long past = drawEnd - drawStart;
//                g2.setColor(Color.cyan);
//                g2.drawString("Draw time = " + past, 10, 400);
//                System.out.println(past);
//            }
//        }
//        g2.dispose(); // a good practice to save memory.
//}
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();



    }
}
