package Client;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pong {
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	
	public Pong(int xCoord, int yCoord, int width, int height) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.height = height;
	}

	public int getxCoord() {
		return xCoord;
	}
	
	public int getyCoord() {
		return yCoord;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void draw(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(xCoord, yCoord - 10, width, 10);
		g.setFill(Color.YELLOW);
		g.fillRect(xCoord, yCoord, width, height);
		g.setFill(Color.BLACK);
		g.fillRect(xCoord, yCoord + height, width, 10);
	}
	
	public void moveUp() {
		yCoord = yCoord - 10;
	}
	
	public void moveDown() {
		yCoord = yCoord + 10;
	}
	
}
