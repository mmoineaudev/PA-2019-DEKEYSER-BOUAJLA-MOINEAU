package m1_miage.controler;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import m1_miage.abstraction.Sprite;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.presenter.GameBoard;

import java.util.Iterator;

/**
 * Boucle de jeu, qui doit appeller les actions des différents objets de jeu
 */
public class GameLoop extends AnimationTimer {
    private Canvas canvas;
    private GameBoard board;
    private GraphicsContext graphicsContext;

    private long lastUpdateNanoTime=System.nanoTime();


    //taken from https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835

    public GameLoop(GraphicsContext gc, GameBoard board, Canvas canvas) {
        this.graphicsContext = gc;
        this.board = board;
        this.canvas = canvas;
    }

    public void handle(long currentNanoTime) {

        double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;

        graphicsContext.setFill(Color.DARKBLUE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(Color.BLACK);

        board.animate(t, graphicsContext);
        lastUpdateNanoTime = currentNanoTime;
        if(Math.random()<0.01) addAsteroid();
    }

    public void addAsteroid(){
        board.addSprite(new AsteroidSprite(Math.random()*board.getWidth(),Math.random()*board.getHeight()));
    }

}