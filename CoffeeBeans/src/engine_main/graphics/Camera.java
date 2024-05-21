package engine_main.graphics;

import engine_main.GameWindow;

import java.awt.*;

//This current camera works by positioning itself at col,row (1,1) or x,y (48,48)
//The camera will span a 14x10 (col/row) or 672x480 area which by default engine terms will be the entire max screen col / row with a one tile border for padding
//I'm doing this for easier testing but eventually the engine will allow for support to position the camera anywhere in the game world

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
        this.x = gameWindow.getTileSize();
        this.y = gameWindow.getTileSize();
        this.col = x / gameWindow.getTileSize();
        this.row = y / gameWindow.getTileSize();
        this.width = gameWindow.getMaxScreenCol() - 2;
        this.height = gameWindow.getMaxScreenRow() - 2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int widthInPixels() {
        return width * gameWindow.getTileSize();
    }

    public int heightInPixels() {
        return height * gameWindow.getTileSize();
    }

    public int getCol() {
        return col;
    }

    private void setCol(int col) {
        this.col = col;
    }

    private void setRow(int row) {
        this.row = row;
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

    public void cameraInfo() {
        System.out.printf("Camera Column: %d%nCamera Row: %d%nCamera X: %d%n Camera Y: %d%nCamera Width: %d%nCamera Height: %d%n",
                getCol(), getRow(), getX(), getY(), getWidth(), getHeight());
    }

    public void setCamera() {
        //For now were just translating the camera if the player has hit the lower bounds
        //Move the y down 192 pixels and move the row down by 4?
        setY(192);
        setRow(getRow() + 4);
        setHeight(10);//ISSUE HEIGHT DOESN'T CHANGE, MAYBE MOVING THE WORLD INSTEAD OF CAMERA IS A BETTER OPTION

    }

    public void drawCamera(Graphics2D graphics2D) {
        graphics2D.setColor(Color.blue);
        //System.out.printf("x, y, w, h: %d %d %d %d%nWorld X, World Y: %d %d%n", x, y, width * 48, height * 48, worldX, worldY);
        graphics2D.drawRect(getX(), getY(),width * 48, height * 48);
    }
}