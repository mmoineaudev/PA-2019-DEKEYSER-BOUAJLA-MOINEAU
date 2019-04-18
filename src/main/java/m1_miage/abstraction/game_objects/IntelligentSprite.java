package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.Sprite;
import m1_miage.presenter.GameBoard;

/**
 * une sourcouche de sprite, mais qui se déplace seule
 */
public class IntelligentSprite extends Sprite {

    public IntelligentSprite(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
    }

    public void update(){
        /* Todo */
    }

    @Override
    public Shape getBoundingShape() {
        return new Rectangle(x,y,10,10);//todo adapter a la forme de l'objet
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void handleCollision(GameBoard b, Sprite p) {

    }
}
