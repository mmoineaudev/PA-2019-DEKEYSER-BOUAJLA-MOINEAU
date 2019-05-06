package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.image.Image;
import m1_miage.abstraction.game_objects.navigation.Direction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BasicWeapon extends Weapon{

    public BasicWeapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y, direction);
        image = new Image(new FileInputStream("src/img/basicWeapon.png"));
        System.out.println("creating "+this.getClass().getSimpleName()+" instance");
    }
    public String getSound() {
        return this.id+":pan";
    }
}
