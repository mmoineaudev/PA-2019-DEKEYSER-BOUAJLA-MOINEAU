package m1_miage.controler;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.presenter.PNGTools;

/**
 * Boucle de jeu, qui doit appeller les actions des différents objets de jeu
 */
public class GameLoop extends AnimationTimer {
    private Canvas canvas;
    private GameBoard board;
    private GraphicsContext graphicsContext;

    private long lastUpdateNanoTime=System.nanoTime();
    private static double chanceForAnAsteroidToAppear = 0.001;


    //taken from https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835

    public GameLoop(GraphicsContext gc, GameBoard board, Canvas canvas) {
        this.graphicsContext = gc;
        this.board = board;
        this.canvas = canvas;
    }

    public static double getAsteroidPercentRisk() {
        return chanceForAnAsteroidToAppear*100;
    }

    public void handle(long currentNanoTime) {

        double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;

        drawSky();
        graphicsContext.setFill(Color.BLACK);
        if(board.ASingleOneIsLeft() || board.getSpriteProvider().getLength()<=5) chanceForAnAsteroidToAppear+=chanceForAnAsteroidToAppear/1000;
        board.animate(t, graphicsContext);
        lastUpdateNanoTime = currentNanoTime;
        if(Math.random()<chanceForAnAsteroidToAppear) addAsteroid();
    }

    private void drawSky() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for(int i = (int) (Math.random()*100); i>0; i--){
            double size = Math.random()*5;
            double x = Math.random()*board.getWidth();
            double y = Math.random()*board.getHeight();
            graphicsContext.setFill(PNGTools.getColor());
            graphicsContext.fillOval(x, y, size, size);
        }

    }

    public void addAsteroid(){
        int x = (int) (Math.random()*board.getWidth());
        int y = (int) (Math.random()*board.getHeight());
        if(!board.getSpriteProvider().containsASpriteAt(x,y))
            board.addSprite(new AsteroidSprite(x,y));
    }

}