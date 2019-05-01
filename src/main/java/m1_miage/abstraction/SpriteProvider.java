package m1_miage.abstraction;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.VaisseauSprite;
import m1_miage.presenter.GameBoard;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A la responsabilité des traitements qui touchent a la liste de spitres
 */
public class SpriteProvider {
    private  ArrayList<IntelligentSprite> list;

    public SpriteProvider() {
        this.list = new ArrayList<>();
    }

    /**
     * Ajoute un objet de jeu à la liste de sprites
     * @param p
     */
    public void add(IntelligentSprite p) {
        list.add(p);
    }

    /**
     * @return un iterator sur la liste de sprites
     */
    public Iterator<IntelligentSprite> iterator() {
        return list.iterator();
    }
    /**
     * Empeche l'animation des {@link IntelligentSprite} morts
     * Les renvoie dans une liste pour mise a jour des scores
     */
    public ArrayList<IntelligentSprite> removeTheDead() {
        ArrayList<IntelligentSprite> newList = new ArrayList<>();
        ArrayList<IntelligentSprite> deadList = new ArrayList<>();
        list.stream().forEach(sprite -> {
            if(!sprite.isDead()){
                newList.add(sprite);
            }else{
                deadList.add(sprite);
            }
        });
        list = newList;
        return deadList;
    }
    /**
     * Empeche l'animation des {@link IntelligentSprite} à l'exterieur de la map
     *///TODO test
    public void removeLostSprites(GameBoard b) {
        ArrayList<IntelligentSprite> newList = new ArrayList<>();
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


    public void checkForCollision(IntelligentSprite s, GameBoard gameBoard) {
        Iterator<IntelligentSprite> it = gameBoard.spriteIterator();
        if(s.isDead()) return;
        while (it.hasNext()) {
            IntelligentSprite d = it.next();
            if (d != s) {
                if(!d.isDead() && !s.isDead())
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
        ArrayList<IntelligentSprite> newList = new ArrayList<>();
        list.stream().forEach(sprite -> {
            if((sprite instanceof VaisseauSprite)){
                newList.addAll(((VaisseauSprite) sprite).getWeaponsByPlugin());
            }
        });
        list.addAll(newList);
    }

    public int getLength() {
        return list.size();
    }

    public boolean containsVaisseau() {
        for(IntelligentSprite i : list)
            if(i instanceof VaisseauSprite) return true;
        return false;
    }
}
