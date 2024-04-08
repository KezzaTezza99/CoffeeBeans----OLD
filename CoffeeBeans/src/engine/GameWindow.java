package engine;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel implements Runnable {
    //SCREEN SETTINGS
    //Tile refers to a collection of pixels on screen that sprites etc. will occupy (16x16, 32x32, 64x64)
    final int originalTileSize;                     //Will be the sprites original size i.e., 16x16
    final int scale;                                //Higher resolution monitors will need small sprites to be scaled up
    final int tileSize;                             //The new size of a tile

    //I.e., create a max screen width x height of (16 x 12) which would be the aspect ratio 4:3
    final int maxScreenCol;                         //Max amount of tiles that can be displayed on the X (i.e., 16)
    final int maxScreenRow;                         //Max amount of tiles that can be displayed on the Y (i.e., 12)

    //Using the 16x12 col x row example this would create a screenWidth x screenHeight of (768 x 576)
    final int screenWidth;                          //The width of the game window
    final int screenHeight;                         //The height of the game window

    //Want more control over these parameters in case a game wants to use different size tiles, aspect ratios etc.
    public GameWindow(int ogTileSize, int scale, int maxScreenCol, int maxScreenRow) {
        //Initialing the screen settings
        this.originalTileSize = ogTileSize;
        this.scale = scale;
        this.tileSize = ogTileSize * scale;

        //Creating the aspect ratio
        this.maxScreenCol = maxScreenCol;
        this.maxScreenRow = maxScreenRow;
        this.screenWidth = tileSize * maxScreenCol;
        this.screenHeight = tileSize * maxScreenRow;

        //Setting up the game window
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //this.addKeyListener(input);
        this.setFocusable(true);
    }

    @Override
    public void run() {

    }
}
