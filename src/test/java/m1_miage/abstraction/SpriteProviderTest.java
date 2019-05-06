package m1_miage.abstraction;

import javafx.scene.canvas.Canvas;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.controler.GameBoard;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class SpriteProviderTest {

    private GameBoard instance;

    @Before
    public void init(){
        instance = new GameBoard(100, 100);
    }

    @Test
    public void add() {
        assertTrue(instance.getSpriteProvider().getLength()==0);
        try {
            instance.addSprite(new VaisseauSprite(0,0, 1));
        } catch (FileNotFoundException e) {
            fail();
        }
        assertTrue(instance.getSpriteProvider().getLength()==1);
        instance.addSprite(new AsteroidSprite(0,0));
        assertTrue(instance.getSpriteProvider().getLength()==2);

    }

    @Test
    public void removeTheDeadAndCheckForCollision() {
        //ce test n'est pas suffisant, d'ou le continue dans la boucle {@link SpriteProvider#animate}
        try {
            instance.addSprite(new VaisseauSprite(0,0, 1));
            instance.addSprite(new AsteroidSprite(0,0));

            assertTrue(instance.getSpriteProvider().getLength()==2);

            instance.animate(0.1, new Canvas(100,100).getGraphicsContext2D());

            assertTrue(instance.getSpriteProvider().getLength()<2);

        } catch (FileNotFoundException e) {
            fail();
        }

    }

    @Test
    public void removeLostSprites() {
        try {
            instance.addSprite(new VaisseauSprite(-1,0, 1));
            instance.addSprite(new AsteroidSprite(instance.getWidth()+1,instance.getHeight()+1));
            assertTrue(instance.getSpriteProvider().getLength()==2);
            instance.animate(0.1, new Canvas(100,100).getGraphicsContext2D());
            assertTrue(instance.getSpriteProvider().getLength()==0);
        } catch (FileNotFoundException e) {
            fail();
        }
    }


}