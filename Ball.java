package Client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Ball {
	private int xCoord;
	private int init_xCoord;
	private int yCoord;
	private int init_yCoord;
	private int width;
	private int height;
	private double speed;
	private int direction;
	private GraphicsContext g;
	
	private boolean prevBlockDrawn = false;
	private boolean pongHit = false;
	
	public Ball(GraphicsContext g, int xCoord, int yCoord, int width, int height) {
		this.g = g;
		this.xCoord = xCoord;
		this.init_xCoord = xCoord;
		this.yCoord = yCoord;
		this.init_yCoord = yCoord;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.direction = (int)(Math.random() * 4) + 1;		
		scoreBoard(0,0);
	}
	
	private void scoreBoard(int player1Score, int player2Score) {
		g.setFill(Color.BLACK);
		g.fillRect(320, 18, 175, 25);
		g.setFill(Color.YELLOW);
		g.setFont(new Font(30));
		g.fillText(player1Score + "", 320, 40);
		g.fillText(player2Score + "", 480, 40);
	}
	
	private void ballDirectionChanger() {
		switch(direction) {
			case 1:
				xCoord = (int)(xCoord - speed * 10);
				yCoord = (int)(yCoord - speed * 10);
				break;
			case 2:
				xCoord = (int)(xCoord - speed * 10);
				yCoord = (int)(yCoord + speed * 10);
				break;
			case 3:
				xCoord = (int)(xCoord + speed * 10);
				yCoord = (int)(yCoord + speed * 10);
				break;
			case 4:
				xCoord = (int)(xCoord + speed * 10);
				yCoord = (int)(yCoord - speed * 10);
				break;
		}
	}
	
	private void draw(Pong player1, Pong player2) {
		if(!prevBlockDrawn && !pongHit) {
			g.setFill(Color.BLACK);
			prevBlockDrawn = true;
		} else if(!prevBlockDrawn && pongHit) {
//			 && (xCoord + width <= player1.getWidth() || xCoord >= player2.getxCoord() + player2.getWidth())
			g.setFill(Color.YELLOW);
			prevBlockDrawn = true;
		} else if (prevBlockDrawn) {
			g.setFill(Color.RED);
			prevBlockDrawn = false;
		}
		g.fillRect(xCoord, yCoord, width, height);		
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
		
		if(xCoord - speed * 10 < 0 && direction == 1) {
			if(checkPongHit(player1)) {
				direction = 4;
				pongHit = true;
//				speed *= 1.1;
			} else {
				player2.incrementScore();
				scoreBoard(player1.getScore(),player2.getScore());
				resetBall();
			}			
		} else if (xCoord - speed * 10 < 0 && direction == 2) {
			if(checkPongHit(player1)) {
				direction = 3;
				pongHit = true;
//				speed *= 1.1;
			} else {
				player2.incrementScore();
				scoreBoard(player1.getScore(),player2.getScore());
				resetBall();
			}	
		} else if (xCoord + speed * 10 > boardWidth - player2.getWidth() && direction == 3) {
			if(checkPongHit(player2)) {
				direction = 2;
				pongHit = true;
//				speed *= 1.1;
			} else {
				player1.incrementScore();
				scoreBoard(player1.getScore(),player2.getScore());
				resetBall();
			}			
		} else if (xCoord + speed * 10 > boardWidth - player2.getWidth() && direction == 4) {
			if(checkPongHit(player2)) {
				direction = 1;
				pongHit = true;
//				speed *= 1.1;
			} else {
				player1.incrementScore();
				scoreBoard(player1.getScore(),player2.getScore());
				resetBall();
			}			
		}
		System.out.println(speed);
		draw(player1, player2);
		ballDirectionChanger();
		draw(player1, player2);
		pongHit = false;
	}
	
	public boolean checkPongHit(Pong player) {
		if((yCoord + height > player.getyCoord() && yCoord + height < player.getyCoord() + player.getHeight()) ||
		   (yCoord > player.getyCoord() && yCoord < player.getyCoord() + player.getHeight())) {
			return true;
		}
		return false;
	}
	
	public void resetBall() {
		g.setFill(Color.BLACK);
		g.fillRect(xCoord, yCoord, width, height);
		xCoord = init_xCoord;
		yCoord = init_yCoord;
		direction = (int)(Math.random() * 4) + 1;
		speed = 1;
	}	
}
