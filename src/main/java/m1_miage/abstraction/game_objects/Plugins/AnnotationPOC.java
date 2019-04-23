package m1_miage.abstraction.game_objects.Plugins;

import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.controler.PluginProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * https://www.jmdoudoux.fr/java/dej/chap-annotations.htm#annotations-2
 *
 * POC basique d'une annotation
 * on va essayer d'accedera une autre classe depuis l'annotation
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationPOC {
    public PluginProvider pluginProvider = new PluginProvider();

    public void feedPluginProvider(List<IntelligentSprite> plugins){
        pluginProvider.setPlugins(plugins);
    }
    public void getPluginProvider(){
        return pluginProvider;
    }

}
