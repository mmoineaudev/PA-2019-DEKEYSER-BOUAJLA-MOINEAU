package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.image.Image;
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
        if(speed>0 && speed<100)speed+=1;//celui là il fait des choses bizarres
        else speed=10;
    }

    @Override
    public Shape getBoundingShape() {
        return new Circle(20);
    }

    @Override
    public void handleCollision(GameBoard b, IntelligentSprite p) {
        super.handleCollision(b, p);
    }

    public AdvancedWeapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y, direction);
        image = new Image(new FileInputStream("src/img/advancedWeapon.png"));
        System.out.println("creating "+this.getClass().getSimpleName()+" instance");
    }
}
