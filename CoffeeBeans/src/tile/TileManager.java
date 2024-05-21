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
        mapTileData = new int[gameWindow.maxWorldCol][gameWindow.maxWorldRow];
        loadGameWorld("/maps/map02.txt");
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

    public void draw(Graphics2D graphics2D) {
        //Drawing the map
/*        for(int x = 0; x < gameWindow.maxWorldCol; x++) {
            for(int y = 0; y < gameWindow.maxWorldRow; y++) {
                int tile = mapTileData[x][y];
                graphics2D.drawImage(tiles[tile].image, x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
            }
        }*/

        System.out.println("Row: " + gameWindow.camera.getRow());
        //Drawing the map using the camera
        for(int x = gameWindow.camera.getCol(); x <= gameWindow.camera.getWidth(); x++) {
            for(int y = gameWindow.camera.getRow(); y <= gameWindow.camera.getHeight(); y++) {
                int tile = mapTileData[x][y];
                graphics2D.drawImage(tiles[tile].image, x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
            }
        }

        //TODO: Have a debug flag? Lets me have useful info displayed?
        boolean debug = false;

        if(debug) {
            //Drawing a grid - Useful debugging info
            graphics2D.setColor(Color.white);
            for (int y = 0; y < 12; y++) {
                for (int x = 0; x < 16; x++) {
                    graphics2D.drawRect(x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize());
                }
            }
        }
    }
}