package Tiles;

import MainPackage.GamePanel;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            InputStream is = getClass().getResourceAsStream("/res/Maps/Sheoah3.txt");
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

        setUp(0, "Beach", false);
        setUp(1, "Beach2", false);
        setUp(2, "Beach3", false);
        setUp(3, "Beach4", false);
        setUp(4, "Beach5", false);
        setUp(5, "Beach6", false);
        setUp(6, "Beach7", false);
        setUp(7, "Beach8", false);
        setUp(8, "Beach9", false);
        setUp(9, "BeachC1", false);
        setUp(10, "BeachC2", false);
        setUp(11, "BeachC3", false);
        setUp(12, "BeachC4", false);
        setUp(13, "Dirt_1", false);
        setUp(14, "Dock_1", false);
        setUp(15, "Flowers_1", false);
        setUp(16, "Flowers_2", false);
        setUp(17, "Grass1", false);
        setUp(18, "Grass2", false);
        setUp(19, "GSB", false);
        setUp(20, "GSC2", false);
        setUp(21, "GSC3", false);
        setUp(22, "GSC4", false);
        setUp(23, "GSC5", false);
        setUp(24, "GSL", false);
        setUp(25, "GSR", false);
        setUp(26, "GST-export", false);
        setUp(27, "Ocean", true);
        setUp(28, "Path_1", false);
        setUp(29, "Sand_1", false);
        setUp(30, "Tile_1", false);
        setUp(31, "Tree_1", true);
        setUp(32, "Tree_2", true);
        setUp(33, "Tree_3", true);
        setUp(34, "Tree_4", true);
        setUp(35, "Tree_5", true);
        setUp(36, "WallCorner1", true);
        setUp(37, "WallCorner2", true);
        setUp(38, "WallCorner3", true);
        setUp(39, "WallCorner4", true);
        setUp(40, "WallSide1", true);
        setUp(41, "WallSide2", true);
        setUp(42, "Wall_1", true);
        setUp(43, "Wall_1", true);
        setUp(44, "Wall_2", false);
        setUp(45, "WaterL", true);
        setUp(46, "WaterB", true);
        setUp(47, "WaterBL", true);
        setUp(48, "WaterBR", true);
        setUp(49, "WaterIBL", true);
        setUp(50, "WaterIBR", true);
        setUp(51, "WaterITL", true);
        setUp(52, "WaterITR", true);
        setUp(53, "WaterL", true);
        setUp(54, "Water", true);
        setUp(55, "WaterR", true);
        setUp(56, "WaterT", true);
        setUp(57, "WaterTL", true);
        setUp(58, "WaterTR", true);
        setUp(59, "Water_2", true);
        setUp(60, "Z", false);
        setUp(61, "Z1", false);
        setUp(62, "Z2", false);
        setUp(63, "Z3", false);
        setUp(64, "ZBeach10", false);
        setUp(65, "ZBeach11", false);
        setUp(66, "ZBeach12", false);
        setUp(67, "ZBeach13", false);
        setUp(68, "ZGSC6", false);
        setUp(69, "ZGSC7", false);
        setUp(70, "ZGSC8", false);
        setUp(71, "ZGSC9", false);
        setUp(72, "ZHole", false);
        setUp(73, "ZWell", true);
        setUp(74, "ZWellBL", true);
        setUp(75, "ZWellBR", true);
        setUp(76, "ZWellTL", true);
        setUp(77, "ZWellTR", true);
        setUp(78, "ZZHouse1", true);
        setUp(79, "ZZHouse10", true);
        setUp(80, "ZZHouse11", true);
        setUp(81, "ZZHouse12", true);
        setUp(82, "ZZHouse2", true);
        setUp(83, "ZZHouse3", true);
        setUp(84, "ZZHouse4", true);
        setUp(85, "ZZHouse5", true);
        setUp(86, "ZZHouse6", true);
        setUp(87, "ZZHouse7", true);
        setUp(88, "ZZHouse8", true);
        setUp(89, "ZZHouse9", true);
        setUp(90, "ZZTree", true);
        setUp(91, "ZZTree2", true);
        setUp(92, "ZZZPath", false);
        setUp(93, "ZZZPath2", false);
        setUp(94, "ZZZPath3", false);
        setUp(95, "ZZZPath4", false);






    }
    public void setUp(int index, String imagePath, boolean collision){
        UtilityTool utilityTool = new UtilityTool();

        try{
            tile[index] = new Tiles();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/" + imagePath + ".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(Exception e){
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

                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }

            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }




    }
}
