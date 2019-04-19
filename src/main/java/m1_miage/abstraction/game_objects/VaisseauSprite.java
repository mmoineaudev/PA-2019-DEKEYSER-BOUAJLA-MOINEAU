package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.Sprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

/**
 * Le futur objet de jeu, le vaisseau
 */
public class VaisseauSprite extends IntelligentSprite {
    private final int L = 25, l=15;
    public VaisseauSprite(double x, double y) {
        super(x, y);
    }

    @Override
    public void update(double time, GameBoard b) {
        speed+=1;
        this.direction = Direction.values()[(int) (Math.random()*Direction.values().length)];
    }

    @Override
    public Shape getBoundingShape() {
        return new Rectangle(x,y,l,L);
    }

    @Override
    public void render(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(Color.RED);
        gc.fillRect(x,y,l,L);
        gc.setFill(save);
    }

    @Override
    public void handleCollision(GameBoard b, Sprite p) {
        System.out.println(" Le vaisseau a explosé :'( ");
    }
}
