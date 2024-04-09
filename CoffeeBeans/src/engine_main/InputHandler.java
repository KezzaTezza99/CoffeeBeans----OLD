package engine_main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A simple class that is used to get the users input and respond to certain keycodes to provide functionality
 * i.e., if W is pressed the character should move upwards on the Y axis etc.
 */
public class InputHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed , isKeyPressed;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        isKeyPressed = true;

        //Handling key presses
        if(keyCode == KeyEvent.VK_W) {
            upPressed = true;
        }

        if(keyCode == KeyEvent.VK_S) {
            downPressed = true;
        }

        if(keyCode == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if(keyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        isKeyPressed = false;

        //Handling key release
        if(keyCode == KeyEvent.VK_W) {
            upPressed = false;
        }

        if(keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }

        if(keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if(keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}