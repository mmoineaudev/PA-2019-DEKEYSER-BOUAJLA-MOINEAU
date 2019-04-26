package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.game_objects.AsteroidSprite;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static m1_miage.presenter.PNGTools.drawRotatedImage;

public class Weapon extends IntelligentSprite {
    protected Image image = null;
    private static final int default_speed = 50;

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
        return new Rectangle(1,1,1,1);
    }

    @Override
    public void render(GraphicsContext gc) {
        if(!isDead()){
            Paint save = gc.getFill();
            //gc.drawImage(image, x, y);
            drawRotatedImage(gc, image, getAngle(), x,y);
            gc.setFill(save);
        }
    }

    @Override
    public void handleCollision(GameBoard b, BasicSprite p) {
        if(p instanceof VaisseauSprite || p instanceof AsteroidSprite) {
            super.handleCollision(b, p);
            System.out.println("Touché : " + p.toString());
        }
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


}
