package m1_miage.abstraction.game_objects.Plugins;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //on veut y avoir accès au runtime
public @interface WeaponType {
    int type();
}
