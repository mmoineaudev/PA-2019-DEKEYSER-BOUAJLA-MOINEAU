package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.Sprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import static m1_miage.abstraction.game_objects.navigation.Direction.*;

/**
 * une sourcouche de sprite, mais qui se déplace seule
 */
public class IntelligentSprite extends Sprite {

    protected double speed;
    protected Direction direction = Direction.values()[(int) (Math.random()* values().length)];

    public IntelligentSprite(double x, double y) {
        super(x, y);
    }


    /**
     *
     * @param time
     */
    public void update(double time, GameBoard b){

        switch (direction){
            case NORTH:this.y+= speed*time;break;

            case EAST:this.x+= speed*time;break;

            case SOUTH:this.y-= speed*time;break;

            case WEST:this.x-= speed*time;break;
        }
    }

    @Override
    public Shape getBoundingShape() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void render(GraphicsContext gc) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void handleCollision(GameBoard b, Sprite p) {
        throw new UnsupportedOperationException("not implemented");
    }

    //getters / setters

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
