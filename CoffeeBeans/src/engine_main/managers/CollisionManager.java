package engine_main.managers;

import engine_main.GameWindow;
import entity.Entity;

//ALL THIS CLASS NEEDS TO BE RESPONSIBLE FOR IS ENSURING THAT IT CAN DETECT AABB COLLISIONS!
//Need to figure out the best way for it to do that though
//Collision with tiles will be done differently? Should the TileManager handle that?
//Logically this class should also be responsible for tile collisions
public class CollisionManager {
    GameWindow gameWindow;          //Need access to the game window to get entities

    public CollisionManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    //TODO:
    /*
        - Entity at the moment we know is the player, should all entities have a tag? We then check the tag and decide on logic for colliding then
        - Do we offload checking collisions to each individual Entity and they all have access to the collision manager? They then pass themselves to the
          collision manager and then all check themselves against other objects using the GameWindow to access them?
     */
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
        if(entity.collisionBox.isCollidingWith(gameWindow.enemy.collisionBox)) {
            System.out.println("HIT");
        }
    }
}