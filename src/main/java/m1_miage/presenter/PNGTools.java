package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;

/**
 * Contient des méthodes utiles pour l'affichage des images
 */
public class PNGTools {
    public static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }
    public static void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public static Paint getColor() {
        switch ((int) (Math.random()*5.)){
            case 0 : return Color.DARKKHAKI;
            case 1 : return Color.DARKORANGE;
            case 2 : return Color.DARKGRAY;
            case 3 : return Color.DARKSLATEBLUE;
            default: return Color.DARKOLIVEGREEN;
        }
    }

}
