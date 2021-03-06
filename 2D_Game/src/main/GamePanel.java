package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
// Work as Game Screen
public class GamePanel extends JPanel implements Runnable{
// Inherits JPanel Class ( subcomponent of JPanel)
// "implements Runnable" To run thread need implement Runnable
	final int originalTileSize = 16; // 16 x 16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48 x 48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	//FPS 
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	// Thread run the game until stop
	Thread gameThread; // Draw many pictures at a time
	public Player player = new Player(this, keyH); // this: gamepanel
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); // this game panel can "focused" to receive the key input
		
	}
	public void startGameThread() {
		
		gameThread = new Thread(this); // passing the GamePanel Class to the thread constructor
		gameThread.start();
		
	}
	@Override
	// Add unimplemented method ( Runnable)
	// Start the game thread 
	public void run() {
		double drawInterval = 1000000000 / FPS ; // 0.01666667s
		double nextDrawTime = System.nanoTime()+ drawInterval;
		while(gameThread != null) {
		
			// System.out.println("the game loop is running");
			/* Returns the current value of the running JVM's high-resolution time source, in nanoseconds
			 long currentTime = System.nanoTime() */
			// 1. UPDATE: update information such as character positions
			update();
			// 2. DRAW: draw the screen with the updated information
			repaint(); // call paintComponent method
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void update() {
		player.update();
	}
	// one of the standard method to draw thing on JPanel
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //
		/* Graphics2D class extends the Graphic class to provide  
		more sophisticated control over geometry, coordinate, 
		transformations, color management and text layout. */
		
		// g2 has a bit more function than g
		Graphics2D g2 = (Graphics2D) g;
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
}
