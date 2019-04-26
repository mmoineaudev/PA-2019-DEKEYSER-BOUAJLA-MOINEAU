package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.SpriteProvider;
import m1_miage.abstraction.game_objects.IntelligentSprite;
import m1_miage.abstraction.game_objects.Plugins.Weapon;
import m1_miage.abstraction.game_objects.VaisseauSprite;

import java.util.Iterator;

/**
 * L'objet qui contient le jeu
 */
public class GameBoard {

	private int width;
	private int height;
	public SpriteProvider spriteProvider;

	public GameBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		spriteProvider = new SpriteProvider();
	}

	/**
	 * Ajoute un objet a l'affichage
	 * @param p
	 */
	public void addSprite(BasicSprite p) {
		spriteProvider.add(p);
	}

	/**
	 * @return un iterator sur tous les objets affichés
	 */
	public Iterator<BasicSprite> spriteIterator() {
		return spriteProvider.iterator();
	}

	/**
	 * permet d'animer tous les sprites 1 fois chacun
	 *
	 * @param t               le temps d'une opération ?
	 * @param graphicsContext
	 */
	public void animate(double t, GraphicsContext graphicsContext) {
		spriteProvider.removeTheDead();
		spriteProvider.removeLostSprites(this);
		Iterator<BasicSprite> it = spriteIterator();
		while (it.hasNext()) {
			BasicSprite s = it.next();
			s.update(t, this);
			spriteProvider.checkForCollision(s, this);
			s.render(graphicsContext);
		}
	}

	/**
	 * permet a un vaisseau de savoir s'il doit tirer
	 * @param vaisseauSprite
	 * @return true si un enemy est en face
	 *///TODO TEST
	public boolean facesAnEnemy(VaisseauSprite vaisseauSprite) {
		Iterator<BasicSprite> it = spriteIterator();
		int delta = 50;
		while (it.hasNext()) {
			BasicSprite s = it.next();
			if(!s.equals(vaisseauSprite)){
				switch (vaisseauSprite.getDirection()){
					case NORTH:
						if(s.getY()<vaisseauSprite.getY() && vaisseauSprite.getX()-delta<s.getX() && vaisseauSprite.getX()+delta>s.getX())
							return true;
					case SOUTH:
						if(s.getY()>vaisseauSprite.getY() && vaisseauSprite.getX()-delta<s.getX() && vaisseauSprite.getX()+delta>s.getX())
							return true;
					case EAST:
						if(s.getX()>vaisseauSprite.getX() && vaisseauSprite.getY()-delta<s.getY() && vaisseauSprite.getY()+delta>s.getY())
							return true;
					case WEST:
						if(s.getX()<vaisseauSprite.getX() && vaisseauSprite.getY()-delta<s.getY() && vaisseauSprite.getY()+delta>s.getY())
							return true;
				}
			}
		}
		return false;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}


