package m1_miage.presenter;

import m1_miage.abstraction.SpriteProvider;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.Score;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

public class GameBoardTest extends GameBoard {

    private GameBoard  boardTest = new GameBoard(200,200);
    private IntelligentSprite intelligentSprite = new IntelligentSprite(20,20);
    private IntelligentSprite intelligentSprite2 = new IntelligentSprite(30,30);

    public GameBoardTest() {
        super(200, 100  );
    }

    @Test
    public void testAddSprite() {
        boardTest.addSprite(intelligentSprite);
        Iterator<IntelligentSprite> iterator = boardTest.spriteIterator();
        while (iterator.hasNext())
            assertTrue(iterator.next() == intelligentSprite);
    }

    @Test
    public void testSpriteIterator() {
        boardTest.addSprite(intelligentSprite);
        boardTest.addSprite(intelligentSprite2);

        Integer nbSprite = 2;
        Iterator<IntelligentSprite> iterator = boardTest.spriteIterator();
        SpriteProvider[] spriteProviders = new SpriteProvider[2];
        while (iterator.hasNext()){
           assertTrue(iterator.next() == intelligentSprite);
           assertTrue(iterator.next() == intelligentSprite2);
        }
        assertTrue(nbSprite == spriteProviders.length);
    }


    @Test
    public void testFacesAnEnemy() {
        VaisseauSprite vaisseauSprite = null;
        try {
            vaisseauSprite = new VaisseauSprite(10,15,2);
            boardTest.facesAnEnemy(vaisseauSprite);
            Iterator<IntelligentSprite> iterator = boardTest.spriteIterator();
            while (iterator.hasNext()){
                assertTrue(iterator.next() == vaisseauSprite);
                assertTrue(vaisseauSprite.getDirection() == Direction.NORTH);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}