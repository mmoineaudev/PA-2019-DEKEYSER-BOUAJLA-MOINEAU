package m1_miage.abstraction.game_objects;

import org.junit.Before;
import org.junit.Test;
import tools.MakeEveryThingPublic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;

public class VaisseauSpriteTest {

    private Object vaisseauSprite;
    @Before
    public void init(){
        Class c;
        Constructor classConstructor;
        try {
            c = MakeEveryThingPublic.getClassWithPublicAccess("m1_miage.abstraction.game_objects.VaisseauSprite");
            classConstructor = getClass().getDeclaredConstructors()[0];
            vaisseauSprite = classConstructor.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }

    @Test
    public void testForMakingEveryThingPublic(){
        System.out.println("vaisseauSprite = " + vaisseauSprite);

        assertTrue(vaisseauSprite instanceof VaisseauSprite);
    }
}