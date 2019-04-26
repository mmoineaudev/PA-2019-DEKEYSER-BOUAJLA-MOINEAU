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
import java.util.ArrayList;
import java.util.List;

import static m1_miage.presenter.PNGTools.drawRotatedImage;

/**
 * Le futur objet de jeu, le vaisseau
 */
public class VaisseauSprite extends IntelligentSprite {
    private final int L = 20, l=50;
    private Image image = new Image(new FileInputStream("src/img/vaisseau.png"));
    private List<Weapon> weaponsByPlugin;
    private int weaponID;

    public VaisseauSprite(double x, double y, int weaponID) throws FileNotFoundException {
        super(x, y);
        this.weaponID = weaponID; // depuis la page de menu
        try {
            weaponsByPlugin= new ArrayList<Weapon>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Accède la classe weapon par l'annotation {@link WeaponType} pour renvoyer le stripe qui convient
     * @param weaponID : récupéré de l'écran menu (unimplemented yet)
     * @return une instance de Weapon, héritée de intelligentSprite
     */
    private Weapon getWeaponByPlugin(int weaponID) throws Exception {
        Class weapon = Class.forName("m1_miage.abstraction.game_objects.Plugins.Weapon");
        System.out.println("getWeaponByPlugin : "+((weapon==null)?"weapon class is null": weapon.getName()));
        for(Method m : weapon.getMethods()) {
            System.out.println("m = " + m.getName());
            WeaponType weaponType = (WeaponType) m.getAnnotation(WeaponType.class);

            if(weaponType!=null) {

                System.out.println("weaponType.type() = " + weaponType.type());//returns null

                if (weaponType.type() == weaponID) {
                    System.out.println("weapon method found " + m.getName());
                    return (Weapon) m.invoke(new Weapon(x, y, direction));//invoke needs a reference to a compatible objet for 1st param
                }
            }

        }
        throw new Exception("getWeaponByPlugin : weapon method not found");
    }

    @Override
    public void update(double time, GameBoard b) {
        speed+=1;
        avoidBorders(b);
        super.update(time,b);
        //update des plugins
        updatePlugins(time,b);
        if(b.facesAnEnemy(this)) {
            try {
                shoot();
            } catch (Exception e) {
                System.out.println("Broken weapon :'( ");
                e.printStackTrace();
            }
        }
    }

    private void shoot() throws Exception {
        weaponsByPlugin.add(getWeaponByPlugin(weaponID));
    }

    private void updatePlugins(double time, GameBoard b) {
        for(Weapon weaponByPlugin : weaponsByPlugin) weaponByPlugin.update(time,b);
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
        if(!isDead()){
            Paint save = gc.getFill();
            //gc.drawImage(image, x, y);
            drawRotatedImage(gc, image, getAngle(), x,y);
            drawLifesRemaining(gc, x, y);
            gc.setFill(save);
            //on affiche ci les plugins
            drawWeapons(gc);
        }

    }

    private void drawWeapons(GraphicsContext gc) {
        for(Weapon weaponByPlugin : weaponsByPlugin)
            if(weaponByPlugin!=null)
                weaponByPlugin.render(gc);
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
        handleCollisionWithWeapon(b,p);
    }

    /**
     * shoot them all
     * @param b
     * @param p
     */
    private void handleCollisionWithWeapon(GameBoard b, BasicSprite p) {
        for(Weapon e : weaponsByPlugin){
            e.handleCollision(b,p);
        }

    }

    private void respawn(GameBoard b) {
        System.out.println("Respawn");
        x=b.getWidth()/2;
        y=b.getHeight()/2;
    }

}
