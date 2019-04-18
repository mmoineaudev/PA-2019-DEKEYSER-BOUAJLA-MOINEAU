package M1_MIAGE.game.gui;


import M1_MIAGE.game.GameBoard;

import M1_MIAGE.sprite.RectangleSprite;
import M1_MIAGE.sprite.RoundSprite;
import M1_MIAGE.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Iterator;

public class GameGUI extends Application {
	private long lastUpdateNanoTime;
	private GameBoard board;

	@Override
	public void start(Stage stage) {
		initGame(stage);
	}

	private void initGame(Stage stage) {

		stage.setTitle("Demo de jeu");
		Group root = new Group();
		Scene theScene = new Scene(root);
		stage.setScene(theScene);
		Canvas canvas = new Canvas(512, 512);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		stage.sizeToScene();

		board = new GameBoard(512, 512);

		RectangleSprite r = new RectangleSprite(50, 10, 0, 100);
		board.addSprite(r);
		RoundSprite rs = new RoundSprite(50, 200, 0, -100, 20);
		board.addSprite(rs);

		//taken from https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
		lastUpdateNanoTime = System.nanoTime();
		//c'est notre boucle de jeu principale
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;

				gc.setFill(Color.AZURE);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.setFill(Color.BLACK);

				Iterator<Sprite> it = board.spriteIterator();
				while (it.hasNext()) {
					Sprite s = it.next();
					s.update(t, board);
					checkForCollision(s, board.spriteIterator());
					s.render(gc);
				}
				lastUpdateNanoTime = currentNanoTime;
			}
		}.start();
		stage.show();
	}

	private void checkForCollision(Sprite s, Iterator<Sprite> it) {
		while (it.hasNext()) {
			Sprite d = it.next();
			if (d != s) {
				if (s.getBoundingShape().getBoundsInParent().intersects(d.getBoundingShape().getBoundsInParent())) {
					System.out.println(" it's a crash !!!");
					s.handleCollision(board, d);
				}
			}
		}

	}




	public static void main(String[] args) {
		launch(args);
	}
}
