package MainPackage;

import javax.swing.*;

public class Main {
    public static JFrame window;



    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //the game closes when exited.
        window.setResizable(false); // the game is not resizable.
        window.setTitle("Deck's Quest"); // the title
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel(); // creates new game panel
        window.add(gamePanel);
        window.pack(); // causes the window to be sized to fit the preferred size and layouts of the game panel.

        window.setLocationRelativeTo(null); // the game displays in the center of the screen.
        window.setVisible(true); // the game is visible.
        gamePanel.setupGame();
        gamePanel.startGameThread(); // starts the game's thread.





    }
}
