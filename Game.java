package Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game {
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	private Scene scene;
	private Pong playerOnePong;
	private Pong playerTwoPong;	
	private int pongWidth = 10;
	private int pongHeight = 80;
	
	public Game() {
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		playerOnePong = new Pong(0, 210, pongWidth, pongHeight);
		playerOnePong.draw(g);
		
		playerTwoPong = new Pong(WIDTH - pongWidth, 210, pongWidth, pongHeight);
		playerTwoPong.draw(g);
		
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
				if(playerOnePong.getyCoord() - 10 >= 0) {
					playerOnePong.moveUp();
					playerOnePong.draw(g);
					playerTwoPong.moveUp();
					playerTwoPong.draw(g);
				}
			} else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
				if(playerOnePong.getyCoord() + playerOnePong.getHeight() + 10 <= HEIGHT) {
					playerOnePong.moveDown();
					playerOnePong.draw(g);
					playerTwoPong.moveDown();
					playerTwoPong.draw(g);
				}
			}
		});
		
		Ball ball = new Ball(g,400,250,10,10);
		Timeline renderer = new Timeline(new KeyFrame(Duration.millis(100), e -> ball.moveBall(WIDTH, HEIGHT, playerOnePong, playerTwoPong)));
		renderer.setCycleCount(Timeline.INDEFINITE);
		renderer.play();	
		
		BorderPane root = new BorderPane(canvas);
		scene = new Scene(root, WIDTH, HEIGHT);
	}
	
	public Scene getScene() {
		return scene;
	}
}
