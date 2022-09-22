package Tiles;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager { // manager class for tiles on the game screen.

    GamePanel gp;
    public Tiles[] tile; // an array that holds the tile images.
    public int mapTileNum[][]; // a 2D array that represents a predetirmened map.

    public TileManager(GamePanel gp){ // constructor for the tile manager.
        this.gp = gp;

        tile = new Tiles[99];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // retrieves the proper tile numbers from the map text file.

        getTileImage();
        loadMap();
    }

    public void loadMap(){ //converts our text file into java.
        try{
            InputStream is = getClass().getResourceAsStream("/res/Maps/Map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){ // retrieves the tile images from the res folder

        try{
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Grass_1.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Wall_1.png"));
            tile[1].collision = true;

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Water_1.png"));
            tile[2].collision = true;

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Wall_2.png"));
            tile[3].collision = true;

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Sand_1.png"));

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Tree_1.png"));
            tile[5].collision = true;

            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Dirt_1.png"));

            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Path_1.png"));

            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Tree_2.png"));
            tile[8].collision = true;

            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Tile_1.png"));

            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Tree_3.png"));
            tile[10].collision = true;

            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Dock_1.png"));

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Tree_4.png"));
            tile[12].collision = true;

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Flowers_1.png"));

            tile[14] = new Tiles();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/Flowers_2.png"));




        } catch(IOException e ){
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g2){ // this method is responsible for drawing the map.

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){ // a loop that draws a tile for every open place on the game screen.

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            //g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }

//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);


    }
}
