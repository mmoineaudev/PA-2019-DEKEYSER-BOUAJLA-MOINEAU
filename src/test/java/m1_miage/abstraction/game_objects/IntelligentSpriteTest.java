package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import m1_miage.abstraction.Sprite;
import m1_miage.presenter.GameBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class IntelligentSpriteTest extends IntelligentSprite {

    private IntelligentSprite instance = new IntelligentSprite(0,0);
    private GameBoard g = new GameBoard(100,100);

    public IntelligentSpriteTest() {
        super(0., 0.);
    }

    @Test
    public void update1() {
        instance.setSpeed(0);
        assertTrue(this.direction.getValue()!=-1);//on a une direction
        double oldx = instance.getX(), oldy = instance.getY(), oldspeed = instance.speed;
        for(int i = 0; i<10; i++) {
            instance.update(0.1, g);
            //System.out.println("instance = " + instance);
        }
        assertFalse(oldx != instance.getX()||oldy != instance.getY());//on bouge pas
        assertFalse(oldspeed != instance.getSpeed());//on accélère pas

    }
    @Test
    public void update2() {
        instance.setSpeed(1);
        assertTrue(this.direction.getValue() != -1);//on a une direction
        double oldx = instance.getX(), oldy = instance.getY(), oldspeed = instance.speed;
        for (int i = 0; i < 10; i++) {
            instance.update(0.1, g);
            //System.out.println("instance = " + instance);
        }
        assertTrue(oldx != instance.getX() || oldy != instance.getY());//on bouge
        assertTrue(oldspeed == instance.getSpeed());//on accélère pas
    }
    @Test
    public void isDead1() {
        assertFalse(isDead());//on est pas morts a la création
        lifes=0;
        assertTrue(isDead());//mais quand on a plus de vie
        lifes=5;//on remet les vies a 5
    }

    @Test
    public void getBoundingShape1() {
        try{
            getBoundingShape();
        }catch(Exception e){
            assertTrue(e instanceof UnsupportedOperationException);//on doit spécifier le IntelligentSprite mais pas
            //l'utiliser directement
        }
    }

    @Test
    public void render1() {
        try{
            Canvas forTest = new Canvas();
            render(forTest.getGraphicsContext2D());
        }catch(Exception e){
            assertTrue(e instanceof UnsupportedOperationException);//on doit spécifier le IntelligentSprite mais pas
            //l'utiliser directement
        }
    }

    //pas utilisable dans l'état, cf getBoundingShape1
    @Test
    public void handleCollision1() {
        instance.setX(0);
        instance.setY(0);

        g.addSprite(instance);
        g.addSprite(new IntelligentSprite(20, 20));

        Iterator<Sprite> it = g.spriteIterator();
        while (it.hasNext()){
            Sprite s = it.next();
            try{
                g.checkForCollision(s);
                assertTrue(instance.lifes == 5);//pas de collision
            }catch(Exception e){
                assertTrue(e instanceof UnsupportedOperationException);//on doit spécifier le IntelligentSprite mais pas
                //l'utiliser directement
            }
        }
    }
    //pas utilisable dans l'état, cf getBoundingShape1
    @Test
    public void handleCollision2() {
        instance.setX(0);
        instance.setY(0);

        g.addSprite(instance);
        g.addSprite(new IntelligentSprite(0, 0));

        Iterator<Sprite> it = g.spriteIterator();
        while (it.hasNext()){
            Sprite s = it.next();
            try{
                g.checkForCollision(s);
                assertFalse(instance.lifes == 5);//pas de collision
            }catch(Exception e){
                assertTrue(e instanceof UnsupportedOperationException);//on doit spécifier le IntelligentSprite mais pas
                //l'utiliser directement
            }
         }
    }

}