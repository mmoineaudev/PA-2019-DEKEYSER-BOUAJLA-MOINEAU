package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.controler.GameBoard;

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
    private Score score = new Score();



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
        return this.lifes<=0;
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
    public void handleCollision(GameBoard b, IntelligentSprite p) {
        if(!this.isDead()) {
            lifes--;
            System.out.println(id + " touché par " + p);
        }
        //c'est pas un else
        if(isDead()) {
            p.getScore().addPoint();
            System.out.println(this + " is dead !");
        }
    }

    //getters / setters

    public Score getScore() {
        return score;
    }

    /**
     * Permet la rotation d'une image en fonction de la direction du sprite qu'elle représente
     * @return un angle en degré
     */
    protected double getAngle() {
        return direction.getValue()*90;
    }

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

    public int getLifes() {
        return this.lifes;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " pv: "+ lifes +" { \n\tx = " + (int)x + " \n\ty = " + (int)y+" ; "+ "\n\tDirection = " + direction.name()+" ; "+ "\n\tspeed = " + (int)speed+ "}";
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
