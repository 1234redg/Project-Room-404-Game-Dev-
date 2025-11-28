package Main;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame("Room 404");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Full screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setPreferredSize(screen);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Show MainMenu first
        MainMenu mainMenu = new MainMenu(window);
        window.setContentPane(mainMenu);

        window.pack();
        window.setVisible(true);
    }
}
