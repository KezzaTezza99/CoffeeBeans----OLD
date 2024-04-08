package tile;

import engine_main.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

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
        mapTileData = new int[gameWindow.getMaxScreenCol()][gameWindow.getMaxScreenRow()];

        //Loading the tile images
        getTileImage();
    }

    public void getTileImage() {
        //Loading all the tile images into the array
        //TODO: Could I implement a similar thing from 3D Graphics using enums to load the tiles?
        //TODO: Or following on from the directory info could loop through and store each image alphabetically
        // but also store a string for name (maybe a hashmap so later on i can reference what type of tile?)

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(tiles[0].image, 0, 0, gameWindow.getTileSize(), gameWindow.getTileSize(), null);
    }
}
