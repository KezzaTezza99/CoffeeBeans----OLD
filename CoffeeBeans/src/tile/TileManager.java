package tile;

import engine_main.GameWindow;
import engine_main.physics.AABB;
import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

/**
 * The tile manager is responsible for creating and storing data that will make up the game window.
 * Tiles will be predetermined sizes set in the GameWindow by the user i.e., 16x16, these 16x16 sprites
 * will take up a 16x16 tile in the world / game. All graphical objects i.e., sprites or background tiles will
 * be stored in an array with all the necessary URLs for loading the file from the res folder
 * ---------------------------------------------------------------------------------------------------------------------
 * mapTileData will then store a 2D array that holds information on what tile is being drawn in each tile for the
 * width and height of the entire game window
 * ---------------------------------------------------------------------------------------------------------------------
 */
public class TileManager {
    //Need access to the game window to draw the tiles to the screen
    GameWindow gameWindow;

    //Tile info
    public Tile[] tiles;                             //Stores all the types of tiles in the game
    public int[][] mapTileData;                      //Stores info on all the tiles for every x,y space
    public Tile[][] tileData;

    public TileManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        tiles = new Tile[11];


        //Loading the tile images
        getTileImage();

        //--------------------------------------------------------------------------------------------------------------
        //TODO: Have this size set based on how many tiles are in the ("res/tiles/") directory? Will allow
        // all games made with the engine to have an array that fits all types of tiles makes it more dynamic and usable
        //--------------------------------------------------------------------------------------------------------------
        //Setting the size of the tile array (type of tiles in the game) (temp hard coding this size) (length - 1 will be the tile that the map is initialized to)
        mapTileData = new int[16][12];
        tileData = new Tile[16][12];

        for (int i = 0; i < mapTileData.length; i++){         // iterates each student
            for (int j = 0; j < mapTileData[i].length; j++){  // iterates each grade
                mapTileData[i][j] = 10;
            }
        }

        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 12; y++) {
                tileData[x][y] = tiles[10];
                //tileData[x][y].tileCollisionBox = new AABB(x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), 48, 48);
            }
        }

        //loadGameWorld("/maps/map01.txt");
        loadTest();
    }

    public void getTileImage() {
        //Loading all the tile images into the array
        // but also store a string for name (maybe a hashmap so later on i can reference what type of tile?)

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/test.png")));
            tiles[1].collision = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/defaultTile.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isTileCollidable(int x, int y) {
        //We know that the last index in the tiles array is the default value for the entire mapTileData, so we can ignore this index while checking if a tile is collidable
        //This is a default tile that is used for initialization and potentially be used if any errors happen during map loading
        int index = mapTileData[x][y];
        if(tiles[index].collision) System.out.println("Oh no you have collided!");
        return tiles[index].collision;
    }

    //TODO: Actuall load map data
    public void loadTest() {
//        //The array will just be filled with numbers - each number will be related to a type of tile [0, 1, 2, 3] etc. [0 - grass, 1 - stone...]
//        mapTileData[5][5] = 1;
//        mapTileData[6][5] = 1;
//        mapTileData[0][0] = 1;

    }

    public void loadGameWorld(String worldTextFilePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(worldTextFilePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

            int col = 0;
            int row = 0;

            while(col < gameWindow.maxWorldCol && row < gameWindow.maxWorldRow) {
                //Reading the text file while it's in within limits of the screen size
                String line = bufferedReader.readLine();

                while(col < gameWindow.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileData[col][row] = num;
                    col++;
                }
                if(col == gameWindow.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch(Exception ignored) {

        }
    }


    //TODO: Actually draw the map
    public void draw(Graphics2D graphics2D) {
        /*
        //This is how you draw a tile image to the screen by default the engine will not draw anything
        // graphics2D.drawImage(tiles[1].image, gameWindow.getScreenWidth() / 2 + 32, gameWindow.getScreenHeight() / 2+32, gameWindow.getTileSize(), gameWindow.getTileSize(), null);

/*        for(int y = 0; y < 12; y++) {
            for(int x = 0; x < 16; x++) {
                graphics2D.drawImage(tiles[0].image, x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
            }
        }

        //graphics2D.drawImage(tiles[1].image, 5 * gameWindow.getTileSize(), 5 * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
        //tiles[1].tileCollisionBox.drawCollider(graphics2D, Color.MAGENTA);

        graphics2D.drawImage(tiles[1].image, 0, 0, gameWindow.getTileSize(), gameWindow.getTileSize(), null);
        graphics2D.drawImage(tiles[1].image, 5 * gameWindow.getTileSize(), 5 * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
        graphics2D.drawImage(tiles[1].image, 6 * gameWindow.getTileSize(), 5 * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);

        for(int y = 0; y < 12; y++) {
            for(int x = 0; x < 16; x++) {
                //graphics2D.drawImage(tileData[x][y].image, x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
            }
        }

        //tileData[0][0].tileCollisionBox = new AABB(0, 0, 48, 48);
       // tileData[1][0].tileCollisionBox = new AABB(48, 48, 48, 48);

        //tileData[0][0].tileCollisionBox.drawCollider(graphics2D, Color.red);
        //tileData[1][0].tileCollisionBox.drawCollider(graphics2D, Color.yellow);

        //Drawing a grid
        graphics2D.setColor(Color.white);
        for(int y = 0; y < 12; y++) {
            for(int x = 0; x < 16; x++) {
                graphics2D.drawRect(x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize());
            }
        }
        */


    }
}