package m1_miage.presenter;

import javafx.scene.canvas.GraphicsContext;
import m1_miage.abstraction.BasicSprite;
import m1_miage.abstraction.SpriteProvider;

import java.util.Iterator;

/**
 * L'objet qui contient le jeu
 */
public class GameBoard {

	private int width;
	private int height;
	private SpriteProvider spriteProvider;

	public GameBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		spriteProvider = new SpriteProvider();
	}

	
	public void addSprite(BasicSprite p) {
		spriteProvider.add(p);
	}
	
	public Iterator<BasicSprite> spriteIterator() {
		return spriteProvider.iterator();
	}

	/**
	 * permet d'animer tous les sprites 1 fois chacun
	 * @param t le temps d'une opération ?
	 * @param graphicsContext
	 */
	public void animate(double t, GraphicsContext graphicsContext) {
		spriteProvider.removeTheDead();
		Iterator<BasicSprite> it = spriteIterator();
		while (it.hasNext()) {
			BasicSprite s = it.next();
			s.update(t, this);
			checkForCollision(s);
			s.render(graphicsContext);
		}
	}



	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void checkForCollision(BasicSprite s) {
		Iterator<BasicSprite> it = spriteIterator();
		while (it.hasNext()) {
			BasicSprite d = it.next();
			if (d != s) {
				if (s.getBoundingShape().getBoundsInParent().intersects(d.getBoundingShape().getBoundsInParent())) {
					System.out.println(" it's a crash !!!");
					s.handleCollision(this, d);
				}
			}
		}

	}


	public void removeSprite(BasicSprite p) {
		Iterator<BasicSprite> it = spriteIterator();
		while (it.hasNext()) {
			BasicSprite d = it.next();
			if(d.equals(p)) {
				it.remove();
			}
		}
	}
}
