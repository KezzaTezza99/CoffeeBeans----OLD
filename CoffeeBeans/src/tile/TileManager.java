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
    public int[][] screenTileData;

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
        screenTileData = new int[gameWindow.getMaxScreenCol()][gameWindow.getMaxScreenRow()];
        loadGameWorld("/maps/world03.txt");
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

            for(int i = 2; i <=9; i++) {
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/defaultTile.png")));
            }

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
/*        //Drawing the map using the camera - NEW
        int x1 = 0;
        int y1 = 0;
        int counter = 0;

        //Do we need to use a different x,y in draw method...
        for(int x = gameWindow.screenX1; x <= gameWindow.screenX2; x++) {
            for(int y = gameWindow.screenY1; y <= gameWindow.screenY2; y++) {
                //int tile = mapTileData[x][y];
//                System.out.printf("%d %d, %d%n", x, y, counter);
//                //if(tile == 1) System.out.println("TILE: " + tile);
//                graphics2D.drawImage(tiles[tile].image, x1, y1, gameWindow.getTileSize(), gameWindow.getTileSize(), null);
//                x1 += gameWindow.getTileSize();
//
//                //We like need to move down the y axis now and draw the tile there

                int tile = mapTileData[x][y];
                graphics2D.drawImage(tiles[tile].image, x1, y1, gameWindow.getTileSize(), gameWindow.getTileSize(), null);
                x1+=gameWindow.getTileSize();
                if(x1 == gameWindow.maxWorldCol) {
                    x1 = 0;
                    y1+=gameWindow.getTileSize();
                }
//
            }
        }*/

        //NEW ATTEMPT
        int i = 0;
        int j = 0;

        //Getting the tile data from the worldMapData[][] using the x,y from the current on screen coordinates and storing them in the screenMapData[][]
        //This will allow us to retrieve the tiles from the world and render just that section on the screen
        for (int y = gameWindow.screenY1; y < gameWindow.screenY2; y++) {
            for (int x = gameWindow.screenX1; x < gameWindow.screenX2; x++) {
                screenTileData[i][j] = mapTileData[x][y];
                int tile = mapTileData[x][y];
                graphics2D.drawImage(tiles[tile].image, i * gameWindow.getTileSize(), j * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize(), null);
                i++;
                if (i == gameWindow.getMaxScreenCol()) {
                    i = 0;
                    j++;
                }
            }
        }

        //TODO: Have a debug flag? Lets me have useful info displayed?
        boolean debug = true;
        boolean verbose = false;

        if(debug) {
            //Drawing a grid - Useful debugging info
            graphics2D.setColor(Color.black);
            for (int y = 0; y < 12; y++) {
                for (int x = 0; x < 16; x++) {
                    graphics2D.drawRect(x * gameWindow.getTileSize(), y * gameWindow.getTileSize(), gameWindow.getTileSize(), gameWindow.getTileSize());
                }
            }
        }

        if(verbose) {
            //WORLD DEBUG
            System.out.printf("%n%nINFO: World X1: %d,%d%nWorld X2: %d,%d%n", gameWindow.screenX1, gameWindow.screenY1, gameWindow.screenX2, gameWindow.screenY1);
            System.out.printf("World Y1: %d,%d%nWorld Y2: %d,%d%n", gameWindow.screenX1, gameWindow.screenY2, gameWindow.screenX2, gameWindow.screenY2);

            //CAMERA DEBUG
            System.out.printf("INFO: Camera X1: %d,%d%nCamera X2: %d,%d%n", gameWindow.camera.getCameraX1(), gameWindow.camera.getCameraY1(), gameWindow.camera.getCameraX2(), gameWindow.camera.getCameraY1());
            System.out.printf("Camera Y1: %d,%d%nCamera Y2: %d,%d%n", gameWindow.camera.getCameraX1(), gameWindow.camera.getCameraY2(), gameWindow.camera.getCameraX2(), gameWindow.camera.getCameraY2());
        }
    }
}