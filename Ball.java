package Client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Ball {
	public int xCoord;
	public int yCoord;
	private int width;
	private int height;
	private int speed;
	private int direction;
	private GraphicsContext g;
	
	public Ball(GraphicsContext g, int xCoord, int yCoord, int width, int height) {
		this.g = g;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.direction = 3;
	}
	
	private void ballDirectionChanger() {
		switch(direction) {
			case 1:
				xCoord = xCoord - speed * 10;
				yCoord = yCoord - speed * 10;
				break;
			case 2:
				xCoord = xCoord - speed * 10;
				yCoord = yCoord + speed * 10;
				break;
			case 3:
				xCoord = xCoord + speed * 10;
				yCoord = yCoord + speed * 10;
				break;
			case 4:
				xCoord = xCoord + speed * 10;
				yCoord = yCoord - speed * 10;
				break;
		}
	}
	
	private void draw() {
		g.setFill(Color.BLACK);
		int surroundingBlocks[][] = {{-10,-10}, {0,-10}, {10,-10},
									 {-10,0}, {10,0},
									 {-10,10}, {0,10}, {10,10}};
		for(int i = 0; i < surroundingBlocks.length; i++) {
			g.fillRect(xCoord + surroundingBlocks[i][0], yCoord + surroundingBlocks[i][1], width, height);
		}
		g.setFill(Color.RED);
		g.fillRect(xCoord, yCoord, width, height);
	}
	
	public void moveBall(int boardWidth, int boardHeight) {
		if(yCoord + speed * 10 > boardHeight && direction == 2) {
			direction = 1;
		} else if (yCoord + speed * 10 > boardHeight && direction == 3) {
			direction = 4;
		} else if (yCoord - speed * 10 < 0 && direction == 1) {
			direction = 2;
		} else if (yCoord - speed * 10 < 0 && direction == 4) {
			direction = 3;
		}		
		
		ballDirectionChanger();
		draw();
	}
	
//	public void render(int boardWidth, int boardHeight) {
//		Timeline renderer = new Timeline(new KeyFrame(Duration.millis(80), e -> moveBall(boardWidth, boardHeight)));
//		renderer.setCycleCount(Timeline.INDEFINITE);
//		renderer.play();
//	}
	
	public void setDirection(int direction) throws Exception {
		if(direction > 0 && direction < 5) {
			this.direction = direction;
		} else {
			throw new Exception("Direction must be a number from 1 to 4");
		}
	}
	
	
	
	
	
}
