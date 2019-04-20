package m1_miage.abstraction.game_objects;

import org.junit.Before;
import org.junit.Test;
import tools.MakeEveryThingPublic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class VaisseauSpriteTest {

    private Object vaisseauSprite;
    @Before
    public void init(){
        Class c;
        Constructor[] classConstructor;
        try {
            c = MakeEveryThingPublic.getClassWithPublicAccess("m1_miage.abstraction.game_objects.VaisseauSprite");
            System.out.println("c = " + c);
            classConstructor = getClass().getDeclaredConstructors();
            System.out.println("classConstructor = " + classConstructor);
            vaisseauSprite = classConstructor[0].newInstance();
            System.out.println("vaisseauSprite = " + vaisseauSprite.getClass().getSimpleName());//we have a problem
        }catch(Exception e) {
            System.out.println("EXCEPTION :"+e.getMessage()+"\n"+e.getCause());

            e.printStackTrace();
        }finally {
            fail();
        }
    }

    @Test
    public void testForMakingEveryThingPublic(){
        System.out.println("vaisseauSprite = " + vaisseauSprite.getClass().getSimpleName());
        assertTrue(vaisseauSprite instanceof VaisseauSprite);
    }
}