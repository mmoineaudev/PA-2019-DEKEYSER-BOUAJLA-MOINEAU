package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.Sprite;
import m1_miage.presenter.GameBoard;

/**
 * Le futur objet de jeu : asteroid
 */
public class AsteroidSprite extends IntelligentSprite {
    public AsteroidSprite(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
    }

    @Override
    public Shape getBoundingShape() {
        return super.getBoundingShape();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void handleCollision(GameBoard b, Sprite p) {
        super.handleCollision(b,p);
    }
}
