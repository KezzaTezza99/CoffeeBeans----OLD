package engine_main.managers;

import engine_main.GameWindow;
import entity.Entity;

public class Collision {
    GameWindow gameWindow;          //Need access to the game window to get entities

    public Collision(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    //Check what entities are colliding with one another and take action based on the entity
    //Entities include playable characters and non-playable characters
    public void checkEntity(Entity entity) {
        //Collisions work by drawing an invisible rectangle around an entity,
        //so we need to get the edges of the rectangle in the world space

        //The entities that can be in the surrounding 4 spaces (left, right, up, down)
        //These entities (tiles) are stored in the tileManager mapTileData array
    }

    //Objects include all other game objects within the game i.e., pickups
    //Is it the player interacting with the object? This is useful to know to stop NPCs interacting
    //with game objects if the desired affect is for players only
    public int checkObject(Entity entity, boolean isPlayer) {
        return 1;
    }
}