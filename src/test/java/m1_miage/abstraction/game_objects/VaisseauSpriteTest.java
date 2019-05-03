package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import m1_miage.abstraction.game_objects.Plugins.BasicWeapon;
import m1_miage.abstraction.game_objects.Plugins.Weapon;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import static m1_miage.abstraction.game_objects.navigation.Direction.NORTH;
import static org.junit.Assert.*;

public class VaisseauSpriteTest {

    private VaisseauSprite instance = null;
    private GameBoard gameBoard = null;

    @Before
    public void init() {
        System.out.println("* init()");
        try {
            this.instance = new VaisseauSprite(50,50, 1);
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            fail();
        }
        this.gameBoard = new GameBoard(100, 100);
    }

    @Test
    public void getBoundingShape() {
        System.out.println("* getBoundingShape()");
        assertTrue(instance.getBoundingShape()!=null);
    }

    @Test
    public void handleCollision() {
        System.out.println("* handleCollision()");

        try {
            Weapon weapon = new BasicWeapon(50,50, NORTH);
            gameBoard.addSprite(weapon);
            gameBoard.addSprite(instance);
            GraphicsContext gc =new Canvas(100,100).getGraphicsContext2D();
            gameBoard.animate(0.1, gc);
            System.out.println("handleCollision instance.lifes = " + instance.getLifes());
            assertTrue(instance.getLifes()==4);//sometimes fails :(
            assertTrue(weapon.isDead());
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            fail();
        }
    }

    @Test
    public void getWeaponsByPlugin() {
        System.out.println("* getWeaponsByPlugin()");

        try {
            instance.shoot();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            fail();
        }
        assertTrue(instance.getWeaponsByPlugin().size()==1);//pour l'instant on a qu'une arme
        assertTrue(new ArrayList<Weapon>((Collection<? extends Weapon>) instance.getWeaponsByPlugin()).get(0)instanceof BasicWeapon);//on init a weaponId=1
        try {
            instance=new VaisseauSprite(25,25, 2);
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            fail();
        }
    }

    @Test
    public void updateShoot() {
        System.out.println("* updateShoot()");


        instance.setDirection(NORTH);
        gameBoard.addSprite(instance);
        gameBoard.animate(0.001, new Canvas(100,100).getGraphicsContext2D());
        gameBoard.addSprite(new AsteroidSprite(50, 0));
        gameBoard.addSprite(new AsteroidSprite(20, 40));
        gameBoard.addSprite(new AsteroidSprite(70, 70));

        System.out.println("gameBoard.spriteProvider.getLength() = " + gameBoard.getSpriteProvider().getLength());

        gameBoard.getSpriteProvider().iterator().forEachRemaining(   intelligentSprite -> System.out.print("\t"+intelligentSprite+";\n"));
        assertTrue(gameBoard.getSpriteProvider().getLength()==4);
        
        gameBoard.animate(0.001, new Canvas(100,100).getGraphicsContext2D());

        System.out.println("gameBoard.spriteProvider.getLength() = " + gameBoard.getSpriteProvider().getLength());
        gameBoard.getSpriteProvider().iterator().forEachRemaining(   intelligentSprite -> System.out.print("\t"+intelligentSprite+";\n"));
        assertTrue(gameBoard.getSpriteProvider().getLength()==3);
    }

    @Test
    public void getBoundingShape1() {
    }

    @Test
    public void render() {
    }

    @Test
    public void handleCollision1() {
    }

    @Test
    public void getWeaponsByPlugin1() {
    }
}