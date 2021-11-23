package Client;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private int speed;
	
	public Ball(int xCoord, int yCoord, int width, int height) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.height = height;
		this.speed = 1;
	}
	
	public void draw(GraphicsContext g) {
		g.setFill(Color.RED);
		g.fillRect(xCoord, yCoord, width, height);
	}
	
	public void moveX() {
		
	}
	
	public void moveY() {
		
	}
	
	public void move
	
	
}
