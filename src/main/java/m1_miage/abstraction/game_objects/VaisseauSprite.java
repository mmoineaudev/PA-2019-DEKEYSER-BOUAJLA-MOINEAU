package m1_miage.abstraction.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
        this.direction = Direction.values()[(int) (Math.random()*Direction.values().length)];
        super.update(time,b);
    }

    @Override
    public Shape getBoundingShape() {
        return new Rectangle(x,y,l,L);
    }

    @Override
    public void render(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.drawImage(image, x, y);
        gc.setFill(save);
    }

    @Override
    public void handleCollision(GameBoard b, Sprite p) {
        speed = 0;
        lifes--;
        if(lifes==0) System.out.println(" Le vaisseau a explosé :'( ");

    }
}
