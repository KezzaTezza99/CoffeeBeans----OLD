package engine_main;
import tile.Tile;
import tile.TileManager;

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

    //Using threads to create delta time for the game loop
    Thread gameThread;
    int FPS = 60;

    //Tile manager to manage the placement of tiles
    TileManager tileManager = new TileManager(this);

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

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Manipulating system time to create delta time to limit the game to FPS (default 60)
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        //Game loop
        while(gameThread != null) {
            //Getting system time to restrict the game to FPS
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                //Updating game data
                update();
                //Drawing game data
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.printf("FPS: %d%n", drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        //Will update the player here
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        //Drawing the tiles
        tileManager.draw(graphics2D);

        //Disposing of the graphics
        graphics2D.dispose();
    }

    //------------------------------------------------------------------------------------------------------------------
    //                          GETTER METHODS
    //------------------------------------------------------------------------------------------------------------------
    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}