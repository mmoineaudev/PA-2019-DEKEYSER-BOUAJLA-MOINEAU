package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.presenter.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdvancedWeapon extends Weapon {
    @Override
    public void update(double time, GameBoard b) {
        super.update(time, b);
        speed = Math.random()*150;
    }

    @Override
    public Shape getBoundingShape() {
        if(isDead()) return null;
        return new Circle(30);
    }

    public AdvancedWeapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y, direction);
        image = new Image(new FileInputStream("src/img/advancedWeapon.png"));
        System.out.println("creating "+this.getClass().getSimpleName()+" instance");
    }
    public String getSound() {
        return "boom";
    }

    @Override
    /**
     * Permet de débugger les problèmes de collision
     * @param gc
     */
    protected void drawHitBox(GraphicsContext gc) {
        Paint save = gc.getFill();
        Paint saveStroke = gc.getStroke();
        gc.setStroke(Color.WHITE);
        gc.strokeOval(x,y, 30,30);
        gc.setFill(save);
        gc.setStroke(saveStroke);
    }

}
