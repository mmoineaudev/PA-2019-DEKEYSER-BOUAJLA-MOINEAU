package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import m1_miage.abstraction.Sprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Le futur objet de jeu, le vaisseau
 */
public class VaisseauSprite extends IntelligentSprite {
    private final int L = 50, l=50;
    private Image image = new Image(new FileInputStream("src/img/vaisseau.png"));
    private int lifes = 5;

    public VaisseauSprite(double x, double y) throws FileNotFoundException {
        super(x, y);
    }

    @Override
    public void update(double time, GameBoard b) {
        speed+=1;
        setDirection(Direction.values()[(int) (Math.random()*Direction.values().length)]);
        super.update(time,b);
    }



    @Override
    public Shape getBoundingShape() {
        return new Rectangle(x,y,l,L);
    }

    @Override
    public void render(GraphicsContext gc) {
        Paint save = gc.getFill();
        //gc.drawImage(image, x, y);
        drawRotatedImage(gc, image, getAngle(), x,y);
        gc.setFill(save);
    }
    //TODO test
    private double getAngle() {
        return direction.getValue()*90;
    }

    @Override
    public void handleCollision(GameBoard b, Sprite p) {
        speed = 0;
        lifes--;
        if(lifes==0) System.out.println(" Le vaisseau a explosé :'( ");

    }


    //-- TODO refactor vers gameboard
    /**
     * source : https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
     * Draws an image on a graphics context.
     *
     * The image is drawn at (tlpx, tlpy) rotated by angle pivoted around the point:
     *   (tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2)
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param angle the angle of rotation.
     * @param tlpx the top left x co-ordinate where the image will be plotted (in canvas co-ordinates).
     * @param tlpy the top left y co-ordinate where the image will be plotted (in canvas co-ordinates).
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }


}
