package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

public class Weapon extends IntelligentSprite {
    private static final int default_speed = 15;
    public Weapon(double x, double y, Direction direction) {
        super(x, y);
        this.direction = direction;
        this.speed=default_speed;
        lifes = 1;
    }

    @Override
    public void update(double time, GameBoard b) {
        super.update(time, b);
    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }

    @Override
    public Shape getBoundingShape() {
        //TODO
    }

    @Override
    public void render(GraphicsContext gc) {
        //TODO
    }

    @Override
    public void handleCollision(GameBoard b, BasicSprite p) {
        super.handleCollision(b, p);
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

    @WeaponType(type=1)
    public BasicWeapon basicWeapon(){
        return new BasicWeapon();
    }

    @WeaponType(type=2)
    public BasicWeapon advancedWeapon(){
        return new AdvancedWeapon();
    }


}
