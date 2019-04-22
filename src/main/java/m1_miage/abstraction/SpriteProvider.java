package m1_miage.abstraction;

import m1_miage.abstraction.game_objects.IntelligentSprite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A la responsabilité des traitements qui touchent a la liste de spitres
 */
public class SpriteProvider {
    private  ArrayList<BasicSprite> list;

    public SpriteProvider() {
        this.list = new ArrayList<>();
    }

    /**
     * Ajoute un objet de jeu à la liste de sprites
     * @param p
     */
    public void add(BasicSprite p) {
        list.add(p);
    }

    /**
     * @return un iterator sur la liste de sprites
     */
    public Iterator<BasicSprite> iterator() {
        return list.iterator();
    }
    /**
     * Empeche l'animation des {@link IntelligentSprite} morts
     */
    public void removeTheDead() {
        ArrayList<BasicSprite> newList = new ArrayList<>();
        list.stream().forEach(sprite -> {
            if((sprite instanceof IntelligentSprite) && !((IntelligentSprite) sprite).isDead()){
                newList.add(sprite);
            }
        });
        list = newList;
    }
}
