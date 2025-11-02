package Main;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		// Create the main game window
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Exit program when window is closed
		window.setResizable(false);// Prevent resizing 	
		window.setTitle("Room 404");// Set the window title
		
		// Create a game panel (the main screen where game runs)
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);// Add game panel to the window
		
		window.pack();// Adjust window size to fit the game panel
		
		window.setLocationRelativeTo(null);// Center window on screen
		window.setVisible(true); // Make the window visible
		
		// Start the main game loop
		gamePanel.startGameThread();
	}

}
