package MainPackage;

import Entity.Player;
import Tiles.TileManager;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable{

    public final int originalTileSize = 16; // num of pixels in the length/width of a tile.
    public final int scale = 3; // how many times we are multiplying the scale.
    public final int tileSize = originalTileSize * scale; // the actual size of a tile.

    // 4/3 ratio
    public final int maxScreenCol = 16; // the number of tiles displayed horizontally.
    public final int maxScreenRow = 12; // the number of tiles displayed vertically.
    public final int screenWidth = tileSize * maxScreenCol; // sets the appropriate width of the screen. 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // sets the appropriate height of the screen. 576 pixels

    public final int maxWorldCol = 58;
    public final int maxWorldRow = 60;



    public final int fps = 60; // the number of frames per second the game will run at.

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(); // instantiates the key handler.
    Sound sound = new Sound();
    Sound music = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this); // creates the collision checker object.
    public AssetSetter assetSetter = new AssetSetter(this); // creates the asset setter used to display objects.
    public UI ui = new UI(this); //creates the UI class object
    Thread gameThread; // a thread will keep processing the game continuously.
    public Player player = new Player(this, keyHandler); // instantiates the player object.
    public SuperObject obj[] = new SuperObject[10]; //the number of objects the game can display.





    public GamePanel(){ //the constructor for the game's screen.
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //sets the window to the correct size.
        this.setBackground(Color.BLACK); //sets the default window bg to black.
        this.setDoubleBuffered(true); // all drawing from this component will be done in an offscreen painting buffer.
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //the game panel can be "focused" to receive key input.

    }
    public void setupGame(){
      assetSetter.setObject();
      playMusic(0);
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
          repaint();

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
        player.update(); // calls the player class update method.
    }

    public void paintComponent(Graphics g){ // how objects are drawn in the game. paintComponent is a method built into Java.
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //Graphics2D extends Graphics and provides more sophisticated control.
        tileManager.draw(g2);

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //Testing player character
        player.draw(g2); // calls the player class draw method.

        ui.Draw(g2);
        g2.dispose(); // a good practice to save memory.
    }
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
