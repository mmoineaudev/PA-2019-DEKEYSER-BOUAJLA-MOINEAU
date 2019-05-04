package m1_miage.presenter;

import org.junit.Test;

import m1_miage.abstraction.Sprite;
import m1_miage.abstraction.game_objects.IntelligentSprite;

import static org.junit.Assert.*;

import java.util.Iterator;

public class GameBoardTest extends GameBoard {

    private IntelligentSprite is1 = new IntelligentSprite(20, 20);
    private IntelligentSprite is2 = new IntelligentSprite(10, 10);

    public GameBoardTest() {
        super(200, 100  );
    }

    private GameBoard board = new GameBoard(200,200);

    @Test
    public void addSprite1() {
        board.addSprite(is1);
        Iterator<Sprite> iterator = board.spriteIterator();
        while (iterator.hasNext()) 
            assertTrue(iterator.next() == is1);
    }

    @Test
    public void spriteIterator1() {
        board.addSprite(is1);
        board.addSprite(is2);
        Integer nbSprite = 2;
        Iterator<Sprite> iterator = board.spriteIterator();
        Sprite[] sprites = new Sprite[2];
        Integer i = 0;
        while (iterator.hasNext()){
            sprites[i] = iterator.next();
            i++;
        } 
        assertTrue(nbSprite == sprites.length);
    }

    @Test
    public void animate1() {
    }

    @Test
    public void checkForCollision1() {
    }

    @Test
    public void removeSprite1() {
        board.addSprite(is1);
        //board.addSprite(is2);
        Integer nbSprite = 0;
        Iterator<Sprite> iterator = board.spriteIterator();
        Sprite[] sprites = new Sprite[2];
        Integer i = 0;
        board.removeSprite(is1);
        while (iterator.hasNext()){
            sprites[i] = iterator.next();
            i++;
        } 
        assertFalse(nbSprite == sprites.length);
    }
}