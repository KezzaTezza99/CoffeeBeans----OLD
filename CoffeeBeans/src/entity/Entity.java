package entity;

import java.awt.image.BufferedImage;

/** Entity is a base class for all entities in the game to inherit from it will store some useful information for the user to
 * develop their own entities from
 */
public class Entity {
    public int x, y;                                        //Stores the entities x and y position
    public int speed;                                       //Stores the entities movement speed

    //Stores 10 necessary images to create animated movement for an entity
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, ideal1, ideal2;

    public String direction;                                //Stores information on the current direction the entity is facing
    public int spriteCounter;                               //Used to determine when the next image should be drawn for animation affects
    public int spriteNumber = 1;                            //Draw image 1 or image 2
}