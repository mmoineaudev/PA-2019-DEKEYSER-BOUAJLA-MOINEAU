package m1_miage.abstraction.game_objects.Plugins;

import javafx.scene.image.Image;
import m1_miage.abstraction.game_objects.navigation.Direction;
import m1_miage.controler.GameBoard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdvancedWeapon extends Weapon {
    @Override
    public void update(double time, GameBoard b) {
        super.update(time, b);
        speed = 200;//this one go fast
    }

    public AdvancedWeapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y, direction);
        image = new Image(new FileInputStream("src/img/advancedWeapon.png"));
        System.out.println("creating "+this.getClass().getSimpleName()+" instance");
    }
    public String getSound() {
        return this.id+":boom";
    }

}
