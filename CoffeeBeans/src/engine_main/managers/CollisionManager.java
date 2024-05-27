package engine_main.managers;
import engine_main.GameWindow;
import entity.Entity;

public class CollisionManager {
    GameWindow gameWindow;          //Need access to the game window to get entities

    public CollisionManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void checkEntity(Entity entity) {
        //Maybe something like this?
        /*
            if(entity.collisionBox.getID() == "player") {
                //We know it's the player so check the player's collisions
                if(entity.collisionBox.isCollidingWith(gameWindow.enemy.collisionBox) {
                   //We now know they've hit an enemy so do something about it
               }
            } else if (entity.collisionBox.getID() == "enemy") {
                //We know it's the enemy so check the enemies collisions
                if(entity.collisionBox.isCollidingWith(gameWindow.player.collisionBox) {
                //We now know they've hit the player so do something about it
                }
            }
            Horrible implementation but should work? Maybe we have an entity manager that stores a list off all entities on the screen
            the EM is then responsible for doing these checks in a nice for loop that checks all entities at once? They pass themselves in but still loop through based on ID?
            Then based on ID react in a similar way to method above?
         */

        //Working but will be messy for all entities to check collisions this way and react accordingly
        if (entity.collisionBox.isCollidingWith(gameWindow.enemy.collisionBox)) {
            System.out.println("HIT");
        }
    }

    public void tileIsFree(Entity entity) {
        entity.canMoveUp = true; entity.canMoveDown = true; entity.canMoveLeft = true; entity.canMoveRight = true;

        //Get the current position in the tile map array
        int entitiesCurrentXInArray = gameWindow.getCurrentColumnPosition(entity.x);
        int entitiesCurrentYInArray = gameWindow.getCurrentRowPos(entity.y);

        int tile;

        //TODO: why are we trigging down, left and right???
        //System.out.printf("")
        switch (entity.direction) {
            case "up":
                entitiesCurrentXInArray = (entity.x + (gameWindow.getTileSize()) / 2) / gameWindow.getTileSize();
                entitiesCurrentYInArray = entity.y / gameWindow.getTileSize();

                tile = gameWindow.tileManager.mapTileData[entitiesCurrentXInArray][entitiesCurrentYInArray];

                if(gameWindow.tileManager.tiles[tile].collision) {
                    System.out.println("Collision up");
                    entity.canMoveUp = false;
                }
                break;
            case "down":
                entitiesCurrentXInArray = (entity.x + (gameWindow.getTileSize()) / 2) / gameWindow.getTileSize();
                entitiesCurrentYInArray = entity.y  / gameWindow.getTileSize();

                tile = gameWindow.tileManager.mapTileData[entitiesCurrentXInArray][entitiesCurrentYInArray + 1];
                if(gameWindow.tileManager.tiles[tile].collision) {
                    System.out.println("Collision down");
                    entity.canMoveDown = false;
                }
            case "left":
                entitiesCurrentXInArray = entity.x / gameWindow.getTileSize();
                entitiesCurrentYInArray = (entity.y + (gameWindow.getTileSize()) / 2) / gameWindow.getTileSize();

                tile = gameWindow.tileManager.mapTileData[entitiesCurrentXInArray][entitiesCurrentYInArray];
                if(gameWindow.tileManager.tiles[tile].collision) {
                    System.out.println("Collision left");
                    entity.canMoveLeft = false;
                }
            case "right":
                entitiesCurrentXInArray = (entity.x + gameWindow.getTileSize()) / gameWindow.getTileSize();
                entitiesCurrentYInArray = (entity.y + (gameWindow.getTileSize() / 2))  / gameWindow.getTileSize();

                tile = gameWindow.tileManager.mapTileData[entitiesCurrentXInArray][entitiesCurrentYInArray];
                if(gameWindow.tileManager.tiles[tile].collision) {
                    System.out.println("Collision right");
                    entity.canMoveRight = false;
                }
        }
    }
}