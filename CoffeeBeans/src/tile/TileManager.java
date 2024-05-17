package tile;

import engine_main.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public int[][] mapTileData;                     //Stores info on all the tiles for every x,y space

    public TileManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        //--------------------------------------------------------------------------------------------------------------
        //TODO: Have this size set based on how many tiles are in the ("res/tiles/") directory? Will allow
        // all games made with the engine to have an array that fits all types of tiles makes it more dynamic and usable
        //--------------------------------------------------------------------------------------------------------------
        //Setting the size of the tile array (type of tiles in the game) (temp hard coding this size)
        tiles = new Tile[10];
        int i = gameWindow.getMaxScreenCol();
        int j = gameWindow.getMaxScreenRow();
        mapTileData = new int[i][j];
        //Loading the tile images
        getTileImage();
        loadGameWorld("/maps/map01.txt");
    }

    public void getTileImage() {
        //Loading all the tile images into the array
        //TODO: Could I implement a similar thing from 3D Graphics using enums to load the tiles?
        //TODO: Or following on from the directory info could loop through and store each image alphabetically
        // but also store a string for name (maybe a hashmap so later on i can reference what type of tile?)

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/test.png")));
            tiles[1].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameWorld(String worldTextFilePath) {
        //Provide a filePath to a worldMapTextFile that will store a grid of numbers.
        //That decided what kind of tile should be drawn, world maps allow the game to have a bigger
        //playable area compared to the screen.
        try {
            InputStream inputStream = getClass().getResourceAsStream(worldTextFilePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

            int col = 0;
            int row = 0;

            while(col < gameWindow.getMaxWorldCol() && row < gameWindow.getMaxWorldRow()) {
                //Reading the text file while it's in within limits of the screen size
                String line = bufferedReader.readLine();

                while(col < gameWindow.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileData[col][row] = num;
                    col++;
                }
                if(col == gameWindow.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch(Exception ignored) {
        }
    }

    public void draw(Graphics2D graphics2D) {
        //This is how you draw a tile image to the screen by default the engine will not draw anything
       //¬ graphics2D.drawImage(tiles[1].image, gameWindow.getScreenWidth() / 2, gameWindow.getScreenHeight() / 2, gameWindow.getTileSize(), gameWindow.getTileSize(), null);
    }
}