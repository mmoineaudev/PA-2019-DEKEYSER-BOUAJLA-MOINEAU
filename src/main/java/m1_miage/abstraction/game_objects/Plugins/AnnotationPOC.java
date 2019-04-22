package m1_miage.abstraction.game_objects.Plugins;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * https://www.jmdoudoux.fr/java/dej/chap-annotations.htm#annotations-2
 *
 * POC basique d'une annotation
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationPOC {

    double speed() default 0.;
}
