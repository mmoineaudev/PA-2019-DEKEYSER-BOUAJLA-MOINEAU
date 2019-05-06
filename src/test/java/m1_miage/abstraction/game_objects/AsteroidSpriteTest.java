package m1_miage.abstraction.game_objects;

import javafx.scene.shape.Rectangle;
import m1_miage.controler.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsteroidSpriteTest extends AsteroidSprite {

    public AsteroidSpriteTest() {
        super(0, 0);
    }

    @Test
    public void testUpdate() {
        AsteroidSprite a = new AsteroidSprite(0,0);
        double oldx = a.getX() , oldy = a.getY() , oldSpeed = a.getSpeed();
        a.update(1, new GameBoard(100, 100));
        assertTrue(a.getSpeed()>0);//on avance
        assertTrue(a.getDirection().getValue()>=0);//on a une direction
        assertTrue(a.getX()!=oldx || a.getY()!=oldy);//on bouge
    }

    @Test
    public void testGetBoundingShape() {
        AsteroidSprite a = new AsteroidSprite(0,0);
        assertTrue(a.getBoundingShape() instanceof Rectangle);
    }

    @Test
    public void testHandleCollision() {
        GameBoard g = new GameBoard(100, 100);
        AsteroidSprite a = new AsteroidSprite(0,0);
        g.addSprite(a);

        AsteroidSprite b = new AsteroidSprite(60, 60);//ne devrait pas toucher l'autre
        g.addSprite(b);

        g.getSpriteProvider().checkForCollision(a, g);

        assertTrue(!a.isDead());
    }
    @Test
    public void testHandleCollision2() {
        GameBoard g = new GameBoard(100, 100);
        AsteroidSprite a = new AsteroidSprite(0,0);
        AsteroidSprite b = new AsteroidSprite(10, 10);//diametre de 10 au minimum
        g.addSprite(a);
        g.addSprite(b);
        g.getSpriteProvider().checkForCollision(a, g);
        assertTrue(a.isDead());
    }
}