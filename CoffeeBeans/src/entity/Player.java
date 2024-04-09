package entity;

import engine_main.GameWindow;
import engine_main.InputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

//TODO: Pass a lot of the info in as params? So creating a player can decide on unique settings
public class Player extends Entity {
    GameWindow gameWindow;
    InputHandler input;

    public Player(GameWindow gameWindow, InputHandler inputHandler) {
        this.gameWindow = gameWindow;
        this.input = inputHandler;

        //Setting default starting position for the player
        x = 100;
        y = 100;

        //Setting players speed and default starting position
        speed = 4;
        direction = "down";

        loadPlayerSprites();
    }

    public void loadPlayerSprites() {
        try {
            //Try loading the player sprites
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (input.upPressed) {
            direction = "up";
            y -= speed;
        }
        if (input.downPressed) {
            direction = "down";
            y += speed;
        }
        if (input.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if (input.rightPressed) {
            direction = "right";
            x += speed;
        }

        //Way of animating could come up with a smarter and better way of doing this
        if(input.isKeyPressed) {
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = switch (direction) {
            case "up" -> (spriteNumber == 1) ? up1 : up2;
            case "down" -> (spriteNumber == 1) ? down1 : down2;
            case "left" -> (spriteNumber == 1) ? left1 : left2;
            case "right" -> (spriteNumber == 1) ? right1 : right2;
            default -> null;
        };
        graphics2D.drawImage(image, x, y, gameWindow.getTileSize(), gameWindow.getTileSize(), null);
    }
}