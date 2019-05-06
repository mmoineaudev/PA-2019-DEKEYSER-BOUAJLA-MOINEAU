package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.canvas.Canvas;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.controler.GameBoard;
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
    public void handleCollisionTest(){
        vaisseauSprite1.setSpeed(0);
        gameBoard.addSprite(vaisseauSprite1);
        asteroidSprite.setSpeed(0);
        gameBoard.addSprite(asteroidSprite);
        try {
            vaisseauSprite1.setDirection(Direction.EAST);
            System.out.println("Shoot");
            vaisseauSprite1.shoot();
        } catch (Exception e) {
            fail();
        }
        for(int i=0; i<10;i++) gameBoard.animate(0.01, new Canvas(200,200).getGraphicsContext2D());
        assertTrue(asteroidSprite.isDead());
    }

}