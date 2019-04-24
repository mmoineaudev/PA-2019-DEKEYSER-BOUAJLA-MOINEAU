package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.image.Image;
import m1_miage.abstraction.game_objects.navigation.Direction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BasicWeapon extends Weapon{
    protected Image image = new Image(new FileInputStream("src/img/basicWeapon.png"));
    public BasicWeapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y, direction);
        System.out.println("creating "+this.getClass().getSimpleName()+" instance");

    }
}
