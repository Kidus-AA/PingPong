package Client;
import javafx.stage.Stage;

public class SceneManager {
	private static Game gameScene;
	private static Stage stage;
	
	public SceneManager(Stage stage) {
		gameScene = new Game();
		SceneManager.stage = stage;
		stage.setResizable(false);
		stage.setTitle("Ping Pong");
	}
	
	public static void setGameScene() {
		stage.setScene(gameScene.getScene());
	}
}
