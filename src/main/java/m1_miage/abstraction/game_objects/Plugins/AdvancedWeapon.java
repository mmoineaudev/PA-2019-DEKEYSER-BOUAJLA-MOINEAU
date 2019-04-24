package m1_miage.abstraction.game_objects.Plugins;

import m1_miage.abstraction.game_objects.navigation.Direction;

import java.io.FileNotFoundException;

public class AdvancedWeapon extends Weapon {
    public AdvancedWeapon(double x, double y, Direction direction) throws FileNotFoundException {
        super(x, y, direction);
    }
}
