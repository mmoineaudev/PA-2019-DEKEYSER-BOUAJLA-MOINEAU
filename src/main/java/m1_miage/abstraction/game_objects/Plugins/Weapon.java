package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileNotFoundException;

import static m1_miage.presenter.PNGTools.drawRotatedImage;

public class Weapon extends IntelligentSprite {
    protected Image image = null;
    private static final int default_speed = 50;
    private VaisseauSprite owner;

    public Weapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y);
        this.direction = direction;
        this.speed=default_speed;
        lifes = 1;
    }

    @Override
    public void update(double time, GameBoard b) {
        super.update(time, b);//avance en ligne droite
    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }

    @Override
    public Shape getBoundingShape() {
        if(isDead()) return null;
        else return new Circle(x, y, 10);
    }
    /**
     * Permet de débugger les problèmes de collision
     * @param gc
     */
    protected void drawHitBox(GraphicsContext gc) {
        Paint save = gc.getFill();
        Paint saveStroke = gc.getStroke();
        gc.setStroke(Color.WHITE);
        gc.strokeOval(x,y, 10,10);
        gc.setFill(save);
        gc.setStroke(saveStroke);
    }

    /**
     * S'il n'a pas été détruit on affiche le projectile,
     * sinon non et il sera retiré de la liste par le {@link m1_miage.abstraction.SpriteProvider}
     * @param gc
     */
    @Override
    public void render(GraphicsContext gc) {
        if(!isDead()){
            Paint save = gc.getFill();
            //gc.drawImage(image, x, y);
            drawRotatedImage(gc, image, getAngle(), x,y);
            gc.setFill(save);
        }
        drawHitBox(gc); //for debug
    }

    /**
     * On va 'dupliquer' le handle collision pour les weapon afin qu'il augmente les scores des vaisseaux
     * @param b
     * @param p
     */
    @Override
    public void handleCollision(GameBoard b, IntelligentSprite p) {
        if(!isDead() && !p.isDead()){
            lifes--;
            p.handleCollision(b,this);
            System.out.println(p + " received a shot from "+ owner);
            owner.getScore().addPoint();
            System.out.println(this + " is dead !");
        }
    }

    public void setOwner(VaisseauSprite vaisseauSprite){
        this.owner = vaisseauSprite;
    }

    @Override
    public double getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(double speed) {
        super.setSpeed(speed);
    }

    @Override
    public Direction getDirection() {
        return super.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //méthodes qu'on cherche a appeller par introspection
    @WeaponType(type=1)
    public BasicWeapon basicWeapon() throws FileNotFoundException {
        return new BasicWeapon(x, y, direction);
    }

    @WeaponType(type=2)
    public AdvancedWeapon advancedWeapon() throws FileNotFoundException {
        return new AdvancedWeapon(x,y,direction);
    }


    public String getSound() {
        return "Peeeewww";
    }
}
