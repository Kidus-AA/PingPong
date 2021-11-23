package Client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Store extends Application {

	public void start(Stage stage) throws Exception {
		SceneManager sceneManager = new SceneManager(stage);
		SceneManager.setGameScene();
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
