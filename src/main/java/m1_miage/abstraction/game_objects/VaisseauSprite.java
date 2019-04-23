package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.game_objects.Plugins.Weapon;
import m1_miage.abstraction.game_objects.Plugins.WeaponType;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static m1_miage.presenter.PNGTools.drawRotatedImage;

/**
 * Le futur objet de jeu, le vaisseau
 */
public class VaisseauSprite extends IntelligentSprite {
    private final int L = 20, l=50;
    private Image image = new Image(new FileInputStream("src/img/vaisseau.png"));
    private Weapon weaponByPlugin;

    //@Retention(value = RetentionPolicy.RUNTIME) //ahbon
    public VaisseauSprite(double x, double y, int weaponID) throws FileNotFoundException {
        super(x, y);
        try {
            weaponByPlugin = getWeaponByPlugin(weaponID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            weaponByPlugin = new Weapon(x,y,direction).basicWeapon();
        }
    }

    /**
     * Accède la classe weapon par l'annotaion {@link WeaponType} pour renvoyer le stripe qui convient
     * @param weaponID
     * @return une instance de Weapon, héritée de intelligentSprite
     */
    private Weapon getWeaponByPlugin(int weaponID) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class weapon = Class.forName("m1_miage.abstraction.game_objects.Plugins.Weapon");
        for(Method m : weapon.getMethods()) {
            WeaponType weaponType = (WeaponType) m.getAnnotation(WeaponType.class);
            if(weaponType.type() == weaponID)
                return (Weapon) m.invoke(x,y,direction);
        }
        return null;
    }

    @Override
    public void update(double time, GameBoard b) {
        speed+=1;
        avoidBorders(b);
        super.update(time,b);
    }

    /**
     * fait faire un demi tour au vaisseau s'il approche une bordure
     * @param b
     */
    private void avoidBorders(GameBoard b) {
        if(b.getWidth()-x<l) setDirection(Direction.WEST);
        if(b.getHeight()-l<y) setDirection(Direction.NORTH);
        if(x<l) setDirection(Direction.EAST);
        if(y<l) setDirection(Direction.SOUTH);
        //et 5% du temps il va ou il veut
        if(Math.random()>0.95) setDirection(Direction.random());
    }


    @Override
    public Shape getBoundingShape() {
        return new Rectangle(x,y,l,L);
    }

    @Override
    public void render(GraphicsContext gc) {
        if(image!=null){
            Paint save = gc.getFill();
            //gc.drawImage(image, x, y);
            drawRotatedImage(gc, image, getAngle(), x,y);
            drawLifesRemaining(gc, x, y);
            gc.setFill(save);

        }
    }

    /**
     * fait apparaitre les vies sur le gameboard
     */
    private void drawLifesRemaining(GraphicsContext gc, double x, double y) {
        if (image != null) {
            Paint save = gc.getFill();
            gc.setFill(Color.RED);
            for(int i = 0; i < lifes ; i ++){
                gc.strokeOval(x+i*7, y-7, 5, 5);
                gc.fillOval(x+i*7, y-7, 5, 5);
            }gc.setFill(save);
        }
    }


    /**
     * Comportement en cas de collision :
     *
     * A définir, pour l'instant perte d'une vie et respawn
     * OU LA MORT
     *
     * @param b
     * @param p
     */
    @Override
    public void handleCollision(GameBoard b, BasicSprite p) {
        super.handleCollision(b,p);//pour décrémenter le nombre de vies
        speed = 0;
        if(isDead()) image=null;
        else respawn(b);//try again
    }

    private void respawn(GameBoard b) {
        System.out.println("Respawn");
        x=b.getWidth()/2;
        y=b.getHeight()/2;
    }





}
