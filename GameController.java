package Code;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController extends Application{
	
	private Group root;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		root = new Group();
		
		Scene scene = new Scene(root, 600,600);
		Menu mainMenu = new Menu(600,600);
		mainMenu.initializeMainMenu(stage);
		root.getChildren().addAll(mainMenu);
		
		stage.setScene(scene);
		stage.show();
	}
}
