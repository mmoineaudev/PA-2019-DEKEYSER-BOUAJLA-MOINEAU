package m1_miage.abstraction;

import javafx.scene.canvas.Canvas;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.presenter.GameBoard;
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
        assertTrue(instance.spriteProvider.getLength()==0);
        try {
            instance.addSprite(new VaisseauSprite(0,0, 1));
        } catch (FileNotFoundException e) {
            fail();
        }
        assertTrue(instance.spriteProvider.getLength()==1);
        instance.addSprite(new AsteroidSprite(0,0));
        assertTrue(instance.spriteProvider.getLength()==2);

    }

    @Test
    public void removeTheDeadAndCheckForCollision() {
        try {
            instance.addSprite(new VaisseauSprite(0,0, 1));
            instance.addSprite(new AsteroidSprite(0,0));

            assertTrue(instance.spriteProvider.getLength()==2);

            instance.animate(0.1, new Canvas(100,100).getGraphicsContext2D());

            assertTrue(instance.spriteProvider.getLength()<2);

        } catch (FileNotFoundException e) {
            fail();
        }

    }

    @Test
    public void removeLostSprites() {
        try {
            instance.addSprite(new VaisseauSprite(-1,0, 1));
            instance.addSprite(new AsteroidSprite(instance.getWidth()+1,instance.getHeight()+1));
            assertTrue(instance.spriteProvider.getLength()==2);
            instance.animate(0.1, new Canvas(100,100).getGraphicsContext2D());
            assertTrue(instance.spriteProvider.getLength()==0);
        } catch (FileNotFoundException e) {
            fail();
        }
    }


}