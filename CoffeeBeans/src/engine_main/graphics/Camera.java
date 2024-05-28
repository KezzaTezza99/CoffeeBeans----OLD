package engine_main.graphics;
import engine_main.GameWindow;

import java.awt.*;

//This current camera works by positioning itself at col,row (1,1) or x,y (48,48)
//The camera will span a 14x10 (col/row) or 672x480 area which by default engine terms will be the entire max screen col / row with a one tile border for padding
//I'm doing this for easier testing but eventually the engine will allow for support to position the camera anywhere in the game world
//RENAME THIS TO CAMERA BOUNDS? AS IT ISN'T REALLY A CAMERA IN THE TYPICAL SENSE IT'S JUST A WAY TO IMAGINE WHAT IT IS DOING.
public class Camera {

    private GameWindow gameWindow;
    private int x;
    private int y;
    private int width;
    private int height;
    private int col;
    private int row;

    public Camera(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        x = 0;
        y = 0;
        col = x / gameWindow.getTileSize();
        row = y / gameWindow.getTileSize();
        width = gameWindow.getMaxScreenCol();
        height = gameWindow.getMaxScreenRow();
    }

    //NOT USED ANYMORE--------------------------------------------------------------------------------------------------
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
    //------------------------------------------------------------------------------------------------------------------

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
        if (playersYPos == getRow()) {
            System.out.println("Hitting upper bounds");
            return true;
        }
        return false;
    }

    public boolean hasHitLowerBounds(int playerYPos) {
        if(playerYPos == getHeight()) {
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
        if(playerXPos == getWidth()) {
            System.out.println("Hitting right bounds");
            return true;
        }
        return false;
    }

    public void setCamera() {

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