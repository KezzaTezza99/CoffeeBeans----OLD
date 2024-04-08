package engine;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel implements Runnable {
    //Screen settings
    final int originalTileSize;
    final int scale;
    final int tileSize;

    final int maxScreenCol;
    final int maxScreenRow;
    final int screenWidth;
    final int screenHeight;

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
