package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Le futur objet de jeu, le vaisseau
 */
public class VaisseauSprite extends IntelligentSprite {
    private final int L = 20, l=50;
    private Image image = new Image(new FileInputStream("src/img/vaisseau.png"));

    public VaisseauSprite(double x, double y) throws FileNotFoundException {
        super(x, y);
    }

    @Override
    public void update(double time, GameBoard b) {
        speed+=1;
        avoidBorders(b);
        super.update(time,b);
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
        if(image!=null){
            Paint save = gc.getFill();
            //gc.drawImage(image, x, y);
            drawRotatedImage(gc, image, getAngle(), x,y);
            drawLifesRemaining(gc, x, y);
            gc.setFill(save);

        }
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
     * Permet la rotation d'une image fonction de la direction du sprite qu'elle représente
     * @return un angle en degré
     */
    private double getAngle() {
        return direction.getValue()*90;
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
    }

    private void respawn(GameBoard b) {
        System.out.println("Respawn");
        x=b.getWidth()/2;
        y=b.getHeight()/2;
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
