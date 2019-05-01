package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.util.Random;

import static m1_miage.abstraction.game_objects.navigation.Direction.*;

/**
 * Un objet de jeu affichable
 */
public class IntelligentSprite extends BasicSprite {
    protected String id = this.getClass().getSimpleName()+"#"+randomName();

    private String randomName() {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

        char[] text = new char[4];
        for (int i = 0; i < 4; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }

        return new String(text);
    }

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
     * fait apparaitre les vies sur le gameboard
     */
    public void drawLifesRemaining(GraphicsContext gc, double x, double y) {
        if (!isDead()) {
            Paint save = gc.getFill();
            gc.setFill(Color.RED);
            for(int i = 0; i < lifes ; i ++){
                gc.strokeOval(x+i*7, y-7, 5, 5);
                gc.fillOval(x+i*7, y-7, 5, 5);
            }gc.setFill(save);
        }
    }

    /**
     * On perd une vie
     * @param b
     * @param p
     */
    @Override
    public void handleCollision(GameBoard b, IntelligentSprite p) {
        lifes--;
        System.out.println(id + " touché par "+ p);
        if(isDead()) System.out.println(this + " is dead !");
    }

    /**
     * Permet la rotation d'une image fonction de la direction du sprite qu'elle représente
     * @return un angle en degré
     */
    protected double getAngle() {
        return direction.getValue()*90;
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
        return id + " pv: "+ lifes +" { \n\tx = " + (int)x + " \n\ty = " + (int)y+" ; "+ "\n\tDirection = " + direction.name()+" ; "+ "\n\tspeed = " + (int)speed+ "}";
    }


    public int getLifes() {
        return this.lifes;
    }
}


/**
 * cette classe doit etre surchargée pour crée un objet de jeu, qu'on pourra animer
 */
abstract class BasicSprite {
    protected double x;
    protected double y;

    public BasicSprite(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Cette méthode permet d'animer un objet de jeu
     * @param time
     * @param b
     */
    public abstract void update(double time, GameBoard b);

    public abstract Shape getBoundingShape() ;

    public abstract void render(GraphicsContext gc);

    public abstract void handleCollision(GameBoard b, IntelligentSprite p);

    /*
     *  getters/setters
     */

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
