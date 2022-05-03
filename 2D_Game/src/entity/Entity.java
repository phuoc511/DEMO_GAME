package entity;

import java.awt.image.BufferedImage;

// This stores variables that will be used in player, monsters, and NPC classes
public class Entity {
	public int worldX, worldY;
	public int speed;
	
	// It describe an image with an accessible buffer of image data( We use this to store our image files)
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction; 
	
	public int spriteCounter = 0;
	public int spriteNum = 1; 
}
