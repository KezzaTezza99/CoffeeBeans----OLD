package engine_main.graphics;

import engine_main.GameWindow;

import java.awt.*;

//TODO: Lets refactor this entire class to work with the new camera idea in mind... i.e., need to render the camera based on the centre of the map... for now!

//This current camera works by positioning itself at col,row (1,1) or x,y (48,48)
//The camera will span a 14x10 (col/row) or 672x480 area which by default engine terms will be the entire max screen col / row with a one tile border for padding
//I'm doing this for easier testing but eventually the engine will allow for support to position the camera anywhere in the game world

//RENAME THIS TO CAMERA BOUNDS? AS IT ISN'T REALLY A CAMERA IN THE TYPICAL SENSE ITS JUST A WAY TO IMAGINE WHAT IT IS DOING.
public class Camera {

    private GameWindow gameWindow;
    private int x;
    private int y;
    private int worldX;
    private int worldY;
    private int width;
    private int height;
    private int col;
    private int row;

    public Camera(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        //The starting position aka x,y can't start at 48,48 anymore... we need start at the top left corner of the current screen col / row
        //X, Y  should then be calculated using (still presuming that we're using centre position of 50,50) 25x25 (1,200x1,200)
/*
        this.x = gameWindow.getTileSize(); //48 (1,1)
        this.y = gameWindow.getTileSize(); //48 (1,1)


        this.col = x / gameWindow.getTileSize();
        this.row = y / gameWindow.getTileSize();
        this.width = gameWindow.getMaxScreenCol() -2; //
        this.height = gameWindow.getMaxScreenRow() -2; //
*/

        //+ is camera width and height
        //this.width = col + 13;
        //this.height = row + 9;

        //Right where now creating the camera based on the screen position within the world...
        //We want to build the camera bounds based on the current screen bounds - 1 on both axis to give us a border
        //-------------------------------------------------------------------------------------------------------------
        // x, y right now for this example is the screen 0,0 which is = tile 1.1 or 48,48
        //x =;
        //y = 48;

        //Create the camera to reflect the player?
        //x = gameWindow.player.x;
        //y = gameWindow.player.y;



        //REFACTORING???
        //TODO: CHECK TO SEE IF THESE VALUES ARE CORRECT AND ARE WHAT IM EXPECTING
        //Using the assumption that at first the engine renders the player at mid-map and mid-screen meaning the cam should be using 25,25 as a centre point also!
        //As the camera will be rendering from the top left we need to offset the centre point
        int rightOffset = gameWindow.getMaxScreenCol() / 2;                 //How many tiles to the left we should push the origin point
        int bottomOffset = gameWindow.getMaxScreenRow() / 2;                //How many tiles upwards we should push the origin point

        this.x = ((gameWindow.maxWorldCol / 2) - rightOffset) * gameWindow.getTileSize();
        this.y = ((gameWindow.maxWorldRow / 2) - bottomOffset) * gameWindow.getTileSize();
        this.col = x / gameWindow.getTileSize();
        this.row = y / gameWindow.getTileSize();

        //The width and height of the camera should be the (max screen row and col - 2) to give us a nice padding of one tile between camera collision and border of screen
        this.width = gameWindow.getMaxScreenCol() - 2;
        this.height = gameWindow.getMaxScreenRow() - 2;

        System.out.printf("Camera INFO: x: %d, y: %d, col: %d, row: %d, width: %d, height: %d %n", this.x, this.y, this.col, this.row, this.width, this.height);
    }

    public int getCameraX1() {
        return gameWindow.screenX1 + 1;
    }

    public int getCameraX2() {
        return gameWindow.screenX2 - 1;
    }

    public int getCameraY1() {
        return gameWindow.screenY1 + 1;
    }

    public int getCameraY2() {
        return gameWindow.screenY2 - 1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean hasHitUpperBounds(int playersYPos) {
        if(playersYPos == getRow()) {
            System.out.println("Hitting upper bounds");
            return true;
        }
        return false;
    }

    public boolean hasHitLowerBounds(int playerYPos) {
        if(playerYPos == getHeight() - 1) {
            System.out.println("Hitting lower bounds");
            return true;
        }
        return false;
    }

    public boolean hasHitLeftBounds(int playerXPos) {
        if(playerXPos == getCol()) {
            System.out.println("Hitting left bounds");
            return true;
        }
        return false;
    }

    public boolean hasHitRightBounds(int playerXPos) {
        if(playerXPos == getWidth() - 1) {
            System.out.println("Hitting right bounds");
            return true;
        }
        return false;
    }

    public void setCamera() {
        //For now were just translating the camera if the player has hit the lower bounds
        //Move the y down 192 pixels and move the row down by 4?
        //setY(192);
        //setRow(4);
        //setHeight(row + 9);
        //cameraInfo();
        //simulateYPos(y);
    }

    public void drawCamera(Graphics2D graphics2D) {
        graphics2D.setColor(Color.red);
        System.out.printf("Drawing camera------------%n x1: %d%nY1: %d%nWidth: %d (%d)%nHeight: %d (%d)%n", getCameraX1(), getCameraY1(), width, width * 48, height, height * 48);
        graphics2D.drawRect(getCameraX1(), getCameraY1(),width * 48, height * 48);
    }

    public int simulateXPos(int topLeftOfCurrentWorldLocation) {
        //We know where the world is currently rendering from
        return -1;
    }

    //Add a direction here also hardcoded values should probably become some kind of constant
    public int simulateYPos(int topLeftOfCurrentWorldLocation) {
        System.out.println("Top left position before translation " + topLeftOfCurrentWorldLocation);
        //We know where the left pos is, if the player has hit the bounds then translate up / down
        //We need to first start from the top left pos, then add the camera height to the value before adding the transformation value
        topLeftOfCurrentWorldLocation += (gameWindow.getScreenHeight()) + (4 * gameWindow.getTileSize());
        System.out.println("Top left position after translation " + topLeftOfCurrentWorldLocation);
        return topLeftOfCurrentWorldLocation;
    }
}