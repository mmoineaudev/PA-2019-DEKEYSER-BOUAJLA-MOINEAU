package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import static m1_miage.abstraction.game_objects.navigation.Direction.*;

/**
 * une sourcouche de sprite, mais qui se déplace seule
 */
public class IntelligentSprite extends BasicSprite {

    protected double speed;
    protected Direction direction = Direction.values()[(int) (Math.random()* values().length)];
    protected int lifes = 5;


    public IntelligentSprite(double x, double y) {
        super(x, y);
    }


    /**
     * Un objet dans l'espace qui se déplace a vitesse constante
     * @param time
     */
    public void update(double time, GameBoard b){

        switch (direction){
            case NORTH:this.y-= speed*time;break;

            case EAST:this.x+= speed*time;break;

            case SOUTH:this.y+= speed*time;break;

            case WEST:this.x-= speed*time;break;
        }
    }

    public boolean isDead(){
        return this.lifes==0;
    }

    @Override
    public Shape getBoundingShape() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void render(GraphicsContext gc) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * On perd une vie
     * @param b
     * @param p
     */
    @Override
    public void handleCollision(GameBoard b, BasicSprite p) {
        lifes--;
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


    @Override
    public String toString() {
        System.out.println();
        return "{ x = " + x + "y = " + y+"\n"+
                "Direction = " + direction.name()+"\n"+
                "speed = " + speed+ "}";
    }
}
