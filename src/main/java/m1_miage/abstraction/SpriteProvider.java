package m1_miage.abstraction;

import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.presenter.GameBoard;

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
            if((sprite instanceof IntelligentSprite)
                    && !((IntelligentSprite) sprite).isDead()
            ){
                newList.add(sprite);
            }
        });
        list = newList;
    }
    /**
     * Empeche l'animation des {@link IntelligentSprite} à l'exterieur de la map
     *///TODO test
    public void removeLostSprites(GameBoard b) {
        ArrayList<BasicSprite> newList = new ArrayList<>();
        list.stream().forEach(sprite -> {
            if((sprite instanceof IntelligentSprite)
                    && sprite.getX() > 0 && sprite.getX() < b.getWidth()
                    && sprite.getY() > 0 && sprite.getY() < b.getHeight()
            ){
                newList.add(sprite);
            }
        });
        list = newList;
    }


    public void checkForCollision(BasicSprite s, GameBoard gameBoard) {
        Iterator<BasicSprite> it = gameBoard.spriteIterator();
        while (it.hasNext()) {
            BasicSprite d = it.next();
            if (d != s) {
                if (s.getBoundingShape().getBoundsInParent().intersects(d.getBoundingShape().getBoundsInParent())) {
                    s.handleCollision(gameBoard, d);
                }
            }
        }

    }

    /**
     * ajoute a la memoire du tableau de jeu les balles tirées
     * (sinon ca les affiche mais ca ne peut pas appeller de méthodes dessus dans la boucle animate)
     */
    public void addShots() {
        ArrayList<BasicSprite> newList = new ArrayList<>();
        list.stream().forEach(sprite -> {
            if((sprite instanceof VaisseauSprite)){
                newList.addAll(((VaisseauSprite) sprite).getWeaponsByPlugin());
            }
        });
        list.addAll(newList);
    }
}
