package engine_main;
import javax.swing.*;

public class EntryPoint {
    public static void main(String[] args) {
        /*
          TODO: Maybe have a starting window that asks the user what params they want for a game window
          these params will then be used to instantiate a game window and set up the correct size arrays for tiles and
          types of tiles.

          Could go a step further and have the starting window ask for resource file paths and copy all of the relevant data
          to the necessary classes i.e., tiles, game objects, entities etc., if none is provided can just use the default resources provided by the engine
         */

        //TODO: Add the concept of maps / game worlds and a camera by default, could provide a handy starting map with tiles and sprites?
        // this would be an addition to the information mentioned above and could be a good concept to add in the next iteration of development

        //Creating a window that will hold the Game Window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("CoffeeBeans");

        //Adding the game window to the JFrame
        //Here we can specify the tileSize, scale, aspect ratio etc. to match specific games you want to create
        //GameWindow gameWindow = new GameWindow(16, 3, 16, 12, 16, 12);
        GameWindow gameWindow = new GameWindow(16, 3, 16, 12, 18, 14);
        window.add(gameWindow);

        //Making the window the size of the desired game window
        window.pack();

        //Setting the window to the centre of the users screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //Starting the game loop
        gameWindow.startGameThread();
    }
}