package m1_miage.controler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import m1_miage.presenter.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLoopTest {

    private GameLoop gameLoop = new GameLoop(null, null, null);

    @Test
    public void addAsteroid() {
        assertTrue(gameLoop.addAsteroid());
    }
}