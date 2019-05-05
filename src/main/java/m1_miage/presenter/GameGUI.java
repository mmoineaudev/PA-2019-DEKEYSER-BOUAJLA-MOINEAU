package m1_miage.presenter;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.controler.GameLoop;
import m1_miage.presenter.GameBoard;


import java.io.FileNotFoundException;

/**
 * TODO refactor
 */
public class GameGUI extends Application {
	private GameBoard board;

	@Override
	public void start(Stage stage) {
		initGame(stage);
	}

	private void initGame(Stage stage) {

		stage.setTitle("M1 MIAGE WAR");
		Group root = new Group();
		Scene theScene = new Scene(root);
		stage.setScene(theScene);
		Canvas canvas = new Canvas(512, 512);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		stage.sizeToScene();

		board = new GameBoard(512, 512);

		/*RectangleSprite r = new RectangleSprite(50, 10);
		board.addSprite(r);
		RoundSprite rs = new RoundSprite(50, 200, 100);
		board.addSprite(rs);
		*/
		initBoard(board);

		//c'est notre boucle de jeu principale
		new GameLoop(gc, board, canvas).start();
		stage.show();


	}

	private void initBoard(GameBoard board) {
		System.out.println("initBoard");
		try {
			board.addSprite(new VaisseauSprite(50, 500, 2));
			board.addSprite(new VaisseauSprite(200, 200, 1));
			board.addSprite(new VaisseauSprite(250, 250, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
