package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.canvas.Canvas;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class WeaponTest {
    private GameBoard gameBoard;
    private VaisseauSprite vaisseauSprite1;
    private VaisseauSprite vaisseauSprite2;
    private AsteroidSprite asteroidSprite;
    @Before
    public void setUp() throws Exception {
        this.gameBoard = new GameBoard(200, 200);
        vaisseauSprite1 = new VaisseauSprite(20,20, 1);
        vaisseauSprite2 = new VaisseauSprite(100,100, 2);
        asteroidSprite = new AsteroidSprite(100,20);
        vaisseauSprite1.setDirection(Direction.EAST);
        vaisseauSprite1.setDirection(Direction.NORTH);
    }

    @Test
    public void handleCollisionTest1(){
        vaisseauSprite1.setSpeed(0);
        gameBoard.addSprite(vaisseauSprite1);
        asteroidSprite.setSpeed(0);
        gameBoard.addSprite(asteroidSprite);
        try {
            vaisseauSprite1.shoot();
        } catch (Exception e) {
            fail();
        }
        gameBoard.animate(1, new Canvas(200,200).getGraphicsContext2D());
        System.out.println(gameBoard);
        assertTrue(gameBoard.getSpriteProvider().getLength()==1);
        assertTrue(asteroidSprite.isDead());
    }
    @Test
    public void handleCollisionTest2(){
        vaisseauSprite2.setSpeed(0);
        gameBoard.addSprite(vaisseauSprite2);
        asteroidSprite.setSpeed(0);
        gameBoard.addSprite(asteroidSprite);
        gameBoard.animate(1, new Canvas(200,200).getGraphicsContext2D());
        try {
            vaisseauSprite2.shoot();
        } catch (Exception e) {
            fail();
        }
        System.out.println(gameBoard);
        assertTrue(gameBoard.getSpriteProvider().getLength()==1);
        assertTrue(asteroidSprite.isDead());
    }
}