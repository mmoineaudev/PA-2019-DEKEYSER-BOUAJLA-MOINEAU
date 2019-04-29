package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.game_objects.Plugins.Weapon;
import m1_miage.abstraction.game_objects.Plugins.WeaponType;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static m1_miage.abstraction.game_objects.navigation.Direction.*;
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
        //System.out.println("getWeaponByPlugin : "+((weapon==null)?"weapon class is null": weapon.getName()));
        for(Method m : weapon.getMethods()) {
          //  System.out.println("m = " + m.getName());
            WeaponType weaponType = (WeaponType) m.getAnnotation(WeaponType.class);

            if(weaponType!=null) {

                System.out.println("weaponType.type() = " + weaponType.type());//returns null

                if (weaponType.type() == weaponID) {
                    System.out.println("weapon method found " + m.getName());

                    return (Weapon) m.invoke(new Weapon(
                            getXForWeapon(direction),
                            getYForWeapon(direction),
                            direction));//invoke needs a reference to a compatible objet for 1st param
                }
            }

        }
        throw new Exception("getWeaponByPlugin : weapon method not found");
    }


    private double getXForWeapon(Direction direction) {
        if(direction==EAST){
            return x+l;
        }
        else if(direction==WEST){
            return x-l;
        }
        else return x;
    }

    private double getYForWeapon(Direction direction) {
        if(direction==NORTH){
            return y-l;
        }
        else if(direction==SOUTH){
            return y+l;
        }
        else return y;
    }

    @Override
    public void update(double time, GameBoard b) {
        if(speed<120) speed+=1;//on peut accélérer mais pas trop quand même
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

    /**
     * ne peut tirer qu'une fois par seconde
     */
    private long timestamp = 0;
    private void shoot() throws Exception {
        if(System.currentTimeMillis()-timestamp>1000 && !isDead())
        {   timestamp=System.currentTimeMillis();
            weaponsByPlugin.add(getWeaponByPlugin(weaponID));
        }
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
        if(b.getHeight()-l<y) setDirection(NORTH);
        if(x<l) setDirection(EAST);
        if(y<l) setDirection(Direction.SOUTH);
        //et 1% du temps il va ou il veut
        if(Math.random()>0.99) setDirection(Direction.random());
    }


    @Override
    public Shape getBoundingShape() {
        if(isDead())return null;
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
        if(!isDead())
            for(Weapon weaponByPlugin : weaponsByPlugin)
                if(weaponByPlugin!=null)
                    weaponByPlugin.render(gc);
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
    public void handleCollision(GameBoard b, IntelligentSprite p) {
        if(isDead()) image=null;
        else {
            super.handleCollision(b, p);//pour décrémenter le nombre de vies
            handleCollisionWithWeapon(b, p);
            speed = 0;
            respawn(b);//try again
        }
    }

    /**
     * shoot them all
     * @param b
     * @param p
     */
    private void handleCollisionWithWeapon(GameBoard b, IntelligentSprite p) {
        for(Weapon e : weaponsByPlugin){
            e.handleCollision(b,p);
        }

    }

    private void respawn(GameBoard b) {
        System.out.println("Respawn");
        x=b.getWidth()/2;
        y=b.getHeight()/2;
    }

    public Collection<? extends IntelligentSprite> getWeaponsByPlugin() {
        return this.weaponsByPlugin;
    }
}
