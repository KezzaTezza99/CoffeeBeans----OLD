package engine_main;
import javax.swing.*;

public class EntryPoint {
    public static void main(String[] args) {
        //Creating a window that will hold the Game Window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("CoffeeBeans");

        //Adding the game window to the JFrame
        GameWindow gameWindow = new GameWindow(16, 3, 16, 12);
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