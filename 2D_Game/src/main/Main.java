package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Click X buttion to exit
		window.setResizable(false); // False to resize the window
		window.setTitle("2D Adventure"); // Title
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); // Cause this Window to be sized to fit the prefferd size and layouts of its subcomponents(= GamePanel)
		
		window.setLocationRelativeTo(null); // The window will display at the center of the screen
		window.setVisible(true); // See the window
		
		gamePanel.startGameThread();
	}
}
