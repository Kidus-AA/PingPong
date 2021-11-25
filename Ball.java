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
	
	private boolean prevBlockDrawn = false;
	private boolean pongHit = false;
	
	public Ball(GraphicsContext g, int xCoord, int yCoord, int width, int height) {
		this.g = g;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.direction = 2;
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
//		if(!pongHit) {
			if(!prevBlockDrawn && !pongHit) {
				g.setFill(Color.BLACK);
				prevBlockDrawn = true;
			} else if(!prevBlockDrawn && pongHit) {
				g.setFill(Color.YELLOW);
				prevBlockDrawn = true;
			} else if (prevBlockDrawn) {
				g.setFill(Color.RED);
				prevBlockDrawn = false;
			}
			g.fillRect(xCoord, yCoord, width, height);
//		}
		
	}
	
	public void moveBall(int boardWidth, int boardHeight, Pong player1, Pong player2) {
		if(yCoord + speed * 10 > boardHeight && direction == 2) {
			direction = 1;
		} else if (yCoord + speed * 10 > boardHeight && direction == 3) {
			direction = 4;
		} else if (yCoord - speed * 10 < 0 && direction == 1) {
			direction = 2;
		} else if (yCoord - speed * 10 < 0 && direction == 4) {
			direction = 3;
		}		
		
		if(xCoord - speed * 10 < 0 && direction == 1 && checkPongHit(player1)) {
			direction = 4;
			pongHit = true;
		} else if (xCoord - speed * 10 < 0 && direction == 2 && checkPongHit(player1)) {
			direction = 3;
			pongHit = true;
		} else if (xCoord + speed * 10 > boardWidth - player2.getWidth() && direction == 3 && checkPongHit(player2)) {
			direction = 2;
			pongHit = true;
		} else if (xCoord + speed * 10 > boardWidth - player2.getWidth() && direction == 4 && checkPongHit(player2)) {
			direction = 1;
			pongHit = true;
		}
		
		draw();
		ballDirectionChanger();
		draw();
		pongHit = false;
	}
	
	public boolean checkPongHit(Pong player) {
		if((yCoord + height > player.getyCoord() && yCoord + height < player.getyCoord() + player.getHeight()) ||
		   (yCoord > player.getyCoord() && yCoord < player.getyCoord() + player.getHeight())) {
			return true;
		}
		return false;
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
