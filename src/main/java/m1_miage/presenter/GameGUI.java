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
import java.util.ArrayList;

/**
 * TODO refactor
 */
public class GameGUI extends Application {

	private int width = 1000;
	private int height = 600;

	private GameBoard board;
	private int nbVaisseaux;
	private ArrayList<Integer> weapons ;
    public GameGUI(int nbVaisseaux, ArrayList<Integer> weapons) {
    	this.nbVaisseaux = nbVaisseaux;
    	this.weapons = weapons;
    }

    @Override
	public void start(Stage stage) {
		initGame(stage);
	}

	private void initGame(Stage stage) {

		stage.setTitle("M1 MIAGE WAR");
		Group root = new Group();
		Scene theScene = new Scene(root);
		stage.setScene(theScene);
		Canvas canvas = new Canvas(width, height);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		stage.sizeToScene();

		board = new GameBoard(width, height);

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
			for(int i = 0; i<nbVaisseaux ; i++){
				int weapon = (Math.random()>0.5)?2:1 ;
				if(weapons.size()==nbVaisseaux) weapon = weapons.get(i);
				System.out.println("ajout vaisseau "+i+":\n"+(board.getWidth()/(nbVaisseaux+2))*i+","+(board.getHeight()/(nbVaisseaux+2))*i+","+weapon);
				board.addSprite(new VaisseauSprite((board.getWidth()/(nbVaisseaux+1))*(i+1),
						(board.getHeight()/(nbVaisseaux+1))*(i+1),
						weapon));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
