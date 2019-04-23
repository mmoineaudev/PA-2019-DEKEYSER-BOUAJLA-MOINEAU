package m1_miage.controler;

import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.game_objects.IntelligentSprite;

import java.util.List;

public class PluginProvider {

    List<IntelligentSprite> plugins;

    /**
     * A modifier quand on aura résolu l'issue de création du menu de sélection
     * des plugins
     */
    public PluginProvider() {
    }

    /**
     * La méthode getPlugin renvoie une liste des options sélectionnées
     * sur le menu
     * @return des stripes affichables, qui implémentent {@link BasicSprite} ou {@link m1_miage.abstraction.game_objects.IntelligentSprite}
     */
    public List<BasicSprite> getPlugins(){
        throw new UnsupportedOperationException(this.getClass().getSimpleName()+ " getPlugins : not implmented yet");
    }

    public void setPlugins(List<IntelligentSprite> plugins) {
        this.plugins = plugins;
    }
}
