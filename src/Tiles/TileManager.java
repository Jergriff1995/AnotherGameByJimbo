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
    public int mapTileNum[][][]; // a 2D array that represents a predetirmened map.

    public TileManager(GamePanel gp){ // constructor for the tile manager.
        this.gp = gp;

        tile = new Tiles[250];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol+1][gp.maxWorldRow+1]; // retrieves the proper tile numbers from the map text file.

        getTileImage();
        loadMap("/res/Maps/Sheoah.txt", 0);
        loadMap("/res/Maps/MattHouse.txt", 1);
        loadMap("/res/Maps/ChaseShop.txt", 2);
        loadMap("/res/Maps/JohnnyHouse.txt", 3);
        loadMap("/res/Maps/TannerHouse.txt", 4);
    }

    public void loadMap(String filepath, int map){ //converts our text file into java.
        try{

            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
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
        setUp(82, "ZZHouse2", false);
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
        setUp(96, "ZZZZCliff", true);
        setUp(97, "ZZZZCliff2", true);
        setUp(98, "ZZZZStairs", false);
        setUp(99, "ZZZZStairs2", false);
        setUp(100, "ZZZZZCliff", false);
        setUp(101, "ZZZZZCliff2", false);
        setUp(102, "ZZZZZCliff3", false);
        setUp(103, "ZZZZZCliff4", false);
        setUp(104, "ZZZZZCliff5", false);
        setUp(105, "ZZZZZZIntWall", true);
        setUp(106, "ZZZZZZIntWall2", true);
        setUp(107, "ZZZZZZIntWall3", true);
        setUp(108, "ZZZZZZIntWall4", true);
        setUp(109, "ZZZZZZIntWall5", true);
        setUp(110, "ZZZZZZIntWall6", true);
        setUp(111, "ZZZZZZVoid", true);
        setUp(112, "ZZZZZZWoodFloor", false);
        setUp(113, "ZZZZZZWoodFloor2", false);
        setUp(114, "ZZZZZZWoodFloor3", false);
        setUp(115, "ZZZZZZZShop1", true);
        setUp(116, "ZZZZZZZShop2", true);
        setUp(117, "ZZZZZZZShop3", true);
        setUp(118, "ZZZZZZZShop4", true);
        setUp(119, "ZZZZZZZShop5", true);
        setUp(120, "ZZZZZZZShop6", true);
        setUp(121, "ZZZZZZZShop7", true);
        setUp(122, "ZZZZZZZShop8", false);
        setUp(123, "ZZZZZZZShop9", true);
        setUp(124, "ZZZZZZZShopFloor2", false);
        setUp(125, "ZZZZZZZShopFloor3", false);
        setUp(126, "ZZZZZZZShopFloor4", false);
        setUp(127, "ZZZZZZZShopTile", false);
        setUp(128, "ZZZZZZZShopWall", true);
        setUp(129, "ZZZZZZZShopWall2", true);

        setUp(130, "ZZZZZZZZBuilding1", true);
        setUp(131, "ZZZZZZZZBuilding10", false);
        setUp(132, "ZZZZZZZZBuilding11", false);
        setUp(133, "ZZZZZZZZBuilding12", true);
        setUp(134, "ZZZZZZZZBuilding2", true);
        setUp(135, "ZZZZZZZZBuilding3", true);
        setUp(136, "ZZZZZZZZBuilding4", true);
        setUp(137, "ZZZZZZZZBuilding5", true);
        setUp(138, "ZZZZZZZZBuilding6", true);
        setUp(139, "ZZZZZZZZBuilding7", true);
        setUp(140, "ZZZZZZZZBuilding8", true);
        setUp(141, "ZZZZZZZZBuilding9", true);

        setUp(142, "ZZZZZZZZBuildingTwo1", true);
        setUp(143, "ZZZZZZZZBuildingTwo10", false);
        setUp(144, "ZZZZZZZZBuildingTwo11", true);
        setUp(145, "ZZZZZZZZBuildingTwo12", true);
        setUp(146, "ZZZZZZZZBuildingTwo2", true);
        setUp(147, "ZZZZZZZZBuildingTwo3", true);
        setUp(148, "ZZZZZZZZBuildingTwo4", true);
        setUp(149, "ZZZZZZZZBuildingTwo5", true);
        setUp(150, "ZZZZZZZZBuildingTwo6", true);
        setUp(151, "ZZZZZZZZBuildingTwo7", false);
        setUp(152, "ZZZZZZZZBuildingTwo8", true);
        setUp(153, "ZZZZZZZZBuildingTwo9", true);

        setUp(154, "ZZZZZZZZFloor4", false);
        setUp(155, "ZZZZZZZZWall", true);
        setUp(156, "ZZZZZZZZWall2", true);








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

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

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
